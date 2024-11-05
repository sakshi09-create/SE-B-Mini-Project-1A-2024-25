
import javax.swing.*; // For GUI components like JFrame, JButton, etc.
import javax.swing.filechooser.FileNameExtensionFilter; // For file filters in JFileChooser
import java.awt.event.ActionEvent; // For handling ActionEvents
import java.io.File; // For file manipulation
import java.io.FileInputStream; // For reading files
import java.io.IOException; // For handling IO exceptions
import java.sql.Connection; // For database connection
import java.sql.DriverManager; // For establishing database connection
import java.sql.PreparedStatement; // For executing parameterized SQL statements
import java.sql.SQLException;
import patmini.usersession;// For handling SQL exceptions


public class notes extends javax.swing.JFrame {

    public notes() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        upbutton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(93, 89, 501, -1));

        jButton1.setText("Close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(525, 142, -1, -1));

        upbutton.setText("UPLOAD");
        upbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upbuttonActionPerformed(evt);
            }
        });
        getContentPane().add(upbutton, new org.netbeans.lib.awtextra.AbsoluteConstraints(443, 142, -1, -1));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Enter PATH  :");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 81, -1));

        jLabel2.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 255));
        jLabel2.setText("UPLOAD FILES");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 165, 71));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Picsart_24-09-01_22-01-15-634.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 600, 170));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
       
    }//GEN-LAST:event_jTextField1ActionPerformed
 private void uploadFile(String filePath) {
          String url = "jdbc:mysql://localhost:3306/pat"; 
    String user = "root"; 
    String password = "pat@admin#0987"; 

    String sql = "INSERT INTO pdf_files (name, pdf_data, admin) VALUES (?, ?, ?)";

    try (Connection conn = DriverManager.getConnection(url, user, password);
         PreparedStatement stmt = conn.prepareStatement(sql);
         FileInputStream inputStream = new FileInputStream(new File(filePath))) {

        // Set parameters for the prepared statement
        stmt.setString(1, new File(filePath).getName());
        stmt.setBlob(2, inputStream);
        stmt.setString(3, UserSession.loggedInUsername); // Assuming you want to store the admin username

        // Execute the update
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(this, "File uploaded successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "File upload failed.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "File IO error: " + e.getMessage());
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Unexpected error: " + e.getMessage());
    }
    }
    private void upbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upbuttonActionPerformed
      String filePath = jTextField1.getText().trim(); // Trim any spaces
    if (!filePath.isEmpty()) {
        uploadFile(filePath);jTextField1.setText("");
    } else {
        JOptionPane.showMessageDialog(this, "Please select a file first.");
    }
    }//GEN-LAST:event_upbuttonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
         try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(notes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new notes().setVisible(true));
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton upbutton;
    // End of variables declaration//GEN-END:variables
}