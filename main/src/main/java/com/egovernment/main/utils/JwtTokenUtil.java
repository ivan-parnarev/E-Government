package com.egovernment.main.utils;

import com.egovernment.main.client.AuthenticationClient;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    private final AuthenticationClient authenticationClient;

    public String getPublicKeyString() {
        return Objects.requireNonNull(authenticationClient.getPublicKey()
                .getBody()).getPublicKey();
    }

    public Boolean validateTokenForAdminRole(String token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Jws<Claims> jwtClaims = getJwtClaims(token);
        return (Boolean) jwtClaims.getBody().get("isAdmin");
    }

    private PublicKey createPublicKeyObject(String key) throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey publicKey = kf.generatePublic(keySpec);
        return publicKey;
    }

    private Jws<Claims> getJwtClaims(String token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        token = token.replace("Bearer ", "");
        String publicKeyString = getPublicKeyString();
        Jws<Claims> jwtClaims = extractClaimsFromToken(publicKeyString, token);
        return jwtClaims;
    }

    public <T> String extractToken(ResponseEntity<T> responseFromClient) {
        String token = responseFromClient.getHeaders().get("Authorization").get(0);
        token = token.replace("Bearer ", "");
        return token;
    }

    public Jws<Claims> extractClaimsFromToken(String publicKeyString, String token) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PublicKey publicKey = createPublicKeyObject(publicKeyString);
        Jws<Claims> jwtClaims = Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(token);
        return jwtClaims;
    }
}
