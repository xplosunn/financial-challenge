package com.github.xplosunn.financialchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class FinancialChallengeApplication {

    public static void main(String[] args) throws SQLException, IOException, InterruptedException {
        createSchemaWithData();
        SpringApplication.run(FinancialChallengeApplication.class, args);
    }

    private static void createSchemaWithData() throws SQLException, IOException, InterruptedException {
        // Await for database
        Thread.sleep(20000);

        String url = "jdbc:mysql://mysql-db:3306/";
        String username = "root";
        String password = "password";
        String databaseName = "transaction_db";

        Connection connection = DriverManager.getConnection(url, username, password);

        Statement statement = connection.createStatement();

        statement.execute("CREATE DATABASE IF NOT EXISTS " + databaseName);

        statement.execute("USE " + databaseName);

        String schemaSql = getStringFromInputStream(getResourceAsStream("schemaWithData.sql"));
        statement.execute(schemaSql);

        statement.execute(
                "INSERT INTO transactions (user_id, amount)" +
                "  VALUES (1, -5), (1, -13), (2, 0), (1, 7);");

        connection.close();
    }

    private static InputStream getResourceAsStream(String resource) {
        final InputStream in
                = FinancialChallengeApplication.class.getClassLoader().getResourceAsStream(resource);

        return in == null ? FinancialChallengeApplication.class.getResourceAsStream(resource) : in;
    }

    private static String getStringFromInputStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();

        String line;

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        br.close();
        return sb.toString();
    }

}
