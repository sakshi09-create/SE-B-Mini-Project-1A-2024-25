import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class approvalportalnew extends JFrame {

    private DefaultTableModel tableModel;
    private JTable table;
    private JComboBox<String> statusComboBox;
    private JButton saveStatusButton;
    private JButton backButton; // Added back button
    private Connection connection;

    public approvalportalnew() {
        // Set up the frame
        setTitle("Approval Portal");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create a panel for the header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(51, 153, 255));
        headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel titleLabel = new JLabel("Approval Portal");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);

        // Status combo box
        statusComboBox = new JComboBox<>(new String[]{"Complete", "Incomplete"});
        statusComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        headerPanel.add(statusComboBox);

        // Save Status button
        saveStatusButton = new JButton("Save Status");
        saveStatusButton.setFont(new Font("Arial", Font.PLAIN, 16));
        saveStatusButton.setBackground(new Color(255, 255, 255));
        saveStatusButton.setForeground(new Color(51, 153, 255));
        saveStatusButton.setBorderPainted(false);
        saveStatusButton.setFocusPainted(false);
        saveStatusButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Add ActionListener to save button
        saveStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveSelectedStatus();
            }
        });

        headerPanel.add(saveStatusButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setBackground(new Color(255, 255, 255));
        backButton.setForeground(new Color(51, 153, 255));
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Add ActionListener to back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBack(); // Implement goBack method
            }
        });

        headerPanel.add(backButton);

        // Add header panel to the frame
        add(headerPanel, BorderLayout.NORTH);

        // Create the table
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Name of the Student");
        tableModel.addColumn("Student ID");
        tableModel.addColumn("Certificate Upload Status");
        tableModel.addColumn("Cohort Name");
        tableModel.addColumn("Mark Status");

        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setSelectionBackground(new Color(51, 153, 255));
        table.setSelectionForeground(Color.WHITE);
        
        // Add a scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Connect to the database and fetch initial data
        connectToDatabase();
        fetchDetails();
    }

    private void connectToDatabase() {
        try {
            // Example connection string - modify as necessary
            String url = "jdbc:mysql://localhost:3306/skillup";
            String user = "root";
            String password = "CHIR2502004|";
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database connection error: " + e.getMessage());
        }
    }

    private void fetchDetails() {
        String query = "SELECT fullname, studentid, certificate_image, internship_domain FROM certificates"; // Adjust query
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Clear existing data
            tableModel.setRowCount(0); // Clear the table

            while (resultSet.next()) {
                String fullname = resultSet.getString("fullname");
                String studentid = resultSet.getString("studentid");
                Blob certificateImage = resultSet.getBlob("certificate_image");
                String uploadedAt = resultSet.getString("internship_domain");

                // Determine if the certificate is uploaded
                String status = (certificateImage != null) ? "Uploaded" : "Not Uploaded";

                // Add row data to the model
                tableModel.addRow(new Object[]{fullname, studentid, status, uploadedAt, "Incomplete"}); // Default status
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching data: " + e.getMessage());
        }
    }

    private void saveSelectedStatus() {
    int selectedRow = table.getSelectedRow();
    if (selectedRow != -1) {
        // Assuming studentId is in the second column (index 1)
        String studentId = tableModel.getValueAt(selectedRow, 1).toString();
        
        // Get the selected status from the combo box
        String status = statusComboBox.getSelectedItem().toString();
        
        // Update the query to match the new table structure
        String updateQuery = "UPDATE approvalportal SET mark_status = ? WHERE studentid = ?"; // Adjust 'your_table_name' as necessary
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, studentId);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Status updated successfully!");
            fetchDetails(); // Refresh data after update
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error updating status: " + e.getMessage());
        }
    } else {
        JOptionPane.showMessageDialog(this, "Please select a student from the table.");
    }
}


    private void goBack() {
        // Implement the logic to go back to the previous frame or window
        // For example, you could close the current frame and open a new one.
        // This is a placeholder; modify as needed for your application.
        JOptionPane.showMessageDialog(this, "Going back to the previous frame...");
        this.dispose(); // Close current frame (optional)
        new PreviousFrame().setVisible(true); // Replace with actual previous frame
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            approvalportalnew portal = new approvalportalnew();
            portal.setVisible(true);
        });
    }
}

// Placeholder for the previous frame (modify as needed)
class PreviousFrame extends JFrame {
    public PreviousFrame() {
        setTitle("Previous Frame");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JLabel label = new JLabel("This is the previous frame.", SwingConstants.CENTER);
        add(label);
    }
}
