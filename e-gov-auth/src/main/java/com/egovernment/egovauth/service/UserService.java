package com.egovernment.egovauth.service;

import com.egovernment.egovauth.domain.entity.User;
import com.egovernment.egovauth.factory.userFactory.UserFactory;
import com.egovernment.egovauth.repository.UserRepository;
import com.egovernment.egovauth.utils.TestUserData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserFactory userFactory = new UserFactory();

    public void initTestUsers() {
        User firstTestUser = userFactory.createUser(TestUserData.TEST_USER_FIRST_NAME,
                TestUserData.TEST_USER_MIDDLE_NAME,
                TestUserData.TEST_USER_LAST_NAME,
                "00000001", TestUserData.TEST_USER_ADDRESS);
        User secondTestUser = userFactory.createUser(TestUserData.TEST_USER_FIRST_NAME,
                TestUserData.TEST_USER_MIDDLE_NAME,
                TestUserData.TEST_USER_LAST_NAME,
                "00000002", TestUserData.TEST_USER_ADDRESS);
        User thirdTestUser = userFactory.createUser(TestUserData.TEST_USER_FIRST_NAME,
                TestUserData.TEST_USER_MIDDLE_NAME,
                TestUserData.TEST_USER_LAST_NAME,
                "00000003", TestUserData.TEST_USER_ADDRESS);

        this.userRepository.saveAll(List.of(firstTestUser, secondTestUser, thirdTestUser));
    }
}
