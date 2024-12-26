package com.assessment.apiusercreation.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Input data for user creation")
public class UserInputDTO {

	@NotBlank(message = "Name is required")
	@Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
	@Schema(description = "User's full name", example = "John Doe")
	private String name;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Email must be in a valid format")
	@Schema(description = "User's email address", example = "john.doe@example.com")
	private String email;

	@NotBlank(message = "Password is required")
	@Pattern(
		regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$",
		message = "Password must be at least 8 characters long and include uppercase, lowercase, and numbers"
	)
	@Schema(
		description = "User's password", 
		example = "Password123",
		pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$"
	)
	private String password;

	@Schema(description = "List of user's phone numbers")
	private List<PhoneInputDTO> phones;

	// Getters and Setters
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

	public List<PhoneInputDTO> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneInputDTO> phones) {
		this.phones = phones;
	}
}
