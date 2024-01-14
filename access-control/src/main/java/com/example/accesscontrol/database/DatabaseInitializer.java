package com.example.accesscontrol.database;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer {

    private final Logger LOGGER = LoggerFactory.getLogger(DatabaseInitializer.class);
    private final DataSourceProperties dataSourceProperties;
    private final String SUBSCRIPTION_NAME = "e_government_subscription".toLowerCase();
    private final String CHECK_SUBSCRIPTION_SQL =
            "SELECT 1 FROM pg_subscription WHERE subname = ?";
    private final String CHECK_REPLICATION_SLOT_SQL =
            "SELECT 1 FROM pg_replication_slots WHERE slot_name = ?";
    private String createSubscriptionSQL() {
        return "CREATE SUBSCRIPTION " + SUBSCRIPTION_NAME + " " +
                "CONNECTION 'host=localhost port=5432 dbname=e-gov-db user=" +
                dataSourceProperties.getUsername() + " password=" +
                dataSourceProperties.getPassword() + " ' " +
                "PUBLICATION e_government_campaigns_publication " +
                "WITH (synchronous_commit = 'off', copy_data = true, enabled = true);";
    }

    public void checkAndCreateSubscription() {
        try (Connection conn = DriverManager.getConnection(
                dataSourceProperties.getUrl(),
                dataSourceProperties.getUsername(),
                dataSourceProperties.getPassword());

             PreparedStatement checkSubscriptionStmt = conn.prepareStatement(CHECK_SUBSCRIPTION_SQL);
             PreparedStatement checkSlotStmt = conn.prepareStatement(CHECK_REPLICATION_SLOT_SQL)) {

            checkSubscriptionStmt.setString(1, SUBSCRIPTION_NAME);

            ResultSet subscriptionResultSet = checkSubscriptionStmt.executeQuery();

            boolean hasSubscription = subscriptionResultSet.next();

            if (!hasSubscription) {

                checkSlotStmt.setString(1, SUBSCRIPTION_NAME);
                ResultSet slotRs = checkSlotStmt.executeQuery();
                if (!slotRs.next()) {
                    try (Statement createSubStmt = conn.createStatement()) {
                        createSubStmt.execute(createSubscriptionSQL());
                        LOGGER.info("Subscription created successfully.");
                        System.out.println("Subscription created successfully.");
                    }
                } else {
                    LOGGER.info("Replication slot '{}' already exists.", SUBSCRIPTION_NAME);
                }
            } else {
                LOGGER.info("Subscription '{}' already exists.", SUBSCRIPTION_NAME);
                System.out.println("Subscription '" + SUBSCRIPTION_NAME + "' already exists.");
            }
        } catch (SQLException e) {
            LOGGER.error("SQL Exception occurred", e);
        }
    }
}
