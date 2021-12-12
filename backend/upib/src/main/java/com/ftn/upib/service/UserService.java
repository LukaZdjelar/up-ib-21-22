package com.ftn.upib.service;

import com.ftn.upib.model.User;

public interface UserService {
	User login(String email, String password);
	User findUserByEmail(String email);
	User findUserById(Long id);
	User save(User user);
}
