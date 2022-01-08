package com.ftn.upib.service;

import java.util.List;

import com.ftn.upib.model.CheckUp;

public interface CheckupService {
	CheckUp save(CheckUp checkup);
	List<CheckUp> findAll();
	List<CheckUp> findAllByPatient(Long id);
}
