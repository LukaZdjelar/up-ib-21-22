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
	UserService userService;
	
	@PostMapping("/login")
	private ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO) {
		User user = userService.login(userDTO.getEmail(), userDTO.getPassword());
		
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	private ResponseEntity<UserDTO> findOne(@PathVariable("id") Long id){
		User user = userService.findUserById(id);
		return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	private ResponseEntity<UserDTO> update(@RequestBody UserDTO updated, @PathVariable("id") Long id){
		
		User user = userService.findUserById(id);
		user = new User(id, updated.getFirstname(), updated.getLastname(), user.getUserType(), user.getEmail(), updated.getPassword(), updated.getAddress(), updated.getPhoneNumber(), user.getLbo(), user.getClinic());
		
		userService.save(user);
		return new ResponseEntity<>(updated, HttpStatus.OK);
	}

	@GetMapping(value="/doctors/{id}")
	private ResponseEntity<List<UserDTO>> findDoctors(@PathVariable("id") Long id){
		List<UserDTO> doctorsDTOList = new ArrayList<>();
		for (User user : userService.findAllDoctors()) {
			if (user.getClinic().getId()==id) {
				doctorsDTOList.add(new UserDTO(user));
			}
		}
		
		return new ResponseEntity<>(doctorsDTOList, HttpStatus.OK);
	}
}
