package com.school.game.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.school.game.model.User;
import com.school.game.service.UserService;
import com.school.game.utils.Constants;
import com.school.game.utils.ShiroHelper;

@RestController
public class LoginController {

	@Resource
	private UserService userServiceImpl;

	/**
	 * 登录方法
	 * 
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestBody User user) {
		JSONObject jsonObject = new JSONObject();
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),
				user.getPassword());
		try {
			subject.login(token);

			user = userServiceImpl.findByUsername(user.getUsername());
			ShiroHelper.setSession(Constants.SESSION_USER_KEY, user);
			jsonObject.put("token", subject.getSession().getId());
			jsonObject.put("msg", "登录成功");
			jsonObject.put("code", 0);
			jsonObject.put("type", user.getType());
		} catch (IncorrectCredentialsException e) {
			jsonObject.put("msg", "密码错误");
			jsonObject.put("code", 1);
		} catch (LockedAccountException e) {
			jsonObject.put("msg", "登录失败，该用户已被冻结");
			jsonObject.put("code", 2);
		} catch (AuthenticationException e) {
			jsonObject.put("msg", "该用户不存在");
			jsonObject.put("code", 3);
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("msg", "登录失败");
			jsonObject.put("code", 4);
		}
		return jsonObject.toString();
	}

	/**
	 * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/noLogin")
	@ResponseBody
	public Object noLogin() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 999);
		map.put("msg", "未登录");
		return map;
	}
	
	/**
	 * 无权限
	 * 
	 * @return
	 */
	@RequestMapping(value = "/403")
	@ResponseBody
	public Object noPermission() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 99);
		map.put("msg", "非法请求");
		return map;
	}
}
