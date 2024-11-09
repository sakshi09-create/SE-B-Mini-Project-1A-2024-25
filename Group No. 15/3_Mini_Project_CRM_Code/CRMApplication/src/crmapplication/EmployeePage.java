package crmapplication;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class EmployeePage extends JFrame {
    private JButton backButton, addNewButton, deleteButton, editButton;
    private JTable employeeTable;
    private DefaultTableModel tableModel;
    private String connectionString = "jdbc:mysql://localhost:3306/newcrm"; 
    private String dbUser = "root"; // Replace with your DB username
    private String dbPassword = "root123"; // Replace with your DB password

    public EmployeePage() {
        // Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Frame settings
        setTitle("Employee Master");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window to full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create main panel with BorderLayout
        JPanel panel = new JPanel(new BorderLayout());
        add(panel);


        // Title Label
        JLabel titleLabel = new JLabel("Employee Master", JLabel.CENTER);
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));  // Add padding to the title
        panel.add(titleLabel, BorderLayout.NORTH);

        // Table to display employees
        tableModel = new DefaultTableModel();
        employeeTable = new JTable(tableModel);
        tableModel.addColumn("Employee ID");
        tableModel.addColumn("Employee Name");
        tableModel.addColumn("Email");
        tableModel.addColumn("Phone");

        JScrollPane scrollPane = new JScrollPane(employeeTable);
        scrollPane.setPreferredSize(new Dimension(screenSize.width - 100, screenSize.height - 200));  // Adjust size of the table
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        addNewButton = new JButton("Add New");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        backButton = new JButton("Back");

        // Add buttons to button panel
        buttonPanel.add(addNewButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);
        
        // Add some padding to ensure buttons are visible
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Add button panel to the main panel at the bottom
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Action Listeners for buttons
        addNewButton.addActionListener(e -> openAddEmployeeDialog());
        editButton.addActionListener(e -> openEditEmployeeDialog());
        deleteButton.addActionListener(e -> deleteEmployee());
        backButton.addActionListener(e -> goBack());

        // Load employee data
        loadEmployeeData();

        setVisible(true);
    }

    // Method to load employees from the database and display in the JTable
    private void loadEmployeeData() {
        try (Connection conn = DriverManager.getConnection(connectionString, dbUser, dbPassword)) {
            String query = "SELECT EmployeeID, EmployeeName, EmployeeEmailAddress, EmployeePhone FROM Employees";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            tableModel.setRowCount(0);  // Clear the table before loading new data
            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("EmployeeID"));
                row.add(rs.getString("EmployeeName"));
                row.add(rs.getString("EmployeeEmailAddress"));
                row.add(rs.getString("EmployeePhone"));
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading employee data: " + ex.getMessage());
        }
    }

    // Method to open dialog for adding a new employee
    private void openAddEmployeeDialog() {
        JDialog dialog = new JDialog(this, "Add New Employee", true);
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(5, 2));

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();

        dialog.add(new JLabel("Employee ID:"));
        dialog.add(idField);
        dialog.add(new JLabel("Employee Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Email:"));
        dialog.add(emailField);
        dialog.add(new JLabel("Phone:"));
        dialog.add(phoneField);

        JButton saveButton = new JButton("Save");
        dialog.add(saveButton);
        saveButton.addActionListener(e -> {
            addNewEmployee(idField.getText(), nameField.getText(), emailField.getText(), phoneField.getText());
            dialog.dispose();
        });

        dialog.setVisible(true);
    }

    // Method to add a new employee to the database
    private void addNewEmployee(String id, String name, String email, String phone) {
        try (Connection conn = DriverManager.getConnection(connectionString, dbUser, dbPassword)) {
            String query = "INSERT INTO Employees (EmployeeID, EmployeeName, EmployeeEmailAddress, EmployeePhone) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setString(3, email);
            stmt.setString(4, phone);
            stmt.executeUpdate();
            loadEmployeeData();
            JOptionPane.showMessageDialog(this, "New employee added successfully!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding new employee: " + ex.getMessage());
        }
    }

    // Method to open dialog for editing an existing employee
    private void openEditEmployeeDialog() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an employee to edit.");
            return;
        }

        String id = employeeTable.getValueAt(selectedRow, 0).toString();
        String name = employeeTable.getValueAt(selectedRow, 1).toString();
        String email = employeeTable.getValueAt(selectedRow, 2).toString();
        String phone = employeeTable.getValueAt(selectedRow, 3).toString();

        JDialog dialog = new JDialog(this, "Edit Employee", true);
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(5, 2));

        JTextField idField = new JTextField(id);
        JTextField nameField = new JTextField(name);
        JTextField emailField = new JTextField(email);
        JTextField phoneField = new JTextField(phone);

        dialog.add(new JLabel("Employee ID:"));
        dialog.add(idField);
        dialog.add(new JLabel("Employee Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Email:"));
        dialog.add(emailField);
        dialog.add(new JLabel("Phone:"));
        dialog.add(phoneField);

        JButton updateButton = new JButton("Update");
        dialog.add(updateButton);
        updateButton.addActionListener(e -> {
            updateEmployee(idField.getText(), nameField.getText(), emailField.getText(), phoneField.getText());
            dialog.dispose();
        });

        dialog.setVisible(true);
    }

    // Method to update an employee in the database
    private void updateEmployee(String id, String name, String email, String phone) {
        try (Connection conn = DriverManager.getConnection(connectionString, dbUser, dbPassword)) {
            String query = "UPDATE Employees SET EmployeeName = ?, EmployeeEmailAddress = ?, EmployeePhone = ? WHERE EmployeeID = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.setString(4, id);
            stmt.executeUpdate();
            loadEmployeeData();
            JOptionPane.showMessageDialog(this, "Employee updated successfully!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating employee: " + ex.getMessage());
        }
    }

    // Method to delete an employee from the database
    private void deleteEmployee() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an employee to delete.");
            return;
        }

        String id = employeeTable.getValueAt(selectedRow, 0).toString();
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete employee ID " + id + "?");
        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = DriverManager.getConnection(connectionString, dbUser, dbPassword)) {
                String query = "DELETE FROM Employees WHERE EmployeeID = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, id);
                stmt.executeUpdate();
                loadEmployeeData();
                JOptionPane.showMessageDialog(this, "Employee deleted successfully!");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error deleting employee: " + ex.getMessage());
            }
        }
    }

    // Method to go back to the main menu or previous screen
    private void goBack() {
        JOptionPane.showMessageDialog(this, "Back to previous page.");
        // Implement your back navigation here
        new Dashboard();
        dispose(); // This will close the current window
    }
}
