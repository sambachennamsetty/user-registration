package com.app.service;

import com.app.model.User;

public interface IUserService {
	
	User findUserByEmail(String emailId);
	void saveUser(User user);

}
