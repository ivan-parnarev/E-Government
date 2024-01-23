package com.egovernment.authentication.factory.userFactory;

import com.egovernment.authentication.domain.entity.Address;
import com.egovernment.authentication.domain.entity.User;


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
