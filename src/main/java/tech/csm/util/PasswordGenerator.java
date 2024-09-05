package tech.csm.util;

import org.springframework.stereotype.Component;

@Component
public class PasswordGenerator {


    // Instance method to generate password
    public String generatePassword(String userName, String userPhone) {
        // Example logic to combine user data and generate a password
        String base = userName + userPhone;
        String hashedBase = hashString(base); // Hash the base for complexity
        return hashedBase.substring(0, 12); // Adjust length as needed
    }

    // Method to hash the string (simplified example)
    private String hashString(String input) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error hashing string", e);
        }
    }

}
