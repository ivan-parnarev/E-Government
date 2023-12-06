package com.egovernment.egovauth.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class KeyServiceTest {

    private static final String TEST_PUBLIC_KEY_PATH = "src/test/resources/testKeys/test-public.pem";
    private static final String TEST_PRIVATE_KEY_PATH = "src/test/resources/testKeys/test-private.pem";

    @InjectMocks
    private KeyService keyService;


    @Test
    void testGetPrivateKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        PrivateKey privateKey = this.keyService.getPrivateKey(TEST_PRIVATE_KEY_PATH);

        assertNotNull(privateKey);
    }

    @Test
    void testGetPublicKey() throws IOException {
        String publicKey = this.keyService.getPublicKey(TEST_PUBLIC_KEY_PATH);

        assertNotNull(publicKey);
    }

}
