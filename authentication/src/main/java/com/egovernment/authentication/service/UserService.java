package com.egovernment.authentication.service;
import com.egovernment.authentication.domain.entity.*;
import com.egovernment.authentication.domain.enums.Country;
import com.egovernment.authentication.factory.userFactory.UserFactory;
import com.egovernment.authentication.repository.*;
import com.egovernment.authentication.utils.TestUserData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final String HASH_ALGORITHM = "SHA-256";
    private final UserRepository userRepository;

    private final RegionRepository regionRepository;

    private final MunicipalityRepository municipalityRepository;

    private final CityRepository cityRepository;

    private final AddressRepository addressRepository;

    private final UserFactory userFactory = new UserFactory();

    public Optional<User> findUserByUserPin(String userPin) throws NoSuchAlgorithmException {
        String hashedUserPin = generateHashedUserPin(userPin);
        return this.userRepository.findByUserPin(hashedUserPin);
    }

    public void initTestUsers() throws NoSuchAlgorithmException {
        Region regionSof = this.regionRepository.findByName(TestUserData.TEST_USER_REGION_SOF);
        Region regionPld = this.regionRepository.findByName(TestUserData.TEST_USER_REGION_PLD);

        Municipality municipalitySof = this.municipalityRepository.findByName(TestUserData.TEST_USER_MUNICIPALITY_SOF);
        Municipality municipalityPld = this.municipalityRepository.findByName(TestUserData.TEST_USER_MUNICIPALITY_PLD);

        City citySof = this.cityRepository.findByName(TestUserData.TEST_USER_CITY_SOF);
        City cityPld = this.cityRepository.findByName(TestUserData.TEST_USER_CITY_PLD);

        Address addressSof = Address.builder()
                .country(Country.България)
                .region(regionSof)
                .postcode(regionSof.getPostcode())
                .municipality(municipalitySof)
                .city(citySof)
                .build();

        Address addressPld = Address.builder()
                .country(Country.България)
                .region(regionPld)
                .postcode(regionPld.getPostcode())
                .municipality(municipalityPld)
                .city(cityPld)
                .build();

        this.addressRepository.save(addressSof);
        this.addressRepository.save(addressPld);

        User adminTestUser = userFactory.createUser(generateHashedUserPin("1111111111"),
                TestUserData.TEST_ADMIN_NAME,
                TestUserData.TEST_ADMIN_NAME,
                TestUserData.TEST_ADMIN_NAME,
                addressSof);
        adminTestUser.setAdmin(true);

        User firstTestUser = userFactory.createUser(generateHashedUserPin("2222222222"),
                TestUserData.TEST_USER_FIRST_NAME,
                TestUserData.TEST_USER_MIDDLE_NAME,
                TestUserData.TEST_USER_LAST_NAME,
                addressSof);
        User secondTestUser = userFactory.createUser(generateHashedUserPin("3333333333"),
                TestUserData.TEST_USER_FIRST_NAME,
                TestUserData.TEST_USER_MIDDLE_NAME,
                TestUserData.TEST_USER_LAST_NAME,
                addressPld);
        User thirdTestUser = userFactory.createUser(generateHashedUserPin("4444444444"),
                TestUserData.TEST_USER_FIRST_NAME,
                TestUserData.TEST_USER_MIDDLE_NAME,
                TestUserData.TEST_USER_LAST_NAME,
                addressPld);

        this.userRepository.saveAll(List.of(adminTestUser, firstTestUser, secondTestUser, thirdTestUser));
    }

    private String generateHashedUserPin(String userPin) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
        byte[] hashedBytes = md.digest(userPin.getBytes());

        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
