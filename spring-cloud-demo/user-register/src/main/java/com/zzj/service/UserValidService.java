package com.zzj.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("user-valid")
public interface UserValidService {

	@RequestMapping(value="/user/valid", method=RequestMethod.GET)
	public String valid(@RequestParam("name")String name);
}
