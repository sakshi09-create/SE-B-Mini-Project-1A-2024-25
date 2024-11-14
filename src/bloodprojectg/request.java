/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bloodprojectg;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.DriverManager;


/**
 *
 * @author kadam
 */
public class request extends javax.swing.JFrame {

    /**
     * Creates new form request
     */
    public request() {
        initComponents();

    }
    






   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        fbloodgroup = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        famount = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        fmobile = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        faddress = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        frequest = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Blood group requested");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 216, 35));

        fbloodgroup.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-" }));
        jPanel1.add(fbloodgroup, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 150, 214, 38));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Amount");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 216, 34));

        famount.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        famount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        famount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                famountActionPerformed(evt);
            }
        });
        jPanel1.add(famount, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 210, 214, 40));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Mobile no.");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, 216, 38));

        fmobile.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel1.add(fmobile, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 280, 214, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Address");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, 130, 30));

        faddress.setColumns(20);
        faddress.setRows(5);
        jScrollPane1.setViewportView(faddress);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 350, -1, -1));

        jPanel2.setBackground(new java.awt.Color(153, 0, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Algerian", 1, 36)); // NOI18N
        jLabel1.setText("blood request");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, 73));

        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Lenovo\\OneDrive\\Desktop\\project\\kk.png")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, -1, 60));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 90));

        jPanel3.setBackground(new java.awt.Color(153, 0, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        frequest.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        frequest.setText("SEND REQUEST");
        frequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                frequestActionPerformed(evt);
            }
        });
        jPanel3.add(frequest, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 188, 56));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, 700, 90));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        home homeframe = new home();
        homeframe.setVisible(true);
        homeframe.setLocationRelativeTo(null);
        homeframe.pack();
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void frequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_frequestActionPerformed
                                           
    if(fbloodgroup.getSelectedItem()==null || famount.getText().isEmpty() || fmobile.getText().isEmpty() ||
        faddress.getText().isEmpty() ) {
        JOptionPane.showMessageDialog(null, "Please fill all fields.");
        return;
    }
    // to check mobile no.
    if (!fmobile.getText().matches("\\d{10}")) {
        JOptionPane.showMessageDialog(null, "Please enter a valid 10-digit mobile number.");
        return;
    }

    if(evt.getSource() == frequest){

        String blood_group = (String) fbloodgroup.getSelectedItem();
        String amount = famount.getText();
        String mobile_no = fmobile.getText();
        String address = faddress.getText();
        String Status = "Pending";

        conn c1 = new conn();
        String query = "INSERT INTO request (blood_group, amount, mobile_no, address, Status) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = c1.c.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

            // Set the parameters
            pstmt.setString(1, blood_group);
            pstmt.setString(2, amount);
            pstmt.setString(3, mobile_no);
            pstmt.setString(4, address);
            pstmt.setString(5, Status);

            // Execute the INSERT statement
            pstmt.executeUpdate();

            // Retrieve the generated request ID
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                int requestId = generatedKeys.getInt(1);
                JOptionPane.showMessageDialog(null, "Request submitted successfully! Your request ID is: " + requestId);
                
                
                 famount.setText("");
            fmobile.setText("");
            faddress.setText("");
            fbloodgroup.setSelectedIndex(0);  // Reset to the first item
            
            
            
            } else {
                JOptionPane.showMessageDialog(null, "Error retrieving request ID.");
            }

            // Close the ResultSet
            generatedKeys.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // You may want to close the connection here (if not handled elsewhere)
        }
    }


    }//GEN-LAST:event_frequestActionPerformed

    private void famountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_famountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_famountActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(request.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(request.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(request.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(request.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new request().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea faddress;
    private javax.swing.JTextField famount;
    private javax.swing.JComboBox<String> fbloodgroup;
    private javax.swing.JTextField fmobile;
    private javax.swing.JButton frequest;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private boolean checkUsernameExists(String username) {
        String url = "jdbc:mysql://localhost:3306/apsit";
        String user = "root";
        String password = "23107097";
        String query = "SELECT * FROM signup WHERE username = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();


            return rs.next(); // Return true if the username exists, false otherwise

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error while checking username.");
        }
        return false;
    }
}

    


