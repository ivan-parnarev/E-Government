package com.egovernment.egovauth.service;

import com.egovernment.egovauth.domain.entity.User;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final KeyService keyService;

    public String createJwtSignedHMAC(User user) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {

        PrivateKey privateKey = getPrivateKey();

        Instant now = Instant.now();
        String jwtToken = Jwts.builder()
                .claim("firstName", user.getFirstName())
                .claim("middleName", user.getMiddleName())
                .claim("lastName", user.getLastName())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(5L, ChronoUnit.MINUTES)))
                .signWith(privateKey)
                .compact();

        return jwtToken;
    }

    private PrivateKey getPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        String pemFilePath = "src/main/resources/keys/private.pem";
        String rsaPrivateKey = this.keyService.extractPrivateKey(pemFilePath);
        Security.addProvider(new BouncyCastleProvider());

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(rsaPrivateKey));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey privKey = kf.generatePrivate(keySpec);
        return privKey;
    }
}
