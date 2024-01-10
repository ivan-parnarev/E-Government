package com.example.egovernmentaccesscontrol.init;

import com.example.egovernmentaccesscontrol.database.DatabaseInitializer;
import com.example.egovernmentaccesscontrol.domain.Address;
import com.example.egovernmentaccesscontrol.service.KeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit implements CommandLineRunner {
    private final DatabaseInitializer databaseInitializer;
    private final KeyService keyService;

    @Override
    public void run(String... args) throws Exception {
        databaseInitializer.checkAndCreateSubscription();
        Address address = keyService.extractAddress();
    }
}
