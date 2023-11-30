package com.egovernment.egovauth.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
class KeyService {
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
