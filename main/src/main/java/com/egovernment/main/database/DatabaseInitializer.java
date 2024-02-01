package com.egovernment.main.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer {

    private final Logger LOGGER = LoggerFactory.getLogger(DatabaseInitializer.class);
    private final DataSourceProperties dataSourceProperties;
    private final String PUBLICATION_NAME = "e_government_campaigns_publication".toLowerCase();
    private final String CHECK_PUBLICATION_SQL =
            "SELECT 1 FROM pg_publication WHERE pubname = ?";

    private String createPublicationSQL() {
        return "CREATE PUBLICATION " + PUBLICATION_NAME + " " +
                "FOR TABLE campaigns, users, roles, users_roles;";
    }

    public void checkAndCreatePublication() {
        try (Connection conn = DriverManager.getConnection(
                dataSourceProperties.getUrl(),
                dataSourceProperties.getUsername(),
                dataSourceProperties.getPassword());
             PreparedStatement checkPublicationStmt = conn.prepareStatement(CHECK_PUBLICATION_SQL)) {

            checkPublicationStmt.setString(1, PUBLICATION_NAME);

            ResultSet publicationResultSet = checkPublicationStmt.executeQuery();

            if (!publicationResultSet.next()) {
                try (Statement createPubStmt = conn.createStatement()) {
                    createPubStmt.execute(createPublicationSQL());
                    LOGGER.info("Publication '{}' created successfully.", PUBLICATION_NAME);
                    System.out.println("Publication '" + PUBLICATION_NAME + "' created successfully.");
                }
            } else {
                LOGGER.info("Publication '{}' already exists.", PUBLICATION_NAME);
            }
        } catch (SQLException e) {
            LOGGER.error("SQL Exception occurred", e);
        }
    }
}