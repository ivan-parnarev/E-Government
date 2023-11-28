package com.egovernment.egovauth.service;
import com.egovernment.egovauth.domain.entity.User;
import com.egovernment.egovauth.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;

    private final JwtService jwtService;

    public String authenticateUser(String userPin) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        Optional<User> optionalUser = userService.findUserByUserPin(userPin);

        if (optionalUser.isPresent()) {
            return this.jwtService.createJwtSignedHMAC(optionalUser.get());
        } else {
            throw new UserNotFoundException();
        }
    }
}
