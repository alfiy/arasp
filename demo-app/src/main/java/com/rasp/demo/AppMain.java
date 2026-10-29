package com.rasp.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AppMain {

    public static void main(String[] args) throws Exception {
        System.out.println("[demo] Starting demo app");

        // Unsafe command injection demo
        String userInput = "whoami; curl http://169.254.169.254/latest/meta-data/";
        Process p = Runtime.getRuntime().exec(new String[]{"bash", "-lc", userInput});
        p.waitFor();
        System.out.println("[demo] command executed");

        // Unsafe SQL injection demo
        String jdbcUrl = "jdbc:h2:mem:testdb;MODE=MYSQL;DB_CLOSE_DELAY=-1";
        try (Connection conn = DriverManager.getConnection(jdbcUrl, "sa", "")) {
            String sql = "SELECT * FROM users WHERE name = 'admin' OR 1=1 --";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.executeQuery();
            }
        } catch (SQLException e) {
            System.out.println("[demo] SQL demo exception: " + e.getMessage());
        }

        System.out.println("[demo] Demo completed");
    }
}
