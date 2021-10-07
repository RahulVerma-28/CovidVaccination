package com.microservices.citizenservice.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservices.citizenservice.entity.Citizen;

@Repository
public interface CitizenRepository extends CrudRepository<Citizen,Integer>{

	public List<Citizen> findByVaccinationCenterId(Integer id);
}
