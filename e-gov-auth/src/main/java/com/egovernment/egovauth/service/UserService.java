package com.egovernment.egovauth.service;

import com.egovernment.egovauth.domain.entity.User;
import com.egovernment.egovauth.factory.userFactory.UserFactory;
import com.egovernment.egovauth.repository.UserRepository;
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

    private final UserFactory userFactory = new UserFactory();

    public Optional<User> findUserByUserPin(String hashedUserPin) {
        return this.userRepository.findByUserPin(hashedUserPin);
    }

    public void initTestUsers() throws NoSuchAlgorithmException {
        User firstTestUser = userFactory.createUser(generateHashedUserPin("00000001"),
                TestUserData.TEST_USER_FIRST_NAME,
                TestUserData.TEST_USER_MIDDLE_NAME,
                TestUserData.TEST_USER_LAST_NAME,
                TestUserData.TEST_USER_ADDRESS);
        User secondTestUser = userFactory.createUser(generateHashedUserPin("00000002"),
                TestUserData.TEST_USER_FIRST_NAME,
                TestUserData.TEST_USER_MIDDLE_NAME,
                TestUserData.TEST_USER_LAST_NAME,
                TestUserData.TEST_USER_ADDRESS);
        User thirdTestUser = userFactory.createUser(generateHashedUserPin("00000003"),
                TestUserData.TEST_USER_FIRST_NAME,
                TestUserData.TEST_USER_MIDDLE_NAME,
                TestUserData.TEST_USER_LAST_NAME,
                TestUserData.TEST_USER_ADDRESS);

        this.userRepository.saveAll(List.of(firstTestUser, secondTestUser, thirdTestUser));
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
