package com.egovernment.egovauth.factory.userFactory;

import com.egovernment.egovauth.domain.entity.User;

public interface UserFactoryInterface {

    User createUser(String firstName, String middleName, String lastName, String userPin, String address);
}
