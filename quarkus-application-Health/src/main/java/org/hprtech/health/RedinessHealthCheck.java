package org.hprtech.health;

import jakarta.inject.Inject;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Readiness
public class RedinessHealthCheck implements HealthCheck {

    @Inject
    private DataSource dataSource;

    @Override
    public HealthCheckResponse call() {
        try {
            Connection connection = dataSource.getConnection();
            if (connection.isValid(1)) {
                return HealthCheckResponse
                        .named("Database Rediness Health")
                        .up()
                        .build();
            } else {
                return HealthCheckResponse
                        .named("Database Rediness Health")
                        .down()
                        .build();
            }
        } catch (SQLException e) {
            return HealthCheckResponse
                    .named("Database Rediness Health")
                    .down()
                    .build();
        }
    }
}
