package com.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.model.User;
import com.app.repo.UserRepository;
import com.app.service.IUserService;
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	@Override
	public User findUserByEmail(String emailId) {
		return userRepo.findByEmailId(emailId);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(pwdEncoder.encode(user.getPassword()));
		userRepo.save(user);
	}

}
