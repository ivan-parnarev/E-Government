package com.egovernment.egovauth.init;

import com.egovernment.egovauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(String... args) throws Exception {
        this.userService.initTestUsers();
    }
}
