package com.market.tracker.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.market.tracker.model.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsernameOrEmail(String username, String email);

    User findByResetToken(String resetToken);

    User findByVerificationCode(String verificationCode);

}
