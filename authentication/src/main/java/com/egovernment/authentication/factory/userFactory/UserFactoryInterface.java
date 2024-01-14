package com.egovernment.authentication.factory.userFactory;

import com.egovernment.authentication.domain.entity.Address;
import com.egovernment.authentication.domain.entity.User;

public interface UserFactoryInterface {

    User createUser(String userPin,String firstName, String middleName, String lastName, Address address);
}
