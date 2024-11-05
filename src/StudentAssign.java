import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 * StudentAssign class to display assignment questions.
 */
public class StudentAssign extends javax.swing.JFrame {

    public StudentAssign() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        q1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        q3 = new javax.swing.JTextField();
        q2 = new javax.swing.JTextField();
        q5 = new javax.swing.JTextField();
        q4 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 18));
        jLabel1.setForeground(new java.awt.Color(153, 153, 255));
        jLabel1.setText("Q.1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));

        q1.setFont(new java.awt.Font("Segoe UI Black", 0, 18));
        q1.setForeground(new java.awt.Color(153, 153, 255));
        getContentPane().add(q1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 302, 41));

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 0, 18));
        jLabel3.setForeground(new java.awt.Color(153, 153, 255));
        jLabel3.setText("ASSIGNMENT QUESTIONS");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 306, 20));

        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 0, 18));
        jLabel5.setForeground(new java.awt.Color(153, 153, 255));
        jLabel5.setText("Q.3");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 50, 20));

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 0, 18));
        jLabel8.setForeground(new java.awt.Color(153, 153, 255));
        jLabel8.setText("Q.2");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        q3.setFont(new java.awt.Font("Segoe UI Black", 0, 18));
        q3.setForeground(new java.awt.Color(153, 153, 255));
        getContentPane().add(q3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 302, 41));

        q2.setFont(new java.awt.Font("Segoe UI Black", 0, 18));
        q2.setForeground(new java.awt.Color(153, 153, 255));
        getContentPane().add(q2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 302, 41));

        q5.setFont(new java.awt.Font("Segoe UI Black", 0, 18));
        q5.setForeground(new java.awt.Color(153, 153, 255));
        getContentPane().add(q5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 400, 302, 41));

        q4.setFont(new java.awt.Font("Segoe UI Black", 0, 18));
        q4.setForeground(new java.awt.Color(153, 153, 255));
        getContentPane().add(q4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, 302, 41));

        jLabel9.setFont(new java.awt.Font("Segoe UI Black", 0, 18));
        jLabel9.setForeground(new java.awt.Color(153, 153, 255));
        jLabel9.setText("Q.4");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 326, 40, 20));

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 0, 18));
        jLabel7.setForeground(new java.awt.Color(153, 153, 255));
        jLabel7.setText("Q.5");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 406, 40, 30));

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 540, -1, -1));

        pack();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Load the database driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pat", "root", "pat@admin#0987");

            // Create a statement to execute the query
            Statement stmt = conn.createStatement();

            // Execute the query to retrieve assignment questions
            ResultSet rs = stmt.executeQuery("SELECT q1, q2, q3, q4, q5 FROM assignques");

            // Display the assignment questions in the text fields
            if (rs.next()) {
                q1.setText(rs.getString("q1"));
                q2.setText(rs.getString("q2"));
                q3.setText(rs.getString("q3"));
                q4.setText(rs.getString("q4"));
                q5.setText(rs.getString("q5"));
            }

            // Close the database connection
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentAssign().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField q1;
    private javax.swing.JTextField q2;
    private javax.swing.JTextField q3;
    private javax.swing.JTextField q4;
    private javax.swing.JTextField q5;
}
