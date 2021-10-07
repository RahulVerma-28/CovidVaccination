package com.microservices.citizenservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.citizenservice.entity.Citizen;
import com.microservices.citizenservice.repository.CitizenRepository;

@RestController
@RequestMapping(value="/citizen")
public class CitizenController {
	
	@Autowired
	private CitizenRepository citizenRepository;
	
	private Citizen citizen;
	
	
	@GetMapping(value="/test")
	public ResponseEntity<String> test()
	{
		return new ResponseEntity<>("hello",HttpStatus.OK);
	}
	
	@GetMapping(value="/getbycenterid")
	public ResponseEntity<java.util.List<Citizen>> getById(@RequestParam("id") Integer id)
	{
		List<Citizen> listCitizen = this.citizenRepository.findByVaccinationCenterId(id);
		return new ResponseEntity<>(listCitizen,HttpStatus.OK);
	}
	
	@PostMapping(value="/add")
	public ResponseEntity<Citizen> addCitizen(@RequestBody Citizen citizen)
	{

		System.out.println(citizen);
		Citizen saveCitizen = this.citizenRepository.save(citizen);
		return new ResponseEntity<>(saveCitizen,HttpStatus.OK);
	}

}
