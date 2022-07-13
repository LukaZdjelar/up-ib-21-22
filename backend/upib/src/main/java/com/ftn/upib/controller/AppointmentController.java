package com.ftn.upib.controller;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.ftn.upib.service.EmailService;
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
	
	@Autowired
	EmailService emailService;

	@PreAuthorize("hasAnyRole('ADMINISTRATOR', 'CLINIC_ADMINISTRATOR', 'DOCTOR', 'NURSE', 'PATIENT')")
	@GetMapping(value="/{id}")
	public ResponseEntity<AppointmentDTO> get(@PathVariable("id") Long id){
		return new ResponseEntity<>(new AppointmentDTO(appointmentService.findAppointmentById(id)), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ADMINISTRATOR', 'CLINIC_ADMINISTRATOR')")
	@PostMapping
	public ResponseEntity<AppointmentDTO> create(@RequestBody AppointmentDTO appointmentDTO){
		Appointment appointment = new Appointment(null, userService.findUserById(appointmentDTO.getDoctorId()), appointmentDTO.getDateAndTime(), appointmentDTO.getPrice(), appointmentDTO.getDuration(), clinicService.findClinicById(appointmentDTO.getClinicId()), true);
		
		appointmentService.create(appointment);
		return new ResponseEntity<>(new AppointmentDTO(appointment), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('PATIENT')")
	@PostMapping(value="/schedule")
	public ResponseEntity<CheckUpDTO> schedule(@RequestBody CheckUpDTO checkUpDTO){
		CheckUp checkup = new CheckUp(null, appointmentService.findAppointmentById(checkUpDTO.getAppointmentId()), userService.findUserById(checkUpDTO.getPatientId()), "");
		Appointment appointment = appointmentService.findAppointmentById(checkUpDTO.getAppointmentId());
		appointmentService.schedule(appointment);
		checkupService.save(checkup);
		String patientEmail = userService.findUserById(checkUpDTO.getPatientId()).getEmail();
		String message = "Uspesno zakazan pregled kod doktora " + appointment.getDoctor().getFirstname() + " " + appointment.getDoctor().getLastname() + " dana " + appointment.getDateAndTime() + "<br />" + "<br />" +
							"Hvala na poveranju!" + "<br />" + appointment.getDoctor().getClinic().getName();
		System.out.println(patientEmail);
		System.out.println(message);
		emailService.sendEmail(patientEmail, message);
		return new ResponseEntity<>(checkUpDTO, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('PATIENT')")
	@GetMapping(value="/history/{id}")
	public ResponseEntity<List<AppointmentDTO>> history(@PathVariable("id") Long id){
		List<AppointmentDTO> appointmentListDTO = new ArrayList<>();
		for (Appointment appointment : checkupService.findAppointmentByPatientId(id)) {
			appointmentListDTO.add(new AppointmentDTO(appointment));
		}
		return new ResponseEntity<>(appointmentListDTO, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('DOCTOR')")
	@GetMapping(value="/workcalendar/{id}")
	public ResponseEntity<List<AppointmentDTO>> workCalendar(@PathVariable("id") Long id){
		List<AppointmentDTO> appointmentListDTO = new ArrayList<>();
		for (Appointment appointment : appointmentService.findAppointmentByDoctorId(id)) {
			appointmentListDTO.add(new AppointmentDTO(appointment));
		}
		return new ResponseEntity<>(appointmentListDTO, HttpStatus.OK);
	}
}
