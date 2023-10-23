package com.market.tracker.util.codeGeneration;

import java.security.SecureRandom;

import org.springframework.stereotype.Component;

/**
 * The CodeGenerator class is responsible for generating random codes.
 */
@Component
public class CodeGenerator {
    // A string containing the characters allowed in the generated codes.
    private static final String ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    // The length of the generated code.
    private static final int CODE_LENGTH = 6;

    // A secure random number generator for generating random indices.
    private final SecureRandom secureRandom = new SecureRandom();

    /**
     * Generate a random code of the specified length using allowed characters.
     *
     * @return A randomly generated code.
     */
    public String generateCode() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);

        for (int i = 0; i < CODE_LENGTH; i++) {
            int randomIndex = secureRandom.nextInt(ALLOWED_CHARACTERS.length());
            char randomChar = ALLOWED_CHARACTERS.charAt(randomIndex);
            code.append(randomChar);
        }

        return code.toString();
    }
}
