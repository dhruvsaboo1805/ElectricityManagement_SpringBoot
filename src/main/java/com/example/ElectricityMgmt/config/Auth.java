package com.example.ElectricityMgmt.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Data
public class Auth {

    private static final SecureRandom random = new SecureRandom();
    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    // Use non-static, thread-safe maps for a Spring singleton bean
    private final Map<String, String> userTokens = new ConcurrentHashMap<>();
    private final Map<String, String> adminTokens = new ConcurrentHashMap<>();
    private final Map<String, String> smeTokens = new ConcurrentHashMap<>();

    // --- Token Generation ---
    public String generateUserAuthCode(String username) {
        String code = generateCode();
        userTokens.put(username, code);
        System.out.println("user tokens map");
        for(Map.Entry<String , String> mp : userTokens.entrySet()) {
            System.out.println(mp.getKey() + ": " + mp.getValue());
        }
        return code;
    }

    public String generateAdminAuthCode(String username) {
        String code = generateCode();
        adminTokens.put(username, code);
        System.out.println("admin tokens map");
        for(Map.Entry<String , String> mp : adminTokens.entrySet()) {
            System.out.println(mp.getKey() + ": " + mp.getValue());
        }
        return code;
    }

    public String generateSmeAuthCode(String username) {
        String code = generateCode();
        smeTokens.put(username, code);
        System.out.println("smetokens map");
        for(Map.Entry<String , String> mp : smeTokens.entrySet()) {
            System.out.println(mp.getKey() + ": " + mp.getValue());
        }
        return code;
    }

    // --- Token Validation ---
    public boolean isValidUserCode(String username, String code) {
        return code != null && code.equals(userTokens.get(username));
    }

    public boolean isValidAdminCode(String username, String code) {
        return code != null && code.equals(adminTokens.get(username));
    }

    public boolean isValidSmeCode(String username, String code) {
        return code != null && code.equals(smeTokens.get(username));
    }

    // A general validation method is useful
    public boolean isValidCode(String username, String code) {
        return isValidUserCode(username, code) || isValidAdminCode(username, code) || isValidSmeCode(username, code);
    }

    // --- Token Invalidation ---
    public void invalidateUserCode(String username) {
        userTokens.remove(username);
    }

    public void invalidateAdminCode(String username) {
        adminTokens.remove(username);
    }

    public void invalidateSmeCode(String username) {
        smeTokens.remove(username);
    }

    // --- Helper Methods ---
    private String generateCode() {
        StringBuilder sb = new StringBuilder(12);
        for (int i = 0; i < 12; i++) {
            sb.append(ALPHANUMERIC.charAt(random.nextInt(ALPHANUMERIC.length())));
        }
        return sb.toString();
    }


}