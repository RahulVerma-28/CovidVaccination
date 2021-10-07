package com.microservices.vaccinationcenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservices.vaccinationcenter.entity.VaccinationCenter;
import com.microservices.vaccinationcenter.model.Citizen;
import com.microservices.vaccinationcenter.model.CitizenVaccinationResponse;
import com.microservices.vaccinationcenter.repository.CenterRepository;
import java.util.*;


@RestController
@RequestMapping("/vaccinationcenter")
public class VaccinationCenterController {
	
	@Autowired
	private CenterRepository centerRepo;

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value="/test")
	public ResponseEntity<String> test()
	{
		return new ResponseEntity<>("hello",HttpStatus.OK);
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ResponseEntity<VaccinationCenter> addCitizen(@RequestBody VaccinationCenter vaccinationCenter)
	{

		System.out.println(vaccinationCenter);
		VaccinationCenter saveCitizen = this.centerRepo.save(vaccinationCenter);
		return new ResponseEntity<>(saveCitizen,HttpStatus.OK);
	}
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public ResponseEntity<CitizenVaccinationResponse> getAllDataBasedOnCenterId(@RequestParam("id") int id)
	{
		
		CitizenVaccinationResponse citizenVaccinationResponse=new CitizenVaccinationResponse();
		
		//get Vaccination center detail
		VaccinationCenter vaccinationCenter = this.centerRepo.findById(id).get();
		citizenVaccinationResponse.setCenter(vaccinationCenter);
		
		//get all citizen registerted to the vaccination center
		List listOfCitizens = restTemplate.getForObject("http://localhost:8081/citizen/getbycenterid?id="+id, List.class);
		citizenVaccinationResponse.setCitizen(listOfCitizens);
		System.out.println(citizenVaccinationResponse);
		return new ResponseEntity<CitizenVaccinationResponse>(citizenVaccinationResponse,HttpStatus.OK);
		
	}
}

