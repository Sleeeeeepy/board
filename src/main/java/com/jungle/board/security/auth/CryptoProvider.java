package com.jungle.board.security.auth;

public interface CryptoProvider {
    String encrypt(String plainText);
    String decrypt(String encrytedText);
}
