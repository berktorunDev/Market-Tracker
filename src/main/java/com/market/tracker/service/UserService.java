package com.market.tracker.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.market.tracker.dto.UserDTO;
import com.market.tracker.model.User;
import com.market.tracker.repository.UserRepository;
import com.market.tracker.util.api.EmailService;
import com.market.tracker.util.codeGeneration.CodeGenerator;
import com.market.tracker.util.mapper.MapperUtil;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final MapperUtil mapperUtil;
    private final EmailService emailService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CodeGenerator codeGenerator;

    /**
     * Constructor to initialize the UserService.
     *
     * @param userRepository  The UserRepository for accessing user data.
     * @param mapperUtil      The MapperUtil for entity-DTO conversions.
     * @param emailService    The EmailService for sending emails.
     * @param passwordEncoder The BCryptPasswordEncoder for password hashing.
     * @param codeGenerator   The CodeGenerator for generate code or token.
     */
    public UserService(UserRepository userRepository, MapperUtil mapperUtil,
            EmailService emailService, BCryptPasswordEncoder passwordEncoder, CodeGenerator codeGenerator) {
        this.userRepository = userRepository;
        this.mapperUtil = mapperUtil;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.codeGenerator = codeGenerator;
    }

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param userId The user's unique identifier.
     * @return The user's details as a UserDTO if found; otherwise, null.
     */
    public UserDTO getUser(UUID userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            return mapperUtil.convertToDTO(user, UserDTO.class);
        }

        return null;
    }

    /**
     * Registers a new user with the provided username, email, and password.
     * The user's creation timestamp is recorded, and the user is saved in the
     * repository.
     *
     * @param username User's username.
     * @param email    User's email address.
     * @param password User's password.
     * @return The newly registered user's details as a UserDTO.
     */
    public UserDTO register(String username, String email, String password) {
        User createdUser = new User(username, email, password);
        createdUser.setCreatedAt(LocalDateTime.now());
        createdUser.setUpdatedAt(LocalDateTime.now());
        userRepository.save(createdUser);
        return mapperUtil.convertToDTO(createdUser, UserDTO.class);
    }

    /**
     * Logs in a user with the provided username or email and password.
     * If the provided credentials are correct, the user is marked as active,
     * and their last login timestamp is updated.
     *
     * @param usernameOrEmail User's username or email.
     * @param password        User's password.
     * @return The user's details as a UserDTO if the login is successful;
     *         otherwise, null.
     */
    public UserDTO login(String usernameOrEmail, String password) {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            user.setActive(true);
            user.setLastLogin(LocalDateTime.now());
            userRepository.save(user);
            return mapperUtil.convertToDTO(user, UserDTO.class);
        }

        return null;
    }

    /**
     * Logs out a user with the given user ID. The user is marked as inactive,
     * and their last logout timestamp is recorded.
     *
     * @param userId User's unique identifier.
     */
    public void logout(UUID userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setActive(false);
            user.setLastLogout(LocalDateTime.now());
            userRepository.save(user);
        }
    }

    /**
     * Initiates a password reset process for a user with the specified email.
     * If the user is found, a reset token is generated and stored with an expiry
     * time.
     * A password reset email containing the token is sent to the user.
     *
     * @param email User's email address.
     * @return True if the password reset process is initiated successfully;
     *         otherwise, false.
     */
    public boolean forgotPassword(UUID userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            String resetToken = codeGenerator.generateCode();
            user.setResetToken(resetToken);
            user.setResetTokenExpiry(LocalDateTime.now().plusMinutes(15));
            userRepository.save(user);

            emailService.sendPasswordResetEmail(user.getEmail(), resetToken);
            return true;
        }

        return false;
    }

    /**
     * Changes a user's password using a reset token. The reset token must be valid,
     * and the new password is hashed before being saved.
     *
     * @param resetToken  The reset token received by the user.
     * @param newPassword The new password to set.
     * @return True if the password is changed successfully; otherwise, false.
     */
    public boolean changePassword(String resetToken, String newPassword) {
        User user = userRepository.findByResetToken(resetToken);

        if (user != null && LocalDateTime.now().isBefore(user.getResetTokenExpiry())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            user.setResetToken(null);
            user.setResetTokenExpiry(null);
            user.setUpdatedAt(LocalDateTime.now());
            userRepository.save(user);
            return true;
        }

        return false;
    }

    /**
     * Updates a user's information, such as username, email, or password.
     * The user's information is updated, and the changes are saved in the
     * repository.
     *
     * @param userId   User's unique identifier.
     * @param username New username (can be null).
     * @param email    New email (can be null).
     * @param password New password (can be null).
     * @return The updated user's details as a UserDTO if the update is successful;
     *         otherwise, null.
     */
    public UserDTO updateUser(UUID userId, String username, String email, String password) {
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            if (username != null) {
                user.setUsername(username);
            }

            if (email != null) {
                user.setEmail(email);
            }

            if (password != null) {
                user.setPassword(passwordEncoder.encode(password));
            }

            user.setUpdatedAt(LocalDateTime.now());
            userRepository.save(user);
            return mapperUtil.convertToDTO(user, UserDTO.class);
        }

        return null;
    }

    /**
     * Verifies a user's account using the verification code. If the user's account
     * is not already verified, the provided verification code is compared with the
     * one stored in the user's record. If they match and the verification code has
     * not expired, the user's account is marked as verified in the database.
     *
     * @param email The user's email address.
     * @return True if the user's account is successfully verified; otherwise,
     *         false.
     */
    public boolean verifyUser(UUID userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            String verificationCode = codeGenerator.generateCode();
            user.setVerificationCode(verificationCode);
            user.setVerificationCodeExpiry(LocalDateTime.now().plusMinutes(15));
            userRepository.save(user);

            emailService.sendVerificationCodeEmail(user.getEmail(), verificationCode);
            return true;
        }

        return false;
    }

    /**
     * Verifies a user's account using the verification code. If the user's account
     * is not already verified, the provided verification code is compared with
     * the one stored in the user's record. If they match and the verification code
     * has not expired, the user's account is marked as verified in the database.
     *
     * @param verificationCode The verification code sent to the user for account
     *                         verification.
     * @return True if the user's account is successfully verified; otherwise,
     *         false.
     */
    public boolean changeVerified(String verificationCode) {
        // Find the user with the provided verification code.
        User user = userRepository.findByVerificationCode(verificationCode);

        // Check if the user exists, the verification code is not expired, and the
        // account is not already verified.
        if (user != null && LocalDateTime.now().isBefore(user.getVerificationCodeExpiry()) && !user.isVerified()) {
            // Mark the user's account as verified.
            user.setVerified(true);
            user.setVerificationCode(null);
            user.setVerificationCodeExpiry(null);
            user.setUpdatedAt(LocalDateTime.now());
            userRepository.save(user);
            return true; // User's account is successfully verified.
        }

        return false; // User's account verification failed.
    }

}
