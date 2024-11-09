/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package crmapplication;

/**
 *
 * @author Rugved
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {
    private JTextField userField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPage() {

        // Frame settings for full screen
        setTitle("Login Page");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the window to full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel
        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("User:");
        userLabel.setBounds(100, 100, 80, 25);
        panel.add(userLabel);

        userField = new JTextField(20);
        userField.setBounds(200, 100, 160, 25);
        panel.add(userField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(100, 140, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(200, 140, 160, 25);
        panel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 200, 250, 25);
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Here you could add authentication logic
                new Dashboard();
                dispose(); // Close Login page
            }
        });
    }
}
