package com.ftn.upib.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.upib.model.User;
import com.ftn.upib.repository.UserRepository;
import com.ftn.upib.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User login(String email, String password) {
		User user = userRepository.findUserByEmail(email);
		if(user==null) {
			return null;
		}
		if(user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

	@Override
	public User findUserByEmail(String email) {
		User user = userRepository.findUserByEmail(email);
		if(user==null) {
			return null;
		}
		return user;
	}

}
