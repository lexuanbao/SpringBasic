package com.example.demo.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;

@RestController //Là combination của @Controller và @ResponseBody annotations.
//@Controller annotation là để định nghĩa 1 controller
//@ResponseBody annotation làm cho các method return về 1 response body của 1 request
@RequestMapping(path="/api")
public class UserController {
	
	//@Autowired  //Dùng depency injection, tự tạo instance cho userService
	//private UserService userService;
	
	@Autowired  //Dùng depency injection, tự tạo instance cho userService
	private UserRepository userRepository;
	
	@GetMapping("/users")
	public List<UserModel> getUsers() {
		return userRepository.findAll();
	}
	
	@PostMapping("/users")
	public UserModel createUser(@Valid @RequestBody UserModel user) { //annotation @valid đảm bảo rằng request body phải valid
		return userRepository.save(user);
	}
	
	@GetMapping("/users/{id}")
	public UserModel getUserById(@PathVariable(value = "id") Long userId) {
		return userRepository.findById(userId).
				orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));//Throw error khi id not found ('agu1' not found with 'agu2' : 'agu3')
	}
	
	//Upsert
	@PutMapping("/users/{id}")
	public UserModel updateUser (@PathVariable(value = "id") Long id, @Valid @RequestBody UserModel userDetail) {
		UserModel user = userRepository.findById(id).
				orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
		
		user.setName(userDetail.getName());
		user.setPhone(userDetail.getPhone());
		user.setEmail(userDetail.getEmail());
		user.setAge(userDetail.getAge());
		
		UserModel updatedUser = userRepository.save(user);
		return updatedUser;
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long userId) {
		
		UserModel user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
		userRepository.delete(user);
		
		return ResponseEntity.ok().build();
	}
}
