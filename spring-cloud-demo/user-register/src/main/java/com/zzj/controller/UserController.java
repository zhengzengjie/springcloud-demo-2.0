package com.zzj.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zzj.service.EmailService;
import com.zzj.service.UserValidService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Resource
	UserValidService userValidService;
	
	@Resource
	EmailService emailService;
	
	/**
	 * 用户注册
	 * @param name
	 * @return
	 */
	@GetMapping("register")
	public String register(String name) {
		//先验证是否合法
		String result = userValidService.valid(name);
		if(result.contains("successful")) {
			//发送邮件
			result += emailService.sendMail(name + "@foxmail.com");
		}
		return result;
	}
}
