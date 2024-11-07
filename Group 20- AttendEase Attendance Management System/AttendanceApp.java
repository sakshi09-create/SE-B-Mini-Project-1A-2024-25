package attendanceSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class AttendanceApp {
    private JFrame frame;
    private JTextField studentNameField, totalClassesField, attendedClassesField;
    private JComboBox<String> subjectComboBox;
    private JTable table;
    private DefaultTableModel tableModel;

    public AttendanceApp() {
        frame = new JFrame("ATTENDEASE - Digital Attendance System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1100, 600);
        frame.setLayout(null);

        // Heading Label
        JLabel headingLabel = new JLabel("ATTENDEASE");
        headingLabel.setFont(new Font("Serif", Font.BOLD, 32));
        headingLabel.setForeground(new Color(0, 0, 255)); // Blue color for heading
        headingLabel.setBounds(300, 10, 400, 50);
        frame.add(headingLabel);

        // Labels with updated color
        JLabel nameLabel = new JLabel("Student Name:");
        nameLabel.setBounds(30, 70, 100, 25);
        nameLabel.setForeground(new Color(0, 0, 255)); // Change label text color to blue
        frame.add(nameLabel);

        studentNameField = new JTextField();
        studentNameField.setBounds(150, 70, 150, 25);
        frame.add(studentNameField);

        JLabel subjectLabel = new JLabel("Subject:");
        subjectLabel.setBounds(30, 110, 100, 25);
        subjectLabel.setForeground(new Color(0, 0, 255)); // Change label text color to blue
        frame.add(subjectLabel);

        subjectComboBox = new JComboBox<>(new String[]{
                "Engineering Mathematics-III",
                "Discrete Structures and Graph Theory",
                "Data Structure",
                "Digital Logic & Computer Architecture",
                "Computer Graphics",
                "Data Structure Lab",
                "Digital Logic & Computer Architecture Lab",
                "Computer Graphics Lab",
                "Skill-based Lab course: Object Oriented Programming with Java",
                "Mini Project - 1 A"
        });
        subjectComboBox.setBounds(150, 110, 250, 25);
        frame.add(subjectComboBox);

        JLabel totalLabel = new JLabel("Total Classes:");
        totalLabel.setBounds(30, 150, 100, 25);
        totalLabel.setForeground(new Color(0, 0, 255)); // Change label text color to blue
        frame.add(totalLabel);

        totalClassesField = new JTextField();
        totalClassesField.setBounds(150, 150, 150, 25);
        frame.add(totalClassesField);

        JLabel attendedLabel = new JLabel("Attended Classes:");
        attendedLabel.setBounds(30, 190, 120, 25);
        attendedLabel.setForeground(new Color(0, 0, 255)); // Change label text color to blue
        frame.add(attendedLabel);

        attendedClassesField = new JTextField();
        attendedClassesField.setBounds(150, 190, 150, 25); // Adjusted bounds to prevent overlap
        frame.add(attendedClassesField);

        // Buttons
        JButton addButton = new JButton("Add Record");
        addButton.setBounds(150, 230, 150, 30);
        frame.add(addButton);

        JButton clearButton = new JButton("Clear Fields");
        clearButton.setBounds(320, 230, 150, 30); // Adjusted to same row as addButton
        frame.add(clearButton);

        JButton deleteButton = new JButton("Delete Record");
        deleteButton.setBounds(150, 270, 150, 30);
        frame.add(deleteButton);

        JButton calculateOverallButton = new JButton("Calculate Overall Attendance");
        calculateOverallButton.setBounds(320, 270, 250, 30); // Adjusted position to avoid overlap
        frame.add(calculateOverallButton);

        // JTable shifted down to avoid overlapping with buttons
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        tableModel.addColumn("ID");
        tableModel.addColumn("Student Name");
        tableModel.addColumn("Subject");
        tableModel.addColumn("Total Classes");
        tableModel.addColumn("Attended Classes");
        tableModel.addColumn("Attendance %");

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(30, 320, 950, 200); // Adjusted the position and size of the table
        frame.add(tableScrollPane);

        // ActionListener for adding records
        addButton.addActionListener(e -> addAttendanceRecord());

        // ActionListener for clearing fields
        clearButton.addActionListener(e -> clearFields());

        // ActionListener for deleting the selected student record
        deleteButton.addActionListener(e -> deleteStudentRecord());

        // ActionListener for calculating overall attendance
        calculateOverallButton.addActionListener(e -> calculateOverallAttendance());

        loadAttendanceRecords();  // Load existing records into the table
        frame.setVisible(true);
    }

    private void addAttendanceRecord() {
        String studentName = studentNameField.getText();
        String subject = (String) subjectComboBox.getSelectedItem();

        // Try-catch block to handle invalid number inputs
        try {
            int totalClasses = Integer.parseInt(totalClassesField.getText());
            int attendedClasses = Integer.parseInt(attendedClassesField.getText());

            // Validation: Ensure attended classes is not greater than total classes
            if (attendedClasses > totalClasses) {
                JOptionPane.showMessageDialog(frame, "Attended classes cannot be greater than total classes!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return; // Stop further execution if the validation fails
            }

            float attendancePercentage = (float) attendedClasses / totalClasses * 100;

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement("INSERT INTO attendance(student_name, subject, total_classes, attended_classes, attendance_percentage) VALUES (?, ?, ?, ?, ?)")) {
                stmt.setString(1, studentName);
                stmt.setString(2, subject);
                stmt.setInt(3, totalClasses);
                stmt.setInt(4, attendedClasses);
                stmt.setFloat(5, attendancePercentage);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(frame, "Record Added Successfully!");
                loadAttendanceRecords();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter valid numbers for classes!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteStudentRecord() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            String studentName = tableModel.getValueAt(selectedRow, 1).toString();  // Get the student name from the selected row

            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete all records for " + studentName + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement("DELETE FROM attendance WHERE student_name = ?")) {
                    stmt.setString(1, studentName);
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(frame, "Records for " + studentName + " deleted successfully.");
                    loadAttendanceRecords();  // Refresh the table after deletion
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a student record to delete.", "No Record Selected", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void loadAttendanceRecords() {
        tableModel.setRowCount(0);
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM attendance ORDER BY student_name ASC")) { // Sorted by Student Name
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("student_name"),
                        rs.getString("subject"),
                        rs.getInt("total_classes"),
                        rs.getInt("attended_classes"),
                        rs.getFloat("attendance_percentage")
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void clearFields() {
        studentNameField.setText("");
        totalClassesField.setText("");
        attendedClassesField.setText("");
        subjectComboBox.setSelectedIndex(0);
    }

    private void calculateOverallAttendance() {
        DefaultTableModel overallTableModel = new DefaultTableModel();
        overallTableModel.addColumn("Student Name");
        overallTableModel.addColumn("Engineering Mathematics-III");
        overallTableModel.addColumn("Discrete Structures and Graph Theory");
        overallTableModel.addColumn("Data Structure");
        overallTableModel.addColumn("Digital Logic & Computer Architecture");
        overallTableModel.addColumn("Computer Graphics");
        overallTableModel.addColumn("Data Structure Lab");
        overallTableModel.addColumn("Digital Logic & Computer Architecture Lab");
        overallTableModel.addColumn("Computer Graphics Lab");
        overallTableModel.addColumn("Skill-based Lab course: Object Oriented Programming with Java");
        overallTableModel.addColumn("Mini Project - 1 A");
        overallTableModel.addColumn("Overall Attendance (%)");

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT student_name, " +
                     "SUM(CASE WHEN subject = 'Engineering Mathematics-III' THEN attendance_percentage END) AS 'Engineering Mathematics-III', " +
                     "SUM(CASE WHEN subject = 'Discrete Structures and Graph Theory' THEN attendance_percentage END) AS 'Discrete Structures and Graph Theory', " +
                     "SUM(CASE WHEN subject = 'Data Structure' THEN attendance_percentage END) AS 'Data Structure', " +
                     "SUM(CASE WHEN subject = 'Digital Logic & Computer Architecture' THEN attendance_percentage END) AS 'Digital Logic & Computer Architecture', " +
                     "SUM(CASE WHEN subject = 'Computer Graphics' THEN attendance_percentage END) AS 'Computer Graphics', " +
                     "SUM(CASE WHEN subject = 'Data Structure Lab' THEN attendance_percentage END) AS 'Data Structure Lab', " +
                     "SUM(CASE WHEN subject = 'Digital Logic & Computer Architecture Lab' THEN attendance_percentage END) AS 'Digital Logic & Computer Architecture Lab', " +
                     "SUM(CASE WHEN subject = 'Computer Graphics Lab' THEN attendance_percentage END) AS 'Computer Graphics Lab', " +
                     "SUM(CASE WHEN subject = 'Skill-based Lab course: Object Oriented Programming with Java' THEN attendance_percentage END) AS 'Skill-based Lab course: Object Oriented Programming with Java', " +
                     "SUM(CASE WHEN subject = 'Mini Project - 1 A' THEN attendance_percentage END) AS 'Mini Project - 1 A' " +
                     "FROM attendance GROUP BY student_name")) {

            while (rs.next()) {
                overallTableModel.addRow(new Object[]{
                        rs.getString("student_name"),
                        rs.getFloat("Engineering Mathematics-III"),
                        rs.getFloat("Discrete Structures and Graph Theory"),
                        rs.getFloat("Data Structure"),
                        rs.getFloat("Digital Logic & Computer Architecture"),
                        rs.getFloat("Computer Graphics"),
                        rs.getFloat("Data Structure Lab"),
                        rs.getFloat("Digital Logic & Computer Architecture Lab"),
                        rs.getFloat("Computer Graphics Lab"),
                        rs.getFloat("Skill-based Lab course: Object Oriented Programming with Java"),
                        rs.getFloat("Mini Project - 1 A"),
                        calculateOverallPercentage(rs)
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Create a new JFrame to display overall attendance
        JFrame overallAttendanceFrame = new JFrame("Overall Attendance");
        overallAttendanceFrame.setSize(800, 400);
        overallAttendanceFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JTable overallTable = new JTable(overallTableModel);
        JScrollPane overallScrollPane = new JScrollPane(overallTable);
        overallAttendanceFrame.add(overallScrollPane);
        overallAttendanceFrame.setVisible(true);
    }

    private float calculateOverallPercentage(ResultSet rs) throws SQLException {
        // Assuming each subject has equal weight
        int subjectsCount = 10; // Update this if you add more subjects
        float totalAttendance = 0;
        for (int i = 2; i <= subjectsCount + 1; i++) {
            totalAttendance += rs.getFloat(i);
        }
        return totalAttendance / subjectsCount;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AttendanceApp::new);
    }
}
