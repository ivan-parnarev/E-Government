package com.example.egovernmentaccesscontrol.service;

import com.example.egovernmentaccesscontrol.domain.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class KeyServiceTest {

    private KeyService keyService;

    @BeforeEach
    void setUp() {
        keyService = new KeyService();
    }

    @Test
    void extractAddress_ValidToken_ReturnsAddress() {

        try {
            Address result = keyService.extractAddress();
            assertNotNull(result);
            assertEquals("Софийска", result.getRegion());
        } catch (Exception e) {
            fail("Exception should not be thrown for valid token");
        }
    }

}
