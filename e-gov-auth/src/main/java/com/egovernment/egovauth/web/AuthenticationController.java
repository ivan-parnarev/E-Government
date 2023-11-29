package com.egovernment.egovauth.web;

import com.egovernment.egovauth.domain.auth.AuthenticationRequest;
import com.egovernment.egovauth.domain.auth.AuthenticationResponse;
import com.egovernment.egovauth.exceptions.UserNotFoundException;
import com.egovernment.egovauth.service.AuthenticationService;
import com.egovernment.egovauth.web.path.ApiPaths;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping(value = ApiPaths.BASE_API_PATH)
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(ApiPaths.AUTH_PATH)
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody AuthenticationRequest authRequest) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        try {
            String token = this.authenticationService.authenticateUser(authRequest.getUserPin());

            AuthenticationResponse response = AuthenticationResponse.builder()
                    .publicKey(authenticationService.getPublicKey()).build();

            return ResponseEntity.ok().header("Authorization", "Bearer " + token)
                    .body(response);
        } catch (UserNotFoundException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
