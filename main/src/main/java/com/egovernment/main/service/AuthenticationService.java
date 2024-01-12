package com.egovernment.main.service;

import com.egovernment.main.client.AuthenticationClient;
import com.egovernment.main.domain.dto.auth.AuthRequest;
import com.egovernment.main.domain.dto.auth.AuthResponse;
import com.egovernment.main.domain.dto.auth.FeignAuthResponse;
import com.egovernment.main.exceptions.UserNotFoundException;
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
            Boolean isAdmin = checkIsUserAdmin(Objects.requireNonNull(responseFromClient));

            if (isAdmin) {
                AuthResponse response = AuthResponse.builder()
                        .isAdmin(true)
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

    private Boolean checkIsUserAdmin(ResponseEntity<FeignAuthResponse> responseFromClient) throws NoSuchAlgorithmException, InvalidKeySpecException {
        FeignAuthResponse body = responseFromClient.getBody();
        String token = Objects.requireNonNull(responseFromClient.getHeaders().get("Authorization")).get(0);
        token = token.replace("Bearer ", "");
        Jws<Claims> jwtClaims = extractClaimsFromToken(body, token);

        return (boolean) jwtClaims.getBody().get("isAdmin");
    }

    private Jws<Claims> extractClaimsFromToken(FeignAuthResponse body, String token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PublicKey publicKey = createPublicKey(Objects.requireNonNull(body).getPublicKey());

        Jws<Claims> jwtClaims = Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token);
        return jwtClaims;
    }
}
