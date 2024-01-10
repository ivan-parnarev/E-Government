package com.egovernment.egovauth.factory.userFactory;

import com.egovernment.egovauth.domain.entity.Address;
import com.egovernment.egovauth.domain.entity.User;


public class UserFactory implements UserFactoryInterface {
    @Override
    public User createUser(String userPin, String firstName, String middleName, String lastName, Address address) {
        return User.builder()
                .userPin(userPin)
                .firstName(firstName)
                .lastName(lastName)
                .middleName(middleName)
                .address(address)
                .build();
    }
}
