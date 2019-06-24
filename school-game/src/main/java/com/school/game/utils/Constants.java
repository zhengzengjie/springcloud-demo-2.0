package com.school.game.utils;

import java.util.HashMap;
import java.util.Map;

public class Constants {

	public static Map<Integer, String> USER_TYPE_MAP = new HashMap<Integer, String>();
	static {
		USER_TYPE_MAP.put(1, "admin");
		USER_TYPE_MAP.put(2, "teacher");
		USER_TYPE_MAP.put(3, "student");
	}
	
	public static final String SESSION_USER_KEY = "SCHOOL_GAME_LOGIN_USER";
}
