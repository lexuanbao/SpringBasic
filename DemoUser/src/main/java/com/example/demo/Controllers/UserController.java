package com.example.demo.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.ResponseObject;
import com.example.demo.Model.UserModel;
import com.example.demo.Repositories.UserRepository;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/users")
	public List<UserModel> getUser() {
		return this.userRepository.findAll();
	}
	
	@PostMapping("/users/insert")
	public ResponseEntity<ResponseObject> insertUser(@RequestBody UserModel user) {
		try {
			userRepository.save(user);
			return ResponseEntity.status(HttpStatus.OK).body(
							new ResponseObject("OK", "add user successly", user)
					);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new ResponseObject("failed", "can't add user", null)
			);
		}
	}
}
