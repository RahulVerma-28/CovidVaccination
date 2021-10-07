package com.microservices.vaccinationcenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.vaccinationcenter.entity.VaccinationCenter;

public interface CenterRepository extends JpaRepository<VaccinationCenter, Integer>{

}
