package com.example.egovernmentaccesscontrol.database;

import java.sql.*;

public class DatabaseInitializer {
    private static final String DB_URL =
            "jdbc:postgresql://localhost:5432/e-gov-db";
    private static final String USER = "postgres";
    private static final String PASS = "1234";
    private static final String SUBSCRIPTION_NAME = "e_government_subscription".toLowerCase();
    private static final String CHECK_SUBSCRIPTION_SQL =
            "SELECT 1 FROM pg_subscription WHERE subname = ?";
    private static final String CHECK_REPLICATION_SLOT_SQL =
            "SELECT 1 FROM pg_replication_slots WHERE slot_name = ?";
    private static final String CREATE_SUBSCRIPTION_SQL =
            "CREATE SUBSCRIPTION " + SUBSCRIPTION_NAME + " " +
                    "CONNECTION 'host=localhost port=5432 dbname=e-gov-db user=postgres password=1234' " +
                    "PUBLICATION e_government_campaigns_publication " +
                    "WITH (synchronous_commit = 'off', copy_data = true, enabled = true);";

    public static void checkAndCreateSubscription() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
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
                        createSubStmt.execute(CREATE_SUBSCRIPTION_SQL);
                        System.out.println("Subscription created successfully.");
                    }
                } else {
                    System.out.println("Replication slot '" + SUBSCRIPTION_NAME + "' already exists.");
                }
            } else {
                System.out.println("Subscription '" + SUBSCRIPTION_NAME + "' already exists.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
