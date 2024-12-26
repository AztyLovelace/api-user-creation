package com.assessment.apiusercreation.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User creation response")
public class UserOutputDTO {

	@Schema(description = "User unique identifier", example = "a6c771d8-83bd-4999-a7ce-8ecf2c5eec54")
	private String id;

	@Schema(description = "Creation timestamp", example = "2024-12-26T17:12:11.201Z")
	private LocalDateTime created;

	@Schema(description = "Last modification timestamp", example = "2024-12-26T17:12:11.201Z")
	private LocalDateTime modified;

	@Schema(description = "Last login timestamp", example = "2024-12-26T17:12:11.201Z")
	private LocalDateTime lastLogin;

	@Schema(
		description = "JWT authentication token",
		example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGV4YW1wbGUuY29tIiwiaWF0IjoxNzM1MjQzOTMxLCJleHAiOjE3MzUyNzk5MzF9"
	)
	private String token;

	@Schema(description = "User account status", example = "true")
	private Boolean isActive;

	// Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getModified() {
		return modified;
	}

	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
