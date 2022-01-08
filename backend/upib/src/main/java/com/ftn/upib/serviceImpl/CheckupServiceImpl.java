package com.ftn.upib.serviceImpl;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	public List<CheckUp> findAll() {
		return checkupRespository.findAll();
	}

	@Override
	public List<CheckUp> findAllByPatient(Long id) {
		List<CheckUp> checkupList = new ArrayList<>();
		for (CheckUp checkUp : findAll()) {
			if (checkUp.getPatient().getId().equals(id)) {
				checkupList.add(checkUp);
			}
		}
		return checkupList;
	}
}
