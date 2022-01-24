package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ResponseObject;
import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping(path = "/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/users")
	public List<UserModel> getUser() {
		return userRepository.findAll(Sort.by(Sort.Direction.ASC, "name").and(Sort.by(Sort.Direction.ASC, "age")));
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<ResponseObject> getUserById(@PathVariable(value = "id") Long userId) {
		try {
			UserModel user = userRepository.findById(userId).orElse(null);
			if(user != null) {
				return ResponseEntity.status(HttpStatus.OK).body(
						new ResponseObject("OK", "valid id", user)
				);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
						new ResponseObject("failed", "id id not exist", user)
				);
			}
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new ResponseObject("failed", "id is null", null)
			);
		}
	}
	
	@PostMapping("/users/insert")
	public ResponseEntity<ResponseObject> insertUser(@Valid @RequestBody UserModel user) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject("OK", "insert successful", userRepository.save(user))
			);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new ResponseObject("failed", "insert unsuccessful", null)
			);
		}
	}
	
	//upsert
	@PutMapping("/users/update/{id}")
	public HttpEntity<ResponseObject> updateUser(@RequestBody UserModel userDetail) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject("OK", "Update succesful", userRepository.save(userDetail))
			);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new ResponseObject("failed", "update unsuccessful", null)
			);
		}
	}
	
	@DeleteMapping("users/delete/{id}")
	public HttpEntity<ResponseObject> deleteUser(@PathVariable(value="id") Long id) {
		try {
			userRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject("OK", "Delete successful", id)
			);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new ResponseObject("failed", "Delete unsuccessful", null)
			);
		}
	}
	
	//get user by name trong white list
	@GetMapping("users/name/{name}")
	public List<UserModel> getUserByName(@PathVariable(value="name") String name) {
		List<String> whiteList = new ArrayList<String>();
		whiteList.add("Richard Hendricks");
		whiteList.add("a");
		whiteList.add("b");
		return userRepository.getUserByName(name, whiteList, JpaSort.unsafe(("LENGTH(phone)"))); //Với unsafe() thì nó sẽ không cần check liệu thuộc tính có thuộc về model hay không (length)
//		return userRepository.getUserByName(name, whiteList});
	}
	
	//update user by name
	@PutMapping("users/update/name/{name}")
	public HttpEntity<ResponseObject> updateUserByName(@PathVariable(value="name") String name, @RequestBody UserModel userDetail){
		try {
			userRepository.updateUserByName(name, userDetail.getPhone(), userDetail.getEmail(), userDetail.getAge());
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject("OK", "Update user successful", userDetail)
			);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new ResponseObject("failed", "Update unsuccessful", null)
			);
		}
	}
	
	@PutMapping("users/update/test")
	public HttpEntity<ResponseObject> updateTest(){
		userRepository.updateTest();
		return ResponseEntity.status(HttpStatus.OK).body(
				new ResponseObject("OK", "Update user successful", null)
		);
	}
}
