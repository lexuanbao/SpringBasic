package com.example.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.UserRepository;

@Service //Dùng annotation này để có thể injection qua class khác
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public List<UserModel> getUser(){
		return userRepository.findAll();
	}
	
	public UserModel createUser(UserModel user) {
		return userRepository.save(user);
	}
	
	public UserModel getUserById(long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
	}
	
	public UserModel updateUser(long id, UserModel userDetail) {
		
		UserModel user = userRepository.getById(id);
		
		user.setName(userDetail.getName());
		user.setPhone(userDetail.getPhone());
		user.setEmail(userDetail.getEmail());
		user.setAge(userDetail.getAge());
		
		return userRepository.save(user);
	}
	
	public ResponseEntity<?>  deleteUser(long id) {
		UserModel user = userRepository.getById(id);
		userRepository.delete(user);
		return ResponseEntity.ok().build();
	}
}
