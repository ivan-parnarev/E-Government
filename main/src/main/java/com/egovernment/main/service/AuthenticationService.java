package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.client.AccessControlClient;
import com.egovernment.egovbackend.client.AuthenticationClient;
import com.egovernment.egovbackend.domain.dto.auth.AuthRequest;
import com.egovernment.egovbackend.domain.dto.auth.AuthResponse;
import com.egovernment.egovbackend.domain.dto.auth.FeignAuthResponse;
import com.egovernment.egovbackend.exceptions.UserNotFoundException;
import com.egovernment.egovbackend.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final ModelMapper modelMapper;
    private final AccessControlClient accessControlClient;
    private final AuthenticationClient authenticationClient;
    private final JwtTokenUtil jwtTokenUtil;

    public ResponseEntity<AuthResponse> authenticateUser(AuthRequest authRequest) throws NoSuchAlgorithmException, InvalidKeySpecException {

        ResponseEntity<FeignAuthResponse> responseFromClient = this.authenticationClient.authenticateUser(authRequest);
        HttpStatusCode statusCode = responseFromClient.getStatusCode();

        if (statusCode.is2xxSuccessful()) {
            String token = this.jwtTokenUtil.extractToken(responseFromClient);
            Boolean isAdmin = this.jwtTokenUtil.validateAdminToken(token);

            if (isAdmin) {
                AuthResponse response = AuthResponse.builder()
                        .isAdmin(true)
                        .build();
                return ResponseEntity.status(HttpStatus.OK)
                        .header("Authorization", "Bearer " + token).body(response);
            } else {
                //TODO: extract the address from the claims
                //TODO: call Access Control Service Via FeignClient
                //TODO: return AuthResponse with the filtered Campaigns
                AuthResponse response = AuthResponse.builder()
                        .build();
                return ResponseEntity.status(HttpStatus.OK)
                        .header("Authorization", "Bearer " + token).body(response);
            }
        } else {
            throw new UserNotFoundException();
        }
    }
}
