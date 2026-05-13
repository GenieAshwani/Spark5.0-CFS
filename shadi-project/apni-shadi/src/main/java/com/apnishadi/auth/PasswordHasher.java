package com.apnishadi.auth;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Component
public class PasswordHasher {
	private static final int ITERATIONS = 120_000;
	private static final int KEY_LENGTH = 256;
	private static final SecureRandom RANDOM = new SecureRandom();

	public String hash(String password) {
		byte[] salt = new byte[16];
		RANDOM.nextBytes(salt);
		byte[] hash = pbkdf2(password.toCharArray(), salt);
		return ITERATIONS + ":" + encode(salt) + ":" + encode(hash);
	}

	public boolean matches(String password, String storedHash) {
		String[] parts = storedHash.split(":");
		if (parts.length != 3) {
			return false;
		}
		byte[] salt = Base64.getDecoder().decode(parts[1]);
		byte[] expected = Base64.getDecoder().decode(parts[2]);
		byte[] actual = pbkdf2(password.toCharArray(), salt);
		return java.security.MessageDigest.isEqual(expected, actual);
	}

	private static byte[] pbkdf2(char[] password, byte[] salt) {
		try {
			PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
			return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException | InvalidKeySpecException exception) {
			throw new IllegalStateException("Password hashing failed", exception);
		}
	}

	private static String encode(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
	}
}
