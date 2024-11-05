package ui;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ConnectionProvider {
    public Connection c;
    public Statement s;

    public ConnectionProvider() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "Dhananjay@007");

            // Create the statement
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to get a PreparedStatement
    public PreparedStatement getPreparedStatement(String query) {
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ps;
    }

    // Method to close the connection
    public void closeConnection() {
        try {
            if (c != null) {
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Testing the connection
        new ConnectionProvider();
    }
}
