package crmapplication;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class ProductPage extends JFrame {
    private JButton backButton;
    private static DefaultTableModel tableModel;
    private static JTable table;

    public ProductPage() {
        // Frame settings for full screen
        setTitle("Product Page");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window to full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel);

        JLabel label = new JLabel("Product Details Page", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.NORTH);

        createProductTable(panel); // Create the product table

        setVisible(true);
    }

    private void createProductTable(JPanel panel) {
        // Table panel
        JPanel tablePanel = new JPanel(new BorderLayout(10, 10));
        tablePanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] columnNames = {"Product ID", "Name", "Size", "Version", "Type", "Batch No", "Generation", "Company", "Price per Unit", "Billing UID"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton addButton = new JButton("Add New");
        addButton.addActionListener(e -> openForm());

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteSelectedRow());

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(e -> updateSelectedRow());

        // Adding buttons to the panel
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new Dashboard(); // Navigate to Dashboard
            dispose(); // Close current window
        });
        buttonPanel.add(backButton); // Adding back button next to update button

        tablePanel.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(tablePanel, BorderLayout.CENTER); // Add table panel to main panel

        loadDataFromDatabase(); // Load data when GUI initializes
    }

    private void openForm() {
    JFrame formFrame = new JFrame("Add Product");
    formFrame.setSize(400, 400);

    JPanel formPanel = new JPanel(new GridLayout(11, 2, 10, 10)); // Adjust grid layout to include new field
    JTextArea[] fields = new JTextArea[10];
    String[] labels = {"Product ID", "Name", "Size", "Version", "Type", "Batch No", "Generation", "Company", "Price per Unit", "Billing UID"};

    // Create text areas for each field
    for (int i = 0; i < labels.length; i++) {
        JLabel label = new JLabel(labels[i]);
        label.setBorder(new EmptyBorder(10, 20, 5, 0));
        formPanel.add(label);

        fields[i] = new JTextArea(2, 2);
        fields[i].setBorder(new EmptyBorder(10, 0, 5, 25));
        formPanel.add(new JScrollPane(fields[i]));
    }

    // References to specific fields to disable if billing_uid is empty
    JTextArea versionField = fields[3];      // Version
    JTextArea batchNoField = fields[5];      // Batch No
    JTextArea generationField = fields[6];   // Generation
    JTextArea priceField = fields[8];        // Price per Unit
    JTextArea billingUidField = fields[9];   // Billing UID

    // Add listener to billing_uid field to disable or enable fields
    billingUidField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
        public void changedUpdate(javax.swing.event.DocumentEvent e) {
            toggleFields(billingUidField, versionField, batchNoField, generationField, priceField);
        }

        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            toggleFields(billingUidField, versionField, batchNoField, generationField, priceField);
        }

        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            toggleFields(billingUidField, versionField, batchNoField, generationField, priceField);
        }
    });

    // Disable fields initially by passing the fields as parameters
    toggleFields(billingUidField, versionField, batchNoField, generationField, priceField);

    JButton submitButton = new JButton("Submit");
    submitButton.addActionListener(e -> submitData(fields, formFrame));
    formPanel.add(new JLabel());
    formPanel.add(submitButton);

    formFrame.add(formPanel);
    formFrame.setVisible(true);
}
    
    private void toggleFields(JTextArea billingUidField, JTextArea versionField, JTextArea batchNoField, JTextArea generationField, JTextArea priceField) {
    boolean isBillingUidEmpty = billingUidField.getText().trim().isEmpty();
    versionField.setEnabled(!isBillingUidEmpty);
    batchNoField.setEnabled(!isBillingUidEmpty);
    generationField.setEnabled(!isBillingUidEmpty);
    priceField.setEnabled(!isBillingUidEmpty);
}



    private void submitData(JTextArea[] fields, JFrame formFrame) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO product (pd_id, prod_name, prod_size, prod_version, prod_type, batch_no, prod_gen, company_belong, prize_per_unit, billing_uid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            for (int i = 0; i < fields.length; i++) {
                String value = fields[i].getText().trim();
                // Check if billing_uid is empty, and set NULL for certain fields
                if (i == 3 || i == 5 || i == 6 || i == 8) { // Disable version, batch no, generation, price per unit if billing_uid is empty
                    if (fields[9].getText().trim().isEmpty()) { // If billing_uid is empty
                        statement.setNull(i + 1, Types.VARCHAR); // Set NULL for corresponding fields
                    } else {
                        statement.setString(i + 1, value);
                    }
                } else {
                    statement.setString(i + 1, value);
                }
            }
            if (statement.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(formFrame, "Data successfully inserted.");
                tableModel.addRow(new Object[]{
                        fields[0].getText(), fields[1].getText(), fields[2].getText(), fields[3].getText(),
                        fields[4].getText(), fields[5].getText(), fields[6].getText(), fields[7].getText(),
                        fields[8].getText(), fields[9].getText() // Adding billing_uid
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(formFrame, "Error: Unable to insert data.");
        }
    }

    private void deleteSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String id = table.getValueAt(selectedRow, 0).toString();
            try (Connection connection = getConnection()) {
                String query = "DELETE FROM product WHERE pd_id = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, id);
                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(null, "Record deleted successfully.");
                    tableModel.removeRow(selectedRow);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: Unable to delete record.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.");
        }
    }

    private void updateSelectedRow() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String[] oldData = new String[10];
            for (int i = 0; i < 10; i++) {
                oldData[i] = table.getValueAt(selectedRow, i).toString();
            }

            JFrame updateFrame = new JFrame("Update Product");
            updateFrame.setSize(400, 400);

            JPanel updatePanel = new JPanel(new GridLayout(11, 2, 10, 10));
            JTextArea[] fields = new JTextArea[10];
            String[] labels = {"Product ID", "Name", "Size", "Version", "Type", "Batch No", "Generation", "Company", "Price per Unit", "Billing UID"};
            for (int i = 0; i < labels.length; i++) {
                updatePanel.add(new JLabel(labels[i]));
                fields[i] = new JTextArea(oldData[i], 2, 2);
                updatePanel.add(new JScrollPane(fields[i]));
            }

            JButton updateButton = new JButton("Update");
            updateButton.addActionListener(e -> {
                try (Connection connection = getConnection()) {
                    String query = "UPDATE product SET prod_name = ?, prod_size = ?, prod_version = ?, prod_type = ?, batch_no = ?, prod_gen = ?, company_belong = ?, prize_per_unit = ?, billing_uid = ? WHERE pd_id = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    for (int i = 0; i < 9; i++) {
                        if (i == 3 || i == 5 || i == 6 || i == 8) { // Disable version, batch no, generation, price per unit if billing_uid is empty
                            if (fields[9].getText().trim().isEmpty()) { // If billing_uid is empty
                                statement.setNull(i + 1, Types.VARCHAR); // Set NULL for corresponding fields
                            } else {
                                statement.setString(i + 1, fields[i].getText());
                            }
                        } else {
                            statement.setString(i + 1, fields[i].getText());
                        }
                    }
                    statement.setString(10, fields[0].getText()); // Set product ID for WHERE clause
                    if (statement.executeUpdate() > 0) {
                        JOptionPane.showMessageDialog(updateFrame, "Record updated successfully.");
                        for (int i = 0; i < fields.length; i++) {
                            table.setValueAt(fields[i].getText(), selectedRow, i);
                        }
                        updateFrame.dispose();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(updateFrame, "Error: Unable to update record.");
                }
            });

            updatePanel.add(new JLabel());
            updatePanel.add(updateButton);
            updateFrame.add(updatePanel);
            updateFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to update.");
        }
    }

    private static void loadDataFromDatabase() {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM product");
             ResultSet resultSet = statement.executeQuery()) {

            tableModel.setRowCount(0);
            while (resultSet.next()) {
                tableModel.addRow(new Object[]{
                        resultSet.getString("pd_id"),
                        resultSet.getString("prod_name"),
                        resultSet.getString("prod_size"),
                        resultSet.getString("prod_version"),
                        resultSet.getString("prod_type"),
                        resultSet.getString("batch_no"),
                        resultSet.getString("prod_gen"),
                        resultSet.getString("company_belong"),
                        resultSet.getString("prize_per_unit"),
                        resultSet.getString("billing_uid") // Include billing_uid in table
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Unable to load data.");
        }
    }

    private static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/newcrm";
        return DriverManager.getConnection(url, "root", "root123");
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(ProductPage::new);
    }

    

    

   
}
