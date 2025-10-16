package com.example.ElectricityMgmt.config;


import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Component
public class Auth {

    private static final SecureRandom random = new SecureRandom();
    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    // Separate maps for users and admins
    private static final Map<String, String> userTokens = new HashMap<>();
    private static final Map<String, String> adminTokens = new HashMap<>();
    private static final Map<String  , String> smeTokens = new HashMap<>();

    /** Generate a 12-character alphanumeric auth code for a user */
    public String generateUserAuthCode(String username) {
        String code = generateCode();
        userTokens.put(username, code);
        return code;
    }

    /** Generate a 12-character alphanumeric auth code for an admin */
    public String generateAdminAuthCode(String username) {
        String code = generateCode();
        adminTokens.put(username, code);
        return code;
    }

    /** Generate a 12-character alphanumeric auth code for an sme */
    public String generateSmeAuthCode(String username) {
        String code = generateCode();
        smeTokens.put(username, code);
        return code;
    }

    /** Validate if a given auth code matches the user's latest token */
    public boolean isValidUserCode(String username, String code) {
        return code != null && code.equals(userTokens.get(username));
    }

    /** Validate if a given auth code matches the admin's latest token */
    public boolean isValidAdminCode(String username, String code) {
        return code != null && code.equals(adminTokens.get(username));
    }

    /** Validate if a given auth code matches the sme's latest token */
    public boolean isValidSmeCode(String username, String code) {
        return code != null && code.equals(smeTokens.get(username));
    }

    /** Invalidate/remove the auth code for a user */
    public boolean invalidateUserCode(String username) {
        return userTokens.remove(username) != null;
    }

    /** Invalidate/remove the auth code for an admin */
    public boolean invalidateAdminCode(String username) {
        return adminTokens.remove(username) != null;
    }

    /** Invalidate/remove the sme code for an admin */
    public boolean invalidateSmeCode(String username) {
        return smeTokens.remove(username) != null;
    }

    /** Optional: Get the current token for a user (for testing) */
    public String getUserToken(String username) {
        return userTokens.get(username);
    }

    /** Optional: Get the current token for an admin (for testing) */
    public String getAdminToken(String username) {
        return adminTokens.get(username);
    }

    /** Optional: Get the current token for an sme (for testing) */
    public String getSmeToken(String username) {
        return smeTokens.get(username);
    }

    /** Private helper to generate random code */
    private String generateCode() {
        StringBuilder sb = new StringBuilder(12);
        for (int i = 0; i < 12; i++) {
            int index = random.nextInt(ALPHANUMERIC.length());
            sb.append(ALPHANUMERIC.charAt(index));
        }
        return sb.toString();
    }

    public boolean isValidCode(String username, String code) {
        return isValidUserCode(username, code) ||  isValidAdminCode(username, code) || isValidSmeCode(username, code);
    }
}