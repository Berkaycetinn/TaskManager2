package com.berkaycetin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashBoardController {
	
	
	//tokenı geçerli user gerçek ise dashboarda erişebilecek
	
	@GetMapping
	public ResponseEntity<String> hello(){
		return ResponseEntity.ok("Welcome dashboard");
	}

}
