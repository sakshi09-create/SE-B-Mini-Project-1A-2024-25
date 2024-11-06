import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Signup extends JFrame implements ActionListener{

    JButton create, cancel;
    Choice accountType;
    JTextField meter, username, name, password;

    Signup() {
        setBounds(450, 150, 700, 400);
        getContentPane().setBackground(new Color(0x3f733a));
        setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(30, 30, 650, 300);
        panel.setBackground(new Color(0x3f733a));
        panel.setLayout(null);
        add(panel);
        
        JLabel heading = new JLabel("Create Account As");
        heading.setBounds(100, 50, 140, 20);
        heading.setForeground(Color.black);
        heading.setFont(new Font("MV Boli", Font.BOLD, 14));
        panel.add(heading);
        
        accountType = new Choice();
        accountType.add("Admin");
        accountType.add("Customer");
        accountType.setBounds(260, 50, 150, 20);
        panel.add(accountType);
        
        JLabel lblmeter = new JLabel("Meter Number");
        lblmeter.setBounds(100, 90, 140, 20);
        lblmeter.setForeground(Color.black);
        lblmeter.setFont(new Font("MV Boli", Font.BOLD, 14));
        lblmeter.setVisible(false);
        panel.add(lblmeter);
        
        meter = new JTextField();
        meter.setBounds(260, 90, 150, 20);
        meter.setVisible(false);
        panel.add(meter);
        
        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(100, 130, 140, 20);
        lblusername.setForeground(Color.black);
        lblusername.setFont(new Font("MV Boli", Font.BOLD, 14));
        panel.add(lblusername);
        
        username = new JTextField();
        username.setBounds(260, 130, 150, 20);
        panel.add(username);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(100, 170, 140, 20);
        lblname.setForeground(Color.black);
        lblname.setFont(new Font("MV Boli", Font.BOLD, 14));
        panel.add(lblname);
        
        name = new JTextField();
        name.setBounds(260, 170, 150, 20);
        panel.add(name);
        
        meter.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent fe) {}
            public void focusLost(FocusEvent fe) {
                try {
                    conn c  = new conn();
                    ResultSet rs = c.s.executeQuery("SELECT * FROM login WHERE meter_no = '"+meter.getText()+"'");
                    while(rs.next()) {
                        name.setText(rs.getString("name"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(100, 210, 140, 20);
        lblpassword.setForeground(Color.black);
        lblpassword.setFont(new Font("MV Boli", Font.BOLD, 14));
        panel.add(lblpassword);
        
        password = new JTextField();
        password.setBounds(260, 210, 150, 20);
        panel.add(password);
        
        accountType.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ae) {
                String user = accountType.getSelectedItem();
                if (user.equals("Customer")) {
                    lblmeter.setVisible(true);
                    meter.setVisible(true);
                    name.setEditable(false);
                } else {
                    lblmeter.setVisible(false);
                    meter.setVisible(false);
                    name.setEditable(true);
                }
            }
        });
        
        create = new JButton("Create");
        create.setBackground(Color.black);
        create.setForeground(Color.white);
        create.setBounds(260, 260, 120, 25);
        create.addActionListener(this);
        panel.add(create);
        
        // Cancel button
        cancel = new JButton("Cancel");
        cancel.setBackground(Color.red);
        cancel.setForeground(Color.white);
        cancel.setBounds(400, 260, 120, 25);
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new login(); // Redirect to login page
            }
        });
        panel.add(cancel);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == create) {
            String atype = accountType.getSelectedItem();
            String susername = username.getText();
            String sname = name.getText();
            String spassword = password.getText();
            String smeter = meter.getText();
           
            try {
                conn c = new conn();
                
                String query = null;
                if (atype.equals("Admin")) {
                    query = "INSERT INTO login (meter_no, username, name, password, role) VALUES (NULL, '"+susername+"', '"+sname+"', '"+spassword+"', '"+atype+"')";
                } else {
                    query = "INSERT INTO login (meter_no, username, name, password, role) VALUES ('"+smeter+"', '"+susername+"', '"+sname+"','"+spassword+"', '"+atype+"')";
                }
                
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Successful");
                setVisible(false);
                new login();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
    }
    public static void main(String[] args) {
        new Signup();
    }
}