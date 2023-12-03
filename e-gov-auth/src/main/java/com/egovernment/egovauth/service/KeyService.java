package com.egovernment.egovauth.service;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Service
public class KeyService {

    public PrivateKey getPrivateKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String keyPath = "src/main/resources/keys/private.pem";
        String rsaPrivateKey = extractPrivateKey(keyPath);
        Security.addProvider(new BouncyCastleProvider());

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(rsaPrivateKey));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey privKey = kf.generatePrivate(keySpec);
        return privKey;
    }

    public String getPublicKey() throws IOException {
        String keyPath = "src/main/resources/keys/public.pem";
        String rsaPublicKey = extractPublicKey(keyPath);

        return rsaPublicKey;
    }
    public String extractPrivateKey(String path) throws IOException {
        byte[] privateKeyBytes = Files.readAllBytes(Paths.get(path));
        String privateKey = new String(privateKeyBytes);
        privateKey = privateKey
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s+", "");

        return privateKey;
    }

    public String extractPublicKey(String path) throws IOException {
        byte[] publicKeyBytes = Files.readAllBytes(Paths.get(path));
        String publicKey = new String(publicKeyBytes);
        publicKey = publicKey
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");

        return publicKey;
    }
}
