package com.school.game.mapper;

import com.school.game.model.User;

public interface UserMapper {

	public User findByUsername(String username);
}
