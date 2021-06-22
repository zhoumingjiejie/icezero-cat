package com.github.icezerocat.study.jdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

/**
 * @author 0.0.0
 */
@Slf4j
@SpringBootApplication
public class StudyJdbcApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(StudyJdbcApplication.class, args);
        while (true) {
            loadMySQLExtDict();
            Thread.sleep(1000);
        }

    }

    private static void loadMySQLExtDict() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {

            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/es_ik_mysql?serverTimezone=GMT&useSSL=false",
                    "root",
                    "123456");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select word from hot_words");

            while (rs.next()) {
                String theWord = rs.getString("word");
                System.out.println("[==========]hot word from mysql: " + theWord);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
