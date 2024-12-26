package com.assessment.apiusercreation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.apiusercreation.dto.ErrorResponse;
import com.assessment.apiusercreation.dto.UserInputDTO;
import com.assessment.apiusercreation.dto.UserOutputDTO;
import com.assessment.apiusercreation.exception.EmailAlreadyExistsException;
import com.assessment.apiusercreation.model.User;
import com.assessment.apiusercreation.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@Tag(name = "User Management", description = "Endpoints for managing users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(
        value = "/creation",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
        summary = "Create a new user",
        description = "Creates a new user and returns the created user details with JWT token"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "User successfully created",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = UserOutputDTO.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input data",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "409",
            description = "Email already registered",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = ErrorResponse.class)
            )
        )
    })
    public ResponseEntity<UserOutputDTO> registerUser(@RequestBody @Valid UserInputDTO userInputDTO) {
        // Check if the email is already registered
        if (userService.findByEmail(userInputDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException(userInputDTO.getEmail());
        }

        // Save the new user
        User savedUser = userService.saveUser(userInputDTO);

        // Convert saved user to output DTO
        UserOutputDTO userOutputDTO = userService.convertTToUserOutputDTO(savedUser);

        // Return created response with the output DTO
        return ResponseEntity.status(HttpStatus.CREATED).body(userOutputDTO);
    }
}
