package com.egovernment.egovauth.service;

import com.egovernment.egovauth.domain.entity.*;
import com.egovernment.egovauth.domain.enums.Country;
import com.egovernment.egovauth.factory.userFactory.UserFactory;
import com.egovernment.egovauth.repository.*;
import com.egovernment.egovauth.utils.TestUserData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final RegionRepository regionRepository;

    private final MunicipalityRepository municipalityRepository;

    private final CityRepository cityRepository;

    private final AddressRepository addressRepository;

    private final UserFactory userFactory = new UserFactory();

    public Optional<User> findUserByUserPin(String hashedUserPin) {
        return this.userRepository.findByUserPin(hashedUserPin);
    }

    public void initTestUsers() throws NoSuchAlgorithmException {
        Region region = this.regionRepository.findByName(TestUserData.TEST_USER_REGION);

        Municipality municipality = this.municipalityRepository.findByName(TestUserData.TEST_USER_MUNICIPALITY);

        City city = this.cityRepository.findByName(TestUserData.TEST_USER_CITY);

        Address address = Address.builder()
                .country(Country.България)
                .region(region)
                .municipality(municipality)
                .city(city)
                .build();

        this.addressRepository.save(address);

        User adminTestUser = userFactory.createUser(generateHashedUserPin("00000000"),
                TestUserData.TEST_ADMIN_NAME,
                TestUserData.TEST_ADMIN_NAME,
                TestUserData.TEST_ADMIN_NAME,
                address);
        adminTestUser.setAdmin(true);

        User firstTestUser = userFactory.createUser(generateHashedUserPin("00000001"),
                TestUserData.TEST_USER_FIRST_NAME,
                TestUserData.TEST_USER_MIDDLE_NAME,
                TestUserData.TEST_USER_LAST_NAME,
                address);
        User secondTestUser = userFactory.createUser(generateHashedUserPin("00000002"),
                TestUserData.TEST_USER_FIRST_NAME,
                TestUserData.TEST_USER_MIDDLE_NAME,
                TestUserData.TEST_USER_LAST_NAME,
                address);
        User thirdTestUser = userFactory.createUser(generateHashedUserPin("00000003"),
                TestUserData.TEST_USER_FIRST_NAME,
                TestUserData.TEST_USER_MIDDLE_NAME,
                TestUserData.TEST_USER_LAST_NAME,
                address);

        this.userRepository.saveAll(List.of(adminTestUser, firstTestUser, secondTestUser, thirdTestUser));
    }

    private String generateHashedUserPin(String userPin) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(userPin.getBytes());

        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
