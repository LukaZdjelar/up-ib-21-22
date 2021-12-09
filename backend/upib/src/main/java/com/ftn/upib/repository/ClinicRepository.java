package com.ftn.upib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.upib.model.Clinic;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, Long>{

	Clinic findClinicById(Long id);
}
