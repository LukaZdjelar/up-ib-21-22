package com.ftn.upib.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.upib.model.CheckUp;
import com.ftn.upib.repository.CheckupRepository;
import com.ftn.upib.service.CheckupService;

@Service
public class CheckupServiceImpl implements CheckupService{

	@Autowired
	CheckupRepository checkupRespository;
	
	@Override
	public CheckUp save(CheckUp checkup) {
		return checkupRespository.save(checkup);
	}

}
