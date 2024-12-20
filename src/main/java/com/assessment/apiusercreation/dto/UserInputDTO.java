package com.assessment.apiusercreation.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class UserInputDTO {
	
	@NotEmpty(message = "The name cannot be empty")
	private String name;
	
	@NotEmpty(message = "The email cannot be empty")
	@Email(message = "The email is not in a valid format")
	private String email;
	
	@NotEmpty(message = "The password cannot be empty")
	private String password;
	
	private List<PhoneInputDTO> phoneList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<PhoneInputDTO> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<PhoneInputDTO> phoneList) {
		this.phoneList = phoneList;
	}
	
	
	
}
