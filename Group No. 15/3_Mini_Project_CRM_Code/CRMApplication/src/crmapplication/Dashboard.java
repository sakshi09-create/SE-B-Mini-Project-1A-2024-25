package crmapplication;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dashboard extends JFrame {

    private JButton customerButton;
    
    
    
    private JButton employeeButton;
    private JButton productButton;
    private JButton logoutButton;

    // Declare the table model and table
    private DefaultTableModel tableModel;
    private JTable table;

    public Dashboard() {

        // Frame settings for full screen
        setTitle("Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window to full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with BorderLayout
        JPanel panel = new JPanel(new BorderLayout());
        add(panel);

        // Set background color (optional)
        panel.setBackground(Color.LIGHT_GRAY);

        // Create and add the Dashboard label
        JLabel label = new JLabel("Dashboard", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 26));
        label.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        panel.add(label, BorderLayout.NORTH); // Add label to the top (NORTH)

        // Create a panel for the buttons with FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20)); // 20px horizontal and vertical padding
        buttonPanel.setBackground(Color.LIGHT_GRAY); // Match background color
        panel.add(buttonPanel, BorderLayout.CENTER); // Add button panel to center

        // Load icons (make sure the paths to the icons are correct)
        ImageIcon customerIcon = new ImageIcon("icons/customer.png"); // Adjust path as necessary
        ImageIcon employeeIcon = new ImageIcon("icons/employee.png");
        ImageIcon productIcon = new ImageIcon("icons/product.png");
        ImageIcon logoutIcon = new ImageIcon("icons/logout.png");

        // Initialize the buttons with icons
        customerButton = new JButton("Customer", customerIcon);
        customerButton.setPreferredSize(new Dimension(160, 50)); // Set smaller size
        customerButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around button
        customerButton.setBackground(java.awt.Color.WHITE);
        customerButton.setForeground(java.awt.Color.BLUE);
        buttonPanel.add(customerButton); // Add to button panel

        employeeButton = new JButton("Employee", employeeIcon);
        employeeButton.setPreferredSize(new Dimension(160, 50)); // Set smaller size
        employeeButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around button
        employeeButton.setBackground(java.awt.Color.WHITE);
        employeeButton.setForeground(java.awt.Color.BLUE);
        buttonPanel.add(employeeButton); // Add to button panel

        productButton = new JButton("Product", productIcon);
        productButton.setPreferredSize(new Dimension(160, 50)); // Set smaller size
        productButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around button
        productButton.setBackground(java.awt.Color.WHITE);
        productButton.setForeground(java.awt.Color.BLUE);
        buttonPanel.add(productButton); // Add to button panel

        // Add the Logout button with icon
        logoutButton = new JButton("Logout", logoutIcon);
        logoutButton.setPreferredSize(new Dimension(160, 50)); // Set smaller size
        logoutButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding around button
        buttonPanel.add(logoutButton); // Add to button panel

        // Add ActionListener to logoutButton
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginPage();
                dispose(); // Close Dashboard
            }
        });

        // Add ActionListeners for navigation
        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CustomerPage();
                dispose(); // Close Dashboard
            }
        });

        employeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EmployeePage();
                dispose(); // Close Dashboard
            }
        });

        productButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProductPage();
                dispose(); // Close Dashboard
            }
        });
        
        

        // Initialize the table model and table
        String[] columnNames = {"Customer ID", "Customer Name", "Company", "Product ID", "Product Name", "Version", "Batch No", "Price per Unit", "Billing UID"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setBackground(java.awt.Color.WHITE);

        // Load data into the table
        loadDataIntoTable();

        // Create a scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Add padding to the scroll pane by wrapping it in another panel
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding (top, left, bottom, right)
        tablePanel.add(scrollPane, BorderLayout.CENTER); // Add scroll pane to the panel
        tablePanel.setBackground(Color.LIGHT_GRAY); 

        panel.add(tablePanel, BorderLayout.SOUTH); // Add table panel to the bottom (SOUTH)

        setVisible(true);
    }

    private void loadDataIntoTable() {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/your_database_name"; // Update with your database name
        String user = "your_username"; // Update with your username
        String password = "your_password"; // Update with your password

        // SQL queries to fetch data
        String customerQuery = "SELECT id, name, company FROM customers"; // Adjust table and column names as necessary
        String productQuery = "SELECT product_id, product_name, version, batch_no, price_per_unit, billing_uid FROM products"; // Adjust table and column names as necessary

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet customerResult = statement.executeQuery(customerQuery);
             ResultSet productResult = statement.executeQuery(productQuery)) {
            
            // Load Customer Data into the table model
            while (customerResult.next()) {
                String customerId = customerResult.getString("id");
                String customerName = customerResult.getString("name");
                String company = customerResult.getString("company");
                
                // Add row for each customer
                tableModel.addRow(new Object[]{customerId, customerName, company, null, null, null, null, null, null});
            }

            // Load Product Data into the table model
            while (productResult.next()) {
                String productId = productResult.getString("product_id");
                String productName = productResult.getString("product_name");
                String version = productResult.getString("version");
                String batchNo = productResult.getString("batch_no");
                String pricePerUnit = productResult.getString("price_per_unit");
                String billingUid = productResult.getString("billing_uid");

                // Add row for each product
                tableModel.addRow(new Object[]{null, null, null, productId, productName, version, batchNo, pricePerUnit, billingUid});
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Dashboard();
        
    }
}