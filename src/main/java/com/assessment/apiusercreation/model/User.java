package com.assessment.apiusercreation.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class User {
	
	@Id
	private UUID id;
	
	private String name;
	private String email;
	private String password;
	
	private LocalDateTime created;
	private LocalDateTime modified;
	private LocalDateTime lastLogin;
	
	private String token;
	private Boolean isActive;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "user_id")
	private List<Phone> phoneList;
	
	@PrePersist
	public void generateUUID() {
		this.id = UUID.randomUUID();
		this.created = LocalDateTime.now();
		this.modified = LocalDateTime.now();
		this.lastLogin = LocalDateTime.now();
		this.isActive = true;
	}
	
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

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

	public List<Phone> getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(List<Phone> phoneList) {
		this.phoneList = phoneList;
	}
	
	
}
