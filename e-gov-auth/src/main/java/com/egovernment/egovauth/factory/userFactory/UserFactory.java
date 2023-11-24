package com.egovernment.egovauth.factory.userFactory;

import com.egovernment.egovauth.domain.entity.User;


public class UserFactory implements UserFactoryInterface {
    @Override
    public User createUser(String firstName, String middleName, String lastName, String userPin, String address) {
        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .middleName(middleName)
                .userPin(userPin)
                .address(address)
                .build();
    }
}
