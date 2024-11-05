/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee.payroll.system;

/**
 *
 * @author Aditya 
 */


import java.sql.*;
import javax.swing.*;

public class db {

    Connection conn = null;

    public static Connection java_db() {

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Update connection string for MySQL
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/empnet", "root", "admin");
            // JOptionPane.showMessageDialog(null, "Connection to database is successful");
            return conn;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
