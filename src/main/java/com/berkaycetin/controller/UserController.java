package com.berkaycetin.controller;

import java.util.List;

import com.berkaycetin.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


import com.berkaycetin.entities.User;
import com.berkaycetin.service.UserService;



@RestController
@RequestMapping("auth")
  
    
public class UserController {
	
	  

	
	@Autowired
	UserService userService;
	@Autowired
    AuthenticationService authenticationService;
	
	 @PostMapping("/register")
	    public ResponseEntity<UserResponse> newUser(@RequestBody User user) {
	        return ResponseEntity.ok(authenticationService.save(user));
	    }



	
	@PostMapping("/login")
	public ResponseEntity<UserResponse> auth(@RequestBody UserRequest userRequest){
	    return ResponseEntity.ok(authenticationService.auth(userRequest));
	}

	}
