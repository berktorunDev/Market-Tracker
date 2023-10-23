package com.market.tracker.util.api;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * Sends a password reset email to the specified email address with the given
     * reset token.
     *
     * @param to         The recipient's email address.
     * @param resetToken The password reset token.
     */
    public void sendPasswordResetEmail(String to, String resetToken) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject("Password Reset Request");
            helper.setText("Your password reset token: " + resetToken);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            // Handle exception, e.g., log or throw a custom exception
        }
    }

    /**
     * Sends an email containing the account verification code to the specified
     * email address.
     *
     * @param to               The recipient's email address.
     * @param verificationCode The account verification code.
     */
    public void sendVerificationCodeEmail(String to, String verificationCode) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject("Account Verification Code");
            helper.setText("Your verification code: " + verificationCode);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            // Handle exception, e.g., log or throw a custom exception
        }
    }
}
