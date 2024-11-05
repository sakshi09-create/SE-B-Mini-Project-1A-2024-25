/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Varunkumar lysetti
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class user_db_connection {
    public static Connection connect() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Updated for MySQL Connector/J 8.0+
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pat?serverTimezone=UTC", "root", "pat@admin#0987");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return conn;
    }
}
