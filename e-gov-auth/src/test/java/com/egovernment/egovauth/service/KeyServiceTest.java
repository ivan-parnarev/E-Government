package com.egovernment.egovauth.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class KeyServiceTest {

    private static final String TEST_PRIVATE_KEY = "testPrivateKey";
    private static final String TEST_PUBLIC_KEY = "testPublicKey";
    private static final String TEST_PUBLIC_KEY_PATH = "src/test/resources/testKeys/test-public.pem";
    private static final String TEST_PRIVATE_KEY_PATH = "src/test/resources/testKeys/test-private.pem";

    @InjectMocks
    private KeyService keyService;

    @Test
    void testExtractPrivateKey_KeyExtracted() throws IOException {
        String result = keyService.extractPrivateKey(TEST_PRIVATE_KEY_PATH);

        assertNotNull(result);
        assertEquals(TEST_PRIVATE_KEY, result);
    }

    @Test
    void testExtractPublicKey_KeyExtracted() throws IOException {
        String result = keyService.extractPublicKey(TEST_PUBLIC_KEY_PATH);

        assertNotNull(result);
        assertEquals(TEST_PUBLIC_KEY, result);
    }



}
