package com.ftn.upib.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ftn.upib.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
	public ResponseEntity<Map> login(@RequestBody UserDTO userDTO) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword());
		try{
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}catch (AuthenticationException e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		try {
			UserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getEmail());
			String token = tokenUtils.generateToken(userDetails);
			String refreshToken = tokenUtils.generateRefreshToken(userDetails);
			Map<String, String> tokens = new HashMap<>();
			tokens.put("token", token);
			tokens.put("refreshToken", refreshToken);
			System.out.println(tokens);
			return ResponseEntity.ok(tokens);
		} catch (UsernameNotFoundException e){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasAnyRole('ADMINISTRATOR', 'CLINIC_ADMINISTRATOR', 'DOCTOR', 'NURSE', 'PATIENT')")
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findOne(@RequestHeader("Authorization") String tokenBearer){
		String token = tokenBearer.substring(7);
		String email = tokenUtils.getUsernameFromToken(token);
		User user = userService.findUserByEmail(email);
		return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ADMINISTRATOR', 'CLINIC_ADMINISTRATOR', 'DOCTOR', 'NURSE', 'PATIENT')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<UserDTO> update(@RequestBody UserDTO updated, @RequestHeader("Authorization") String tokenBearer){
		String token = tokenBearer.substring(7);
		String email = tokenUtils.getUsernameFromToken(token);
		User user = userService.findUserByEmail(email);
		user = new User(user.getId(), updated.getFirstname(), updated.getLastname(), user.getUserType(), user.getEmail(), updated.getPassword(), updated.getAddress(), updated.getPhoneNumber(), user.getLbo(), user.getClinic());
		
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

	@PostMapping("/refresh")
	public ResponseEntity<String> refresh(@RequestBody String refreshToken){
		String username = tokenUtils.getUsernameFromToken(refreshToken);
		if (username !=null){
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			if (tokenUtils.validateToken(refreshToken, userDetails) && userDetails.isAccountNonLocked() && userDetails.isAccountNonExpired()){
				String newToken = tokenUtils.generateToken(userDetails);
				return new ResponseEntity<>(newToken, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
