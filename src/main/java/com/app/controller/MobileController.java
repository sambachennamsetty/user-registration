package com.app.controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Mobile;
import com.app.repo.MobileRepository;
import com.app.service.impl.MobileServiceImpl;

@RestController
@RequestMapping("/mobile")
public class MobileController {
	
	private static final Logger logger = LogManager.getLogger(MobileController.class);

	@Autowired
	private MobileRepository mobileRepository;

	@Autowired
	private MobileServiceImpl mobileServiceImpl;

	@PostMapping("/save")
	public String saveMobile(@RequestBody Mobile mobile) {
		Mobile mob = mobileRepository.save(mobile);
		if (mob.getMobileId() != null) {
			logger.info("Mobile Saved...!");
			return "Mobile Saved....!";
		}
		logger.warn("Mobile Saved...!");
		return "Not Saved";
	}

	// this is for batch update using statement
	@PostMapping("/saveBulk")
	public String saveBulkMobile(@RequestBody List<Mobile> mobiles) throws SQLException {
		mobileServiceImpl.mobileBatchUpdate(mobiles);

		return "Saved Successfully";
	}

	@PostMapping("/date")
	public Date saveBulkMobile(@RequestBody Mobile date) throws SQLException {
		return date.getDate();
	}

}
