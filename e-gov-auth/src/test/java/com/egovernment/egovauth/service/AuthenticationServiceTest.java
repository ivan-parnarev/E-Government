package com.egovernment.egovauth.service;

import com.egovernment.egovauth.domain.entity.User;
import com.egovernment.egovauth.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    private static final String FIRST_NAME = "George";
    private static final String USER_PIN = "testHashedUserPin";
    private static final String TEST_JWT = "testToken";

    @Mock
    private UserService userService;
    @Mock
    private JwtService jwtService;
    @InjectMocks
    private AuthenticationService authenticationService;

    private User testUser;

    @BeforeEach
    void setup() {
            testUser = User.builder()
                    .firstName(FIRST_NAME)
                    .userPin(USER_PIN)
                    .build();
    }

    @Test
    void testAuthenticateUser_UserExist_TokenReturned() throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        Mockito.when(this.userService.findUserByUserPin(USER_PIN)).thenReturn(Optional.of(testUser));
        Mockito.when(this.jwtService.createJwtSignedHMAC(testUser)).thenReturn(TEST_JWT);

        String token = authenticationService.authenticateUser(USER_PIN);

        Assertions.assertNotNull(token);
        Assertions.assertEquals(TEST_JWT, token);
    }

    @Test
    void testAuthenticateUser_UserNonExistent_ErrorThrown() {
        assertThrows(UserNotFoundException.class, () -> {
            authenticationService.authenticateUser(USER_PIN);
        });
    }
}
