package com.jungle.board.security.auth;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class HashCrypto implements CryptoProvider {
    public HashCrypto() {
        
    }

    @Override
    public String encrypt(String plainText) {
        try {
            var digest = MessageDigest.getInstance("SHA-256");
            digest.update(plainText.getBytes(StandardCharsets.UTF_8));
            byte[] hashBytes = digest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte hashByte : hashBytes) {
                sb.append(String.format("%02x", hashByte));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    @Override
    public String decrypt(String encrytedText) {
        throw new IllegalArgumentException("Hash cannot be decrypted.");
    }
}
