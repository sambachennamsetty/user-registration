package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Mobile;
@Repository
public interface MobileRepository extends JpaRepository<Mobile, String> {

}
