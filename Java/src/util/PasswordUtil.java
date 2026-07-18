package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Simple password hashing utility using SHA-256.
 * In production, use bcrypt, scrypt, or Argon2.
 */
public class PasswordUtil {

    private PasswordUtil() {
    }

    public static String hashPassword(String password) {

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);

        } catch (NoSuchAlgorithmException e) {

            throw new RuntimeException("SHA-256 not available", e);

        }

    }

    public static boolean checkPassword(String password, String hashed) {

        return hashPassword(password).equals(hashed);

    }

}
