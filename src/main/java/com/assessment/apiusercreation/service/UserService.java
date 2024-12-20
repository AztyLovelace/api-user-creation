package com.assessment.apiusercreation.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.assessment.apiusercreation.dto.UserInputDTO;
import com.assessment.apiusercreation.dto.UserOutputDTO;
import com.assessment.apiusercreation.mapper.UserMapper;
import com.assessment.apiusercreation.model.User;
import com.assessment.apiusercreation.repository.UserRepository;
import com.assessment.apiusercreation.util.JwtUtil;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	
	public UserService(UserRepository userRepository,UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}
	
	public Optional<User> findByEmail(String email){
		return userRepository.findByEmail(email);
	}
	
	public User saveUser(UserInputDTO userInputDTO) {
		User user = userMapper.mapToUserInput(userInputDTO);
		
		String token = JwtUtil.generateToken(user.getEmail());
		user.setToken(token);
		
		return userRepository.save(user);
	}
	
	public UserOutputDTO convertTToUserOutputDTO(User user) {
		return userMapper.mapToUserOutputDTO(user);
	}
}
