package com.ftn.upib.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.upib.model.Clinic;
import com.ftn.upib.repository.ClinicRepository;
import com.ftn.upib.service.ClinicService;

@Service
public class ClinicServiceImpl implements ClinicService{

	@Autowired
	ClinicRepository clinicRepository;
	
	@Override
	public List<Clinic> findAll() {
		return clinicRepository.findAll();
	}

	@Override
	public Clinic findClinicById(Long id) {
		return clinicRepository.findClinicById(id);
	}
}
