package com.jungle.board.application;

import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.jungle.board.common.WebException;
import com.jungle.board.domain.user.User;
import com.jungle.board.domain.user.UserRepository;
import com.jungle.board.security.auth.CryptoProvider;
import com.jungle.board.security.jwt.JwtTokenProvider;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final CryptoProvider cryptoProvider;
    private final JwtTokenProvider jwtTokenProvider;
    public AuthServiceImpl(UserRepository userRepository, CryptoProvider cryptoProvider, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.cryptoProvider = cryptoProvider;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String signIn(String nickname, String password) {
        var user = userRepository.findByNickname(nickname).orElseThrow(() -> {
            throw new WebException(HttpStatus.BAD_REQUEST, "Incorrect ID or Password");
        });

        password = cryptoProvider.encrypt(password + user.getSalt());
        if (!password.equals(user.getPassword())) {
            throw new WebException(HttpStatus.BAD_REQUEST, "Incorrect ID or Password");
        }

        var token = this.jwtTokenProvider.createAccessToken(user.getId(), user.getEmail());
        return token;
    }

    @Override
    public void hashAndSaltPassword(User user) {
        user.setSalt(generateRandomString(8));
        user.changePassword(cryptoProvider.encrypt(user.getPassword() + user.getSalt()));
    }

    private static String generateRandomString(int length) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[length];
        secureRandom.nextBytes(randomBytes);

        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }

    @Override
    public void verifyUser(Long userId, String token) {
        var claims = this.jwtTokenProvider.parseAccessToken(token);
        if (!claims.getPayload().getSubject().equals(userId.toString())) {
            throw new WebException("Invaild token");
        }
    }
}