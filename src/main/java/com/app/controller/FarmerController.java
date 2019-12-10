package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Farmer;
import com.app.repo.FarmerRepo;

@RestController
public class FarmerController {

	@Autowired
	private FarmerRepo repo;

	@PostMapping(value = "/farmer")
	public String saveFarmer(@RequestBody Farmer farmer) {
		repo.save(farmer);
		return "farmer saved";
	}
}
