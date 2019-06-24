package com.school.game.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.school.game.mapper.UserMapper;
import com.school.game.model.User;
import com.school.game.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;
	
	@Override
	public User findByUsername(String username) {
		return userMapper.findByUsername(username);
	}

}
