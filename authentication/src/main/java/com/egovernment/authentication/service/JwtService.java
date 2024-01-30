package com.egovernment.authentication.service;
import com.egovernment.authentication.domain.entity.User;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${keyPaths.privateKeyPath}")
    private String privateKeyPath;
    private final KeyService keyService;
    private final LocationService locationService;
    private static final long ADMIN_TOKEN_EXPIRATION = 120L;
    private static final long USER_TOKEN_EXPIRATION = 10L;

    String createJwtSignedHMAC(User user) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {

        PrivateKey privateKey = this.keyService.getPrivateKey(privateKeyPath);

        long tokenExpiration;
        if (user.isAdmin()) {
            tokenExpiration = ADMIN_TOKEN_EXPIRATION;
        } else {
            tokenExpiration = USER_TOKEN_EXPIRATION;
        }

        Instant now = Instant.now();
        String jwtToken = Jwts.builder()
                .claim("firstName", user.getFirstName())
                .claim("middleName", user.getMiddleName())
                .claim("lastName", user.getLastName())
                .claim("isAdmin", user.isAdmin())
                .claim("address", this.locationService.mapAddressToDto(user.getAddress()))
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(tokenExpiration, ChronoUnit.MINUTES)))
                .signWith(privateKey)
                .compact();

        return jwtToken;
    }
}
