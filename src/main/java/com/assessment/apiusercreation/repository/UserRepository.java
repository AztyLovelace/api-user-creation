package com.assessment.apiusercreation.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment.apiusercreation.model.User;

public interface UserRepository extends JpaRepository<User, UUID>{
	Optional<User> findByEmail(String email);
}
