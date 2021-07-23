package com.sample.mapper;

import java.util.List;

import com.sample.vo.User;

public interface UserMapper {
	void insertUser(User user);

	void updateUser(User user);

	User getUserById(String id);

	User getUserByEmail(String email);

	List<User> getUserByPhone(String phone);
	
	void deleteUser(String id);

}
