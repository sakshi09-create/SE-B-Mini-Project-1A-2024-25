/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author dhana
 */

package ui;
import java.sql.PreparedStatement;
import javax.swing.*;
public class signup extends javax.swing.JFrame {

    /**
     * Creates new form signup
     */
    public signup() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        sname = new javax.swing.JTextField();
        semail = new javax.swing.JTextField();
        spass = new javax.swing.JPasswordField();
        security_question = new javax.swing.JComboBox<>();
        ans = new javax.swing.JTextField();
        signup = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("     Signup");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 51, 1366, 50));

        jLabel2.setBackground(new java.awt.Color(105, 0, 0));
        jLabel2.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Name");
        jLabel2.setOpaque(true);
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 250, 200, 30));

        jLabel3.setBackground(new java.awt.Color(105, 0, 0));
        jLabel3.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Email");
        jLabel3.setOpaque(true);
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 300, 200, 30));

        jLabel4.setBackground(new java.awt.Color(105, 0, 0));
        jLabel4.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Password");
        jLabel4.setOpaque(true);
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 350, 200, 30));

        jLabel5.setBackground(new java.awt.Color(105, 0, 0));
        jLabel5.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Security Question");
        jLabel5.setOpaque(true);
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 400, 200, 30));

        jLabel6.setBackground(new java.awt.Color(105, 0, 0));
        jLabel6.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Answer");
        jLabel6.setOpaque(true);
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 450, 200, 30));

        sname.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        getContentPane().add(sname, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 250, 380, 30));

        semail.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        getContentPane().add(semail, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 300, 380, 30));

        spass.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        getContentPane().add(spass, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 350, 380, 30));

        security_question.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        security_question.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "What is the name of your first pet?", "Which elementary school did you attend?", "Which is your favourite colour?", "What is name of the town where you were born?" }));
        security_question.setName("jComboBox"); // NOI18N
        security_question.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                security_questionActionPerformed(evt);
            }
        });
        getContentPane().add(security_question, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 400, 380, 30));

        ans.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        getContentPane().add(ans, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 450, 380, 30));

        signup.setBackground(new java.awt.Color(105, 0, 0));
        signup.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        signup.setForeground(new java.awt.Color(255, 255, 255));
        signup.setText("Signup");
        signup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupActionPerformed(evt);
            }
        });
        getContentPane().add(signup, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 550, -1, 30));

        jButton2.setBackground(new java.awt.Color(105, 0, 0));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Already have an accout?");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 550, -1, 30));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/close.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 20, 31, 32));

        jButton3.setBackground(new java.awt.Color(105, 0, 0));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Forgot Password");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 550, -1, 30));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/GuestVission.jpg"))); // NOI18N
        jLabel7.setText("jLabel7");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(-110, -80, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new AdminLogin().setExtendedState(new AdminLogin().getExtendedState() | JFrame.MAXIMIZED_BOTH);;
        new AdminLogin().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void security_questionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_security_questionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_security_questionActionPerformed

    private void signupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupActionPerformed
        // TODO add your handling code here:
        if(sname.getText().isEmpty() || spass.getPassword().length == 0 || semail.getText().isEmpty() || ans.getText().isEmpty()) {      
        JOptionPane.showMessageDialog(null, "Please fill all fields.");
        return;
    }
    
    if(evt.getSource() == signup) {
        String name = sname.getText();
        String password = new String(spass.getPassword()); 
        String email = semail.getText();
        String securityquestion = (String) security_question.getSelectedItem();
        String answer = ans.getText();
        String roomdefault = "0";
        String stay= "Booked";

        // Using PreparedStatement to avoid SQL injection
        String query = "INSERT INTO signup (name, email, password, security_question, answer, room, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            ConnectionProvider c = new ConnectionProvider();
            PreparedStatement ps = c.c.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, securityquestion);
            ps.setString(5, answer);
            ps.setString(6, roomdefault);
            ps.setString(7, stay);
            
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Signup Successfully!");
                new AdminLogin().setVisible(true);
                this.dispose(); // Close the current frame
            }
        } catch (Exception e) {    
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Signup failed! Please try again.");
        }
    }
    }//GEN-LAST:event_signupActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(null, "Do you really want to Exit?", "Select", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            System.exit(0);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        new forgotPassword().setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                signup frame = new signup();
                frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
                frame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ans;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JComboBox<String> security_question;
    private javax.swing.JTextField semail;
    private javax.swing.JButton signup;
    private javax.swing.JTextField sname;
    private javax.swing.JPasswordField spass;
    // End of variables declaration//GEN-END:variables
}
