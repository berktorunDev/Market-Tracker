package com.market.tracker.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserDTO {
    private UUID id;

    private String username;

    private String email;

    private boolean isVerified;

    private boolean isActive;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime lastLogin;

    private LocalDateTime lastLogout;

    private String resetToken;

    private LocalDateTime resetTokenExpiry;

    private String verificationCode;

    private LocalDateTime verificationCodeExpiry;

    public UserDTO() {
    }

    public UserDTO(UUID id, String username, String email, boolean isVerified, boolean isActive,
            LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime lastLogin, LocalDateTime lastLogout,
            String resetToken, LocalDateTime resetTokenExpiry, String verificationCode,
            LocalDateTime verificationCodeExpiry) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.isVerified = isVerified;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lastLogin = lastLogin;
        this.lastLogout = lastLogout;
        this.resetToken = resetToken;
        this.resetTokenExpiry = resetTokenExpiry;
        this.verificationCode = verificationCode;
        this.verificationCodeExpiry = verificationCodeExpiry;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public LocalDateTime getLastLogout() {
        return lastLogout;
    }

    public void setLastLogout(LocalDateTime lastLogout) {
        this.lastLogout = lastLogout;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public LocalDateTime getResetTokenExpiry() {
        return resetTokenExpiry;
    }

    public void setResetTokenExpiry(LocalDateTime resetTokenExpiry) {
        this.resetTokenExpiry = resetTokenExpiry;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public LocalDateTime getVerificationCodeExpiry() {
        return verificationCodeExpiry;
    }

    public void setVerificationCodeExpiry(LocalDateTime verificationCodeExpiry) {
        this.verificationCodeExpiry = verificationCodeExpiry;
    }

}