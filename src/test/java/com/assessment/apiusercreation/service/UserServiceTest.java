package com.assessment.apiusercreation.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.assessment.apiusercreation.dto.UserInputDTO;
import com.assessment.apiusercreation.mapper.UserMapper;
import com.assessment.apiusercreation.model.User;
import com.assessment.apiusercreation.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
	
	@Mock
	private UserRepository userRepository;
	@Mock
	private UserMapper userMapper;
	
	@InjectMocks
	private UserService userService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	
	@Test
	void testSaveUserGenerateJwtToken() {
		
		UserInputDTO userInputDTO = new UserInputDTO();
		userInputDTO.setName("Test User");
		userInputDTO.setEmail("test@email.com");
		userInputDTO.setPassword("testPass");
		
		User mappedUser = new User();
		mappedUser.setName("Test User");
		mappedUser.setEmail("test@email.com");
		mappedUser.setPassword("testPass");
		
		when(userMapper.mapToUserInput(userInputDTO)).thenReturn(mappedUser);
		when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));
		
		User savedUser = userService.saveUser(userInputDTO);
		
		assertNotNull(savedUser.getToken(), "The token should not be null");
		assertTrue(savedUser.getToken().startsWith("eyJ"), "The token should be a valid JWT");
		
		verify(userRepository, times(1)).save(any(User.class));
		
	}
	
}
