package com.ftn.upib.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ftn.upib.dto.AppointmentDTO;
import com.ftn.upib.dto.ClinicDTO;
import com.ftn.upib.model.Appointment;
import com.ftn.upib.model.Clinic;
import com.ftn.upib.service.AppointmentService;
import com.ftn.upib.service.ClinicService;

@Controller
@RequestMapping("/clinic")
@CrossOrigin(origins = "http://localhost:3000")
public class ClinicController {

	@Autowired
	ClinicService clinicService;
	
	@Autowired
	AppointmentService appointmentService;
	
	@GetMapping
	private ResponseEntity<List<ClinicDTO>> findAll(){
		List<ClinicDTO> clinicDTOList = new ArrayList<>();
		for (Clinic clinic : clinicService.findAll()) {
			clinicDTOList.add(new ClinicDTO(clinic));
		}
		return new ResponseEntity<>(clinicDTOList, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	private ResponseEntity<ClinicDTO> findOne(@PathVariable("id") Long id){
		Clinic clinic = clinicService.findClinicById(id);
		return new ResponseEntity<>(new ClinicDTO(clinic), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}/appointments")
	private ResponseEntity<List<AppointmentDTO>> findClinicAppointments(@PathVariable("id") Long id){
		List<Appointment> clinicAppointments = appointmentService.findAllClinic(id);
		List<AppointmentDTO> clinicAppointmentsDTO = new ArrayList<>();
		for (Appointment appointment : clinicAppointments) {
			clinicAppointmentsDTO.add(new AppointmentDTO(appointment));
		}
		return new ResponseEntity<>(clinicAppointmentsDTO, HttpStatus.OK);
	}
}
