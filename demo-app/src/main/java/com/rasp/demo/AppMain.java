package com.rasp.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class AppMain {

    public static void main(String[] args) throws Exception {
        System.out.println("[demo] Starting demo app");

        Class.forName("org.h2.Driver");
        String jdbcUrl = "jdbc:h2:mem:testdb;MODE=MYSQL;DB_CLOSE_DELAY=-1";

        try (Connection conn = DriverManager.getConnection(jdbcUrl, "sa", "")) {
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY, name VARCHAR(255))");
                stmt.execute("DELETE FROM users");
                stmt.execute("INSERT INTO users VALUES (1, 'alice')");
            }

            String sql = "SELECT * FROM users WHERE name = 'admin' OR 1=1 --";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.executeQuery();
            }
        }

        String userInput = "whoami; curl http://169.254.169.254/latest/meta-data/";
        Process p = Runtime.getRuntime().exec(new String[]{"bash", "-lc", userInput});
        p.waitFor();

        System.out.println("[demo] Demo completed");
    }
}
