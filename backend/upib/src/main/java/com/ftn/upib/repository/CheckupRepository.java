package com.ftn.upib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.upib.model.CheckUp;

@Repository
public interface CheckupRepository extends JpaRepository<CheckUp, Long>{
	
}
