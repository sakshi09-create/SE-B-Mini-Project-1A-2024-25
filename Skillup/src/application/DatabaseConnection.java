/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application;

/**
 *
 * @author CHITRESH
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class manages the database connection.
 */
public class DatabaseConnection {

    // Database URL, username, and password
    private static final String URL = "jdbc:mysql://localhost:3306/Skillup"; // Replace with your DB name
    private static final String USERNAME = "root"; // Replace with your DB username
    private static final String PASSWORD = "CHIR2502004|"; // Replace with your DB password

    /**
     * Establishes and returns a connection to the MySQL database.
     *
     * @return a Connection object for interacting with the database
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}

