package com.example.egovernmentaccesscontrol.init;

import com.example.egovernmentaccesscontrol.database.DatabaseInitializer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        DatabaseInitializer.checkAndCreateSubscription();
    }
}
