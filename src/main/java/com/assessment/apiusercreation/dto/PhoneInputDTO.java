package com.assessment.apiusercreation.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(description = "User's phone information")
public class PhoneInputDTO {
	
	@NotBlank(message = "Phone number is required")
	@Pattern(regexp = "^[0-9]{7,10}$", message = "Phone number must be between 7 and 10 digits")
	@Schema(description = "Phone number", example = "1234567")
	private String number;
	private String cityCode;
	private String countryCode;
	
	
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
}
