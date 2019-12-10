package com.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Laptop;
import com.app.repo.LaptopRepository;

@RestController
@RequestMapping(value = "laptop/api/")
public class LaptopController {

	@Autowired
	private LaptopRepository laptopRepository;

	@PostMapping(value = "save")
	public Laptop saveLaptop(@RequestBody Laptop laptop) {
		return laptopRepository.save(laptop);
	}

	@GetMapping(value = "update")
	public Optional<Laptop> getLaptop(@RequestParam Integer laptopId) {
		System.out.println(laptopId);
		return laptopRepository.findById(laptopId);
	}
}
