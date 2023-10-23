package com.market.tracker.controller;

import java.util.HashMap;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.market.tracker.dto.UserDTO;
import com.market.tracker.request.ChangePasswordRequest;
import com.market.tracker.request.LoginRequest;
import com.market.tracker.request.UserRequest;
import com.market.tracker.request.VerificationRequest;
import com.market.tracker.service.UserService;
import com.market.tracker.util.responseHandler.ResponseHandler;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint for retrieving a user by their ID.
    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable UUID userId) {
        // Retrieve the user by their user ID using the UserService.getUser method.
        UserDTO userDTO = userService.getUser(userId);

        if (userDTO != null) {
            // Return a positive response with the user's details if the user is found.
            return ResponseHandler.successResponse(HttpStatus.OK, "User retrieved successfully", userDTO);
        } else {
            // Return a negative response when the user is not found.
            return ResponseHandler.errorResponse(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    // Endpoint for user registration.
    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@RequestBody UserRequest userRequest) {
        // Create a new user registration using UserService.register method.
        UserDTO createdUser = userService.register(
                userRequest.getUsername(),
                userRequest.getEmail(),
                userRequest.getPassword());
        if (createdUser != null) {
            // Return a positive response when user registration is successful.
            return ResponseHandler.successResponse(HttpStatus.CREATED, "User registered successfully", createdUser);
        } else {
            // Return a negative response when user registration fails.
            return ResponseHandler.errorResponse(HttpStatus.BAD_REQUEST, "Failed to register user");
        }
    }

    // Endpoint for user login.
    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody LoginRequest loginRequest) {
        // Perform user login using the UserService.login method.
        UserDTO userDTO = userService.login(loginRequest.getUsernameOrEmail(), loginRequest.getPassword());
        if (userDTO != null) {
            // Return a positive response when the user successfully logs in.
            return ResponseHandler.successResponse(HttpStatus.OK, "User logged in successfully", userDTO);
        } else {
            // Return a negative response when login fails.
            return ResponseHandler.errorResponse(HttpStatus.UNAUTHORIZED, "Login failed");
        }
    }

    // Endpoint for user logout.
    @GetMapping("/logout/{userId}")
    public ResponseEntity<Object> logoutUser(@PathVariable UUID userId) {
        // Log out the user using the UserService.logout method.
        userService.logout(userId);
        // Return a positive response when the user logs out successfully.
        return ResponseHandler.successResponse(HttpStatus.OK, "User logged out successfully", new HashMap<>());
    }

    // Endpoint for initiating a password reset request.
    @PostMapping("/forgotPassword/{userId}")
    public ResponseEntity<Object> forgotPassword(@PathVariable UUID userId) {
        if (userService.forgotPassword(userId)) {
            // Return a positive response when the password reset email is successfully
            // sent.
            return ResponseHandler.successResponse(HttpStatus.OK, "Password reset email sent", new HashMap<>());
        } else {
            // Return a negative response when the password reset operation fails.
            return ResponseHandler.errorResponse(HttpStatus.BAD_REQUEST, "User not found or password reset failed");
        }
    }

    // Endpoint for changing the user's password.
    @PostMapping("/changePassword")
    public ResponseEntity<Object> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        if (userService.changePassword(changePasswordRequest.getResetToken(), changePasswordRequest.getNewPassword())) {
            // Return a positive response when the password is successfully changed.
            return ResponseHandler.successResponse(HttpStatus.OK, "Password changed successfully", new HashMap<>());
        } else {
            // Return a negative response when the password change operation fails.
            return ResponseHandler.errorResponse(HttpStatus.BAD_REQUEST, "Password change failed");
        }
    }

    // Endpoint for updating user information.
    @PutMapping("/update/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable UUID userId, @RequestBody UserRequest userRequest) {
        // Update user information using the UserService.updateUser method.
        UserDTO updatedUser = userService.updateUser(userId,
                userRequest.getUsername(),
                userRequest.getEmail(),
                userRequest.getPassword());
        if (updatedUser != null) {
            // Return a positive response when user information is updated successfully.
            return ResponseHandler.successResponse(HttpStatus.OK, "User updated successfully", updatedUser);
        } else {
            // Return a negative response when user information update fails.
            return ResponseHandler.errorResponse(HttpStatus.BAD_REQUEST, "User update failed");
        }
    }

    // Endpoint for verifying a user's account using a verification code.
    @PostMapping("/verify/{userId}")
    public ResponseEntity<Object> verifyUser(@PathVariable UUID userId) {
        if (userService.verifyUser(userId)) {
            // Return a positive response when the verification code email is successfully
            // sent.
            return ResponseHandler.successResponse(HttpStatus.OK, "Verification code sent successfully",
                    new HashMap<>());
        } else {
            // Return a negative response when the verification code operation fails.
            return ResponseHandler.errorResponse(HttpStatus.BAD_REQUEST,
                    "User not found or verification code sending failed");
        }
    }

    // Endpoint for changing a user's account verification status.
    @PutMapping("/changeVerified")
    public ResponseEntity<Object> changeVerified(@RequestBody VerificationRequest verificationRequest) {
        if (userService.changeVerified(verificationRequest.getVerificationCode())) {
            // Return a positive response when the user's account is successfully verified.
            return ResponseHandler.successResponse(HttpStatus.OK, "User account verified successfully",
                    new HashMap<>());
        } else {
            // Return a negative response when the account verification operation fails.
            return ResponseHandler.errorResponse(HttpStatus.BAD_REQUEST, "User account verification failed");
        }
    }
}
