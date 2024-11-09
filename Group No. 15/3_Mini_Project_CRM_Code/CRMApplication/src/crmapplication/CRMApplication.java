/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package crmapplication;
/**
 *
 * @author Rugved
 */
import javax.swing.*;

public class CRMApplication {
    public static void main(String[] args) {
        // Set up Login Page as the first page to display
        SwingUtilities.invokeLater(LoginPage::new);
    }
}
