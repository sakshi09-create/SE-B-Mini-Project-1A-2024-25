package ASimulatorSystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class MiniStatement extends JFrame implements ActionListener {

    JButton b1;
    JLabel l2, l3, l4;
    JTable table;
    DefaultTableModel model;

    MiniStatement(String pin) {
        super("Mini Statement");
        getContentPane().setBackground(Color.WHITE);
        setSize(400, 600);
        setLocation(20, 20);
 
        Color customColor = new Color(232, 147, 31);
        // Header labels
        l2 = new JLabel("Bank of India");
        l2.setBounds(150, 20, 100, 20);
        add(l2);

        l3 = new JLabel();
        l3.setBounds(20, 80, 300, 20);
        add(l3);

        l4 = new JLabel();
        l4.setBounds(20, 400, 300, 20);
        add(l4);

        // Create a table to show transaction data
        String[] columns = {"Date", "Type", "Amount"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setBorder(new LineBorder(Color.BLACK, 1));
        table.setGridColor(Color.BLACK);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 140, 350, 200);
        add(sp);

        try {
            // Fetch card number
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from login where pin = '" + pin + "'");
            if (rs.next()) {
                l3.setText("Card Number:    " + rs.getString("cardnumber").substring(0, 4) + "XXXXXXXX" + rs.getString("cardnumber").substring(12));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            int balance = 0;
            Conn c1 = new Conn();
            ResultSet rs = c1.s.executeQuery("SELECT * FROM bank where pin = '" + pin + "'");
            while (rs.next()) {
                String date = rs.getString("date");
                String type = rs.getString("type");
                String amount = rs.getString("amount");

                // Add data to table
                model.addRow(new Object[]{date, type, amount});

                // Update balance
                if (type.equals("Deposit")) {
                    balance += Integer.parseInt(amount);
                } else {
                    balance -= Integer.parseInt(amount);
                }
            }
            l4.setText("Your total Balance is Rs " + balance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Layout and button setup
        setLayout(null);
        b1 = new JButton("Exit");
        b1.setBackground(customColor);
        b1.setForeground(Color.WHITE);
        b1.setBounds(20, 500, 100, 25);
        b1.addActionListener(this);
        add(b1);
    }

    public void actionPerformed(ActionEvent ae) {
        this.setVisible(false);
    }

    public static void main(String[] args) {
        new MiniStatement("").setVisible(true);
    }
}
