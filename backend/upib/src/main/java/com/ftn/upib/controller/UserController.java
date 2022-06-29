package com.ftn.upib.controller;

import java.util.ArrayList;
import java.util.List;

import com.ftn.upib.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ftn.upib.dto.UserDTO;
import com.ftn.upib.model.User;
import com.ftn.upib.service.UserService;

@Controller
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	TokenUtils tokenUtils;
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserService userService;

	@GetMapping("/encode")
	public void encode(){
		for (User user: userService.findAll()) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userService.save(user);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword());
		Authentication authentication = authenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		try {
			UserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getEmail());
			String token = tokenUtils.generateToken(userDetails);
			System.out.println(token);
			return ResponseEntity.ok(token);
		} catch (UsernameNotFoundException e){
			return ResponseEntity.notFound().build();
		}
	}

	@PreAuthorize("hasAnyRole('ADMINISTRATOR', 'CLINIC_ADMINISTRATOR', 'DOCTOR', 'NURSE', 'PATIENT')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findOne(@PathVariable("id") Long id){
		//preko tokena
		User user = userService.findUserById(id);
		return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ADMINISTRATOR', 'CLINIC_ADMINISTRATOR', 'DOCTOR', 'NURSE', 'PATIENT')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDTO> update(@RequestBody UserDTO updated, @PathVariable("id") Long id){
		//preko tokena

		User user = userService.findUserById(id);
		user = new User(id, updated.getFirstname(), updated.getLastname(), user.getUserType(), user.getEmail(), updated.getPassword(), updated.getAddress(), updated.getPhoneNumber(), user.getLbo(), user.getClinic());
		
		userService.save(user);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ADMINISTRATOR', 'CLINIC_ADMINISTRATOR', 'DOCTOR', 'NURSE', 'PATIENT')")
	@GetMapping(value="/doctors/{id}")
	public ResponseEntity<List<UserDTO>> findDoctors(@PathVariable("id") Long id){
		List<UserDTO> doctorsDTOList = new ArrayList<>();
		for (User user : userService.findDoctorsByClinic(id)) {
			doctorsDTOList.add(new UserDTO(user));
		}
		
		return new ResponseEntity<>(doctorsDTOList, HttpStatus.OK);
	}
}
