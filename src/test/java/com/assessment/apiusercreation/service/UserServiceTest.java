package com.assessment.apiusercreation.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.assessment.apiusercreation.dto.UserInputDTO;
import com.assessment.apiusercreation.mapper.UserMapper;
import com.assessment.apiusercreation.model.User;
import com.assessment.apiusercreation.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
	
	@Mock
	private UserRepository userRepository;
	@Mock
	private UserMapper userMapper;
	
	@InjectMocks
	private UserService userService;
	
	
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
		

		assertNotNull(userMapper, "UserMapper should not be null");
		
		when(userMapper.mapToUserInput(any(UserInputDTO.class))).thenReturn(mappedUser);
		when(userRepository.save(any(User.class))).thenReturn(mappedUser);
		

		User savedUser = userService.saveUser(userInputDTO);
		
	
		assertNotNull(savedUser, "Saved user should not be null");
		assertNotNull(savedUser.getToken(), "The token should not be null");
		assertTrue(savedUser.getToken().startsWith("eyJ"), "The token should be a valid JWT");
		
		verify(userMapper, times(1)).mapToUserInput(any(UserInputDTO.class));
		verify(userRepository, times(1)).save(any(User.class));
	}
	
}
