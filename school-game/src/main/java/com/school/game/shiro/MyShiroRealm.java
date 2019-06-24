package com.school.game.shiro;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.school.game.model.User;
import com.school.game.service.UserService;
import com.school.game.utils.Constants;
import com.school.game.utils.ShiroHelper;

public class MyShiroRealm extends AuthorizingRealm {
	@Resource
	private UserService userServiceImpl;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
		User user = ShiroHelper.getSession(Constants.SESSION_USER_KEY);
		if(user == null){
			return null;
		}
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.addRole(Constants.USER_TYPE_MAP.get(user.getType()));
		return authorizationInfo;
	}

	/* 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
		UsernamePasswordToken upToken = (UsernamePasswordToken)token;
		// 获取用户的输入的账号.
		String username = upToken.getUsername();
//        System.out.println(token.getCredentials());
		// 通过username从数据库中查找 User对象，如果找到，没找到.
		// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		User user = userServiceImpl.findByUsername(username);
//        System.out.println("----->>userInfo="+userInfo);
		if (user == null) {
			return null;
		}
		if (user.getStatus() == 0) { // 账户冻结
			throw new LockedAccountException();
		}
		this.clearCache(SecurityUtils.getSubject().getPrincipals());
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, // 用户名
				user.getPassword(), // 密码
				getName() // realm name
		);
		return authenticationInfo;
	}

}
