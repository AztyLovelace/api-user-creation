package com.assessment.apiusercreation.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.apiusercreation.dto.UserInputDTO;
import com.assessment.apiusercreation.dto.UserOutputDTO;
import com.assessment.apiusercreation.model.User;
import com.assessment.apiusercreation.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/creation")
	public ResponseEntity<?> registerUser(@RequestBody @Valid UserInputDTO userInputDTO){//"
		if(userService.findByEmail(userInputDTO.getEmail()).isPresent()) {
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("message", "This email has already been registered");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
		}
		
		User savedUser = userService.saveUser(userInputDTO);
		UserOutputDTO userOutputDTO = userService.convertTToUserOutputDTO(savedUser);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userOutputDTO);
	}
	
}
