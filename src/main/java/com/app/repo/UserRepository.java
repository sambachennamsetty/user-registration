package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmailId(String emailId);
}
