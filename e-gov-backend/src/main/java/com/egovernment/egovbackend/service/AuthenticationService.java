package com.egovernment.egovbackend.service;

import com.egovernment.egovbackend.client.AuthenticationClient;
import com.egovernment.egovbackend.domain.dto.auth.AuthRequest;
import com.egovernment.egovbackend.domain.dto.auth.AuthResponse;
import com.egovernment.egovbackend.domain.dto.auth.FeignAuthResponse;
import com.egovernment.egovbackend.exceptions.UserNotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationClient authenticationClient;

    public AuthResponse authenticateUser(String hashedUserPin) throws NoSuchAlgorithmException, InvalidKeySpecException {

        AuthRequest authRequest = AuthRequest.builder()
                .userPin(hashedUserPin)
                .build();

        ResponseEntity<FeignAuthResponse> responseFromClient = this.authenticationClient.authenticateUser(authRequest);
        HttpStatusCode statusCode = responseFromClient.getStatusCode();

        if (statusCode.is2xxSuccessful()) {
            FeignAuthResponse body = responseFromClient.getBody();
            String token = Objects.requireNonNull(responseFromClient.getHeaders().get("Authorization")).get(0);
            token = token.replace("Bearer ", "");
            PublicKey publicKey = createPublicKey(Objects.requireNonNull(body).getPublicKey());

            Jws<Claims> jwtClaims = Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(token);

            boolean isAdmin = (boolean) jwtClaims.getBody().get("isAdmin");

            if (isAdmin) {
                AuthResponse response = AuthResponse.builder()
                        .isAdmin((Boolean) jwtClaims.getBody().get("isAdmin"))
                        .build();
                return response;
            } else {
                //TODO: extract the address from the claims
                //TODO: call Access Control Service Via FeignClient
                //TODO: return AuthResponse with the filtered Campaigns
                return null;
            }
        } else {
            throw new UserNotFoundException();
        }
    }

    private PublicKey createPublicKey(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey publicKey = kf.generatePublic(keySpec);
        return publicKey;
    }
}
