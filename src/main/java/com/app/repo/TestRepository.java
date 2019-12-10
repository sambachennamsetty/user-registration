package com.app.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.app.model.User;

public interface TestRepository extends CrudRepository<User, Long> {
	
	@Transactional
	@Procedure(procedureName = "insert_records")
	void movePersonToHistory(@Param("name") String personId);

}
