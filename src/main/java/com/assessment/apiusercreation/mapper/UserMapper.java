package com.assessment.apiusercreation.mapper;

import org.springframework.stereotype.Component;

import com.assessment.apiusercreation.dto.UserInputDTO;
import com.assessment.apiusercreation.dto.UserOutputDTO;
import com.assessment.apiusercreation.model.Phone;
import com.assessment.apiusercreation.model.User;

@Component
public class UserMapper {

	public User mapToUserInput(UserInputDTO userInputDTO) {
		User user = new User();
		user.setName(userInputDTO.getName());
		user.setEmail(userInputDTO.getEmail());
		user.setPassword(userInputDTO.getPassword());
		
		if(userInputDTO.getPhones()!=null) {
			user.setPhoneList(userInputDTO.getPhones().stream().map(phoneInputDTO -> {
				Phone phone = new Phone();
				phone.setNumber(phoneInputDTO.getNumber());
				phone.setCityCode(phoneInputDTO.getCityCode());
				phone.setCountryCode(phoneInputDTO.getCountryCode());
				return phone;
			}).toList());
		}
		return user;
	}
	
	public UserOutputDTO mapToUserOutputDTO(User user) {
		UserOutputDTO userOutputDTO = new UserOutputDTO();
		userOutputDTO.setId(user.getId().toString());
		userOutputDTO.setCreated(user.getCreated());
		userOutputDTO.setModified(user.getModified());
		userOutputDTO.setLastLogin(user.getLastLogin());
		userOutputDTO.setToken(user.getToken());
		userOutputDTO.setIsActive(user.getIsActive());
		return userOutputDTO;
	}
	
}
