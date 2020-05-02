package com.pagantis.admintool.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pagantis.admintool.dto.UserDTO;
import com.pagantis.admintool.model.User;
import com.pagantis.admintool.service.UserService;
import com.pagantis.admintool.service.UserServiceImpl;

@RestController
@CrossOrigin(origins = {"http://localhost:8081" })
@RequestMapping(value = "/api")
public class UserController {
	
	@Autowired UserService userService;
	
	/**
	 * Get all the users available
	 * @return list of users
	 */
	@GetMapping("/users")
	 ResponseEntity <List<User>> findAllUsers() {
		List <User> users = userService.findAllUsers();
		return new ResponseEntity < >(users, HttpStatus.OK);
	}

 
	/**
     * Get the user detail based on the id passed by the client API.
     * @param id
     * @return user detail
     */
	@GetMapping("/user/{id}")
	ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
		UserDTO user = userService.findById(id);		
		return new ResponseEntity < >(user, HttpStatus.OK);		
	}
	 
	
	/**
     * Create a user with the system.This end point accepts user information in 
     * the json format.It will create and send back the data to the REST user.
     * @param user
     * @return newely created user
     */
	@PostMapping(value = "/user")
	public ResponseEntity <UserDTO> createUser(@RequestBody UserDTO user) {
		final UserDTO userDTO = userService.createUser(user);
		return new ResponseEntity < >(userDTO, HttpStatus.CREATED);
	}
	
	
	/**
     * Deleted the user from the system.client will pass the ID for the user 
     * @param id
     * @return
     */
	  @DeleteMapping("/user/{id}")
	  public ResponseEntity<Long> deleteUser(@PathVariable Long id){		
		   userService.deleteUser(id);		
			return new ResponseEntity < >(id, HttpStatus.OK);	
	  }
	
}
