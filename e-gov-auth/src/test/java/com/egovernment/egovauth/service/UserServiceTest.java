package com.egovernment.egovauth.service;

import com.egovernment.egovauth.domain.entity.Region;
import com.egovernment.egovauth.domain.entity.User;
import com.egovernment.egovauth.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private static final String FIRST_NAME = "George";
    private static final String USER_PIN = "testHashedUserPin";
    private static final String TEST_REGION_NAME = "Софийска";
    private static final String TEST_REGION_POSTCODE = "1000";

    @Mock
    private UserRepository userRepository;
    @Mock
    private RegionRepository regionRepository;
    @Mock
    private MunicipalityRepository municipalityRepository;
    @Mock
    private CityRepository cityRepository;
    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private UserService userService;
    private User testUser;

    private Region testRegion;

    @BeforeEach
    void setup() {
        testUser = User.builder()
                .firstName(FIRST_NAME)
                .userPin(USER_PIN)
                .build();

        testRegion = Region.builder().name(TEST_REGION_NAME)
                .postcode(TEST_REGION_POSTCODE)
                .build();
    }

    @Test
    void testFindUserByUserPinUserReturned() {
        when(this.userRepository.findByUserPin(USER_PIN))
                .thenReturn(Optional.of(testUser));

        Optional<User> result = this.userService.findUserByUserPin(USER_PIN);

        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get().getFirstName(), FIRST_NAME);
        Assertions.assertEquals(result.get().getUserPin(), USER_PIN);
    }

    @Test
    void testInitTestUsers() throws NoSuchAlgorithmException {
        when(this.regionRepository.findByName(TEST_REGION_NAME))
                .thenReturn(testRegion);

        userService.initTestUsers();

        verify(this.userRepository).saveAll(any());
        verify(this.userRepository, times(1)).saveAll(any());
    }
}
