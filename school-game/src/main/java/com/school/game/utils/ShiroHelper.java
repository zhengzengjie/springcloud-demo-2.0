package com.school.game.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * shiro 辅助工具类
 * 
 * @author asus
 * 
 */
public class ShiroHelper {

	/**
	 * 将一些数据放到ShiroSession中,以便于其它地方使用
	 */
	public static void setSession(Object key, Object value) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				session.setAttribute(key, value);
			}
		}
	}
	

	/**
	 * 获取session中的数据
	 * 
	 * @param <T>
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getSession(final Object key) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			Session session = currentUser.getSession();
			if (null != session) {
				return (T) session.getAttribute(key);
			}
		}
		return null;
	}
	
	/**
	 * 检验是否为ip地址
	 * 
	 * @param host
	 * @return
	 */
	public static boolean isIPAddress(String host) {
		String exg = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
		if (host.matches(exg)) {
			return true;
		}
		return false;
	}
}
