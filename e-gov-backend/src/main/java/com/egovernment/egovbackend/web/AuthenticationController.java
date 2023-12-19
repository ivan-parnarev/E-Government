package com.egovernment.egovbackend.web;

import com.egovernment.egovbackend.domain.dto.auth.AuthRequest;
import com.egovernment.egovbackend.domain.dto.auth.AuthResponse;
import com.egovernment.egovbackend.domain.dto.auth.FeignAuthResponse;
import com.egovernment.egovbackend.service.AuthenticationService;
import com.egovernment.egovbackend.web.path.ApiPaths;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiPaths.BASE_API_PATH)
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody AuthRequest authRequest) throws NoSuchAlgorithmException, InvalidKeySpecException {

        AuthResponse authResponse = authenticationService.authenticateUser(authRequest.getUserPin());

        if (authResponse == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(authResponse);
    }
}
