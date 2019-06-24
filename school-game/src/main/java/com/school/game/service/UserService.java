package com.school.game.service;

import com.school.game.model.User;

public interface UserService {

	public User findByUsername(String username);
}
