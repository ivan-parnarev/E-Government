package com.egovernment.egovauth.init;

import com.egovernment.egovauth.service.LocationService;
import com.egovernment.egovauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit implements CommandLineRunner {

    private final UserService userService;
    private final LocationService locationService;

    @Override
    public void run(String... args) throws Exception {
        this.locationService.initRegions();
        this.locationService.initMunicipalities();
        this.locationService.initCities();
        this.userService.initTestUsers();
    }
}
