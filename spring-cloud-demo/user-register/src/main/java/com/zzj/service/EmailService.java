package com.zzj.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("email-server")
public interface EmailService {

	@RequestMapping(value="/email/send", method=RequestMethod.GET)
	public String sendMail(@RequestParam("email")String email);
}
