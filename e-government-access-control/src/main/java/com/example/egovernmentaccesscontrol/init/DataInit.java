package com.example.egovernmentaccesscontrol.init;

import com.example.egovernmentaccesscontrol.database.DatabaseInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInit implements CommandLineRunner {
    private final DatabaseInitializer databaseInitializer;
    @Override
    public void run(String... args) throws Exception {
        databaseInitializer.checkAndCreateSubscription();
    }
}
