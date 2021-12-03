package com.ftn.upib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.upib.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findUserByEmail(String email);
}
