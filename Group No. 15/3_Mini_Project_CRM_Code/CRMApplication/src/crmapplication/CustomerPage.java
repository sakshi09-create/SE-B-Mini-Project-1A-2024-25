package crmapplication;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class CustomerPage extends JFrame {
    private JButton backButton;
    private static DefaultTableModel tableModel;
    private static JTable table;

    public CustomerPage() {
        // Frame settings for full screen
        setTitle("Customer Page");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window to full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel);

        // Title Label
        JLabel label = new JLabel("Customer Details Page");
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setBounds(100, 50, 200, 25);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.NORTH);
        
        // Add Back Button
        backButton = new JButton("Back");
        backButton.setBounds(50, 100, 100, 25);
        

        // Action Listener to go back to Dashboard
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Dashboard(); // Navigate to Dashboard
                dispose(); // Close current window
            }
        });

        // Initialize the table model and table
        String[] columnNames = {"Id", "Name", "Company", "Product Id", "Phone", "Email", "Role"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setBackground(java.awt.Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.add(scrollPane, BorderLayout.CENTER);

        // Create button panel for actions
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton addButton = new JButton("Add New");
        // addButton.setToolTipText("Add New Customer");
        addButton.addActionListener(e -> openForm());

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deleteSelectedRow());

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(e -> updateSelectedRow());
        
        JButton messageWhatsApp = new JButton("WhatsApp");
        messageWhatsApp.addActionListener(e -> msgWhatsApp() );
        
        JButton emailButton = new JButton("Email");
        emailButton.addActionListener(e -> sendEmail());

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(messageWhatsApp);
        buttonPanel.add(emailButton);
        buttonPanel.add(backButton, BorderLayout.SOUTH);

        
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Load data from database
        loadDataFromDatabase();

        setVisible(true);
    }

    private void openForm() {
        JFrame formFrame = new JFrame("Add Customer");
        formFrame.setSize(300, 400);

        JPanel formPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        JTextArea[] fields = new JTextArea[7];
        String[] labels = {"Id", "Name", "Company", "Product Id", "Phone", "Email", "Role"};
        
        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setBorder(new EmptyBorder(20, 20, 0, 0));
            formPanel.add(label);
            
            fields[i] = new JTextArea(2, 2);
            fields[i].setBorder(new EmptyBorder(20, 0, 20, 20));
            formPanel.add(new JScrollPane(fields[i]));
        }

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> submitData(fields, formFrame));
        formPanel.add(new JLabel());
        formPanel.add(submitButton);

        formFrame.add(formPanel);
        formFrame.setVisible(true);
    }

    private void submitData(JTextArea[] fields, JFrame formFrame) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO customers (id, Customer_name, Cust_company, productid, phone_no, email, role) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            for (int i = 0; i < fields.length; i++) {
                statement.setString(i + 1, fields[i].getText());
            }
            if (statement.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(formFrame, "Data successfully inserted.");
                tableModel.addRow(new Object[]{
                        fields[0].getText(), fields[1].getText(), fields[2].getText(), fields[3].getText(),
                        fields[4].getText(), fields[5].getText(), fields[6].getText()
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
                String query = "DELETE FROM customers WHERE id = ?";
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
            String[] oldData = new String[7];
            for (int i = 0; i < 7; i++) {
                oldData[i] = table.getValueAt(selectedRow, i).toString();
            }

            JFrame updateFrame = new JFrame("Update Customer");
            updateFrame.setSize(300, 400);

            JPanel updatePanel = new JPanel(new GridLayout(8, 2, 10, 10));
            JTextArea[] fields = new JTextArea[7];
            String[] labels = {"Id", "Name", "Company", "Product Id", "Phone", "Email", "Role"};
            for (int i = 0; i < labels.length; i++) {
                updatePanel.add(new JLabel(labels[i]));
                fields[i] = new JTextArea(oldData[i], 2, 2);
                updatePanel.add(new JScrollPane(fields[i]));
            }

            JButton updateButton = new JButton("Update");
            updateButton.addActionListener(e -> {
                try (Connection connection = getConnection()) {
                    String query = "UPDATE customers SET Customer_name = ?, Cust_company = ?, productid = ?, phone_no = ?, email = ?, role = ? WHERE id = ?";
                    PreparedStatement statement = connection.prepareStatement(query);
                    for (int i = 1; i <= 6; i++) {
                        statement.setString(i, fields[i].getText());
                    }
                    statement.setString(7, fields[0].getText());
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

    private void loadDataFromDatabase() {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers");
             ResultSet resultSet = statement.executeQuery()) {

            tableModel.setRowCount(0);
            while (resultSet.next()) {
                tableModel.addRow(new Object[]{
                        resultSet.getString("id"),
                        resultSet.getString("Customer_name"),
                        resultSet.getString("Cust_company"),
                        resultSet.getString("productid"),
                        resultSet.getString("phone_no"),
                        resultSet.getString("email"),
                        resultSet.getString("role")
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
    
    private void sendEmail() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow != -1) {
        // Get the customer's email from the correct column (6th column, index 5)
        String customerEmail = table.getValueAt(selectedRow, 5).toString();
        
        // Prompt user for the message they want to send
        String subject = JOptionPane.showInputDialog(this, "Enter the subject:");
        String messageContent = JOptionPane.showInputDialog(this, "Enter the message to send:");

        if (subject != null && !subject.trim().isEmpty() && messageContent != null && !messageContent.trim().isEmpty()) {
            // Format the URL for sending the email
            String mailTo = "mailto:" + customerEmail + "?subject=" + subject.replace(" ", "%20") + "&body=" + messageContent.replace(" ", "%20");

            try {
                // Open the default mail client with the constructed mailto URL
                Desktop desktop = Desktop.getDesktop();
                desktop.mail(new java.net.URI(mailTo));
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to open email client. Error: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Subject and message content cannot be empty.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Please select a customer from the table.");
    }
}
    
    private void msgWhatsApp() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow != -1) {
        // Get the customer's phone number from the correct column (5th column, index 4)
        String customerNumber = table.getValueAt(selectedRow, 4).toString();
        
        // Ensure phone number format is proper for WhatsApp (remove spaces, non-numeric characters, etc.)
        customerNumber = customerNumber.replaceAll("[^\\d]", ""); // Keep only digits
        
        // Prompt user for the message they want to send
        String messageContent = JOptionPane.showInputDialog(this, "Enter the message to send:");
        
        if (messageContent != null && !messageContent.trim().isEmpty()) {
            // Format the URL for sending the WhatsApp message via browser
            String url = "https://api.whatsapp.com/send?phone=" + customerNumber + "&text=" + messageContent.replace(" ", "%20");

            try {
                // Open the default browser with the constructed WhatsApp URL
                Desktop desktop = Desktop.getDesktop();
                desktop.browse(new java.net.URI(url));
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to open WhatsApp. Error: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Message content cannot be empty.");
        }
    } else {
        JOptionPane.showMessageDialog(this, "Please select a customer from the table.");
    }
}


    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(CustomerPage::new);
    }
}
