package com.ftn.upib.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ftn.upib.dto.AppointmentDTO;
import com.ftn.upib.service.AppointmentService;

@Controller
@RequestMapping("/appointment")
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {

	@Autowired
	AppointmentService appointmentService;
	
	@GetMapping(value="/{id}")
	private ResponseEntity<AppointmentDTO> get(@PathVariable("id") Long id){
		return new ResponseEntity<>(new AppointmentDTO(appointmentService.findAppointmentById(id)), HttpStatus.OK);
	}
}
