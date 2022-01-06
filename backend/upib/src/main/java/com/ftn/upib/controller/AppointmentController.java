package com.ftn.upib.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ftn.upib.dto.AppointmentDTO;
import com.ftn.upib.dto.CheckUpDTO;
import com.ftn.upib.model.Appointment;
import com.ftn.upib.model.CheckUp;
import com.ftn.upib.service.AppointmentService;
import com.ftn.upib.service.CheckupService;
import com.ftn.upib.service.ClinicService;
import com.ftn.upib.service.UserService;

@Controller
@RequestMapping("/appointment")
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {

	@Autowired
	AppointmentService appointmentService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ClinicService clinicService;
	
	@Autowired
	CheckupService checkupService;
	
	@GetMapping(value="/{id}")
	private ResponseEntity<AppointmentDTO> get(@PathVariable("id") Long id){
		return new ResponseEntity<>(new AppointmentDTO(appointmentService.findAppointmentById(id)), HttpStatus.OK);
	}
	
	@PostMapping
	private ResponseEntity<AppointmentDTO> create(@RequestBody AppointmentDTO appointmentDTO){
		Appointment appointment = new Appointment(null, userService.findUserById(appointmentDTO.getDoctorId()), appointmentDTO.getDateAndTime(), appointmentDTO.getPrice(), appointmentDTO.getDuration(), clinicService.findClinicById(appointmentDTO.getClinicId()), true);
		
		appointmentService.create(appointment);
		return new ResponseEntity<>(new AppointmentDTO(appointment), HttpStatus.OK);
	}
	
	@PostMapping(value="/schedule")
	private ResponseEntity<CheckUpDTO> schedule(@RequestBody CheckUpDTO checkUpDTO){
		System.out.println(checkUpDTO);
		CheckUp checkup = new CheckUp(null, appointmentService.findAppointmentById(checkUpDTO.getAppointmentId()), userService.findUserById(checkUpDTO.getPatientId()), "");
		Appointment appointment = appointmentService.findAppointmentById(checkUpDTO.getAppointmentId());
		appointmentService.schedule(appointment);
		checkupService.save(checkup);
		return new ResponseEntity<>(checkUpDTO, HttpStatus.OK);
	}
}
