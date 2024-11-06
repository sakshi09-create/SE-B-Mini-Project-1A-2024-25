import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class login extends JFrame implements ActionListener {
    JLabel l1, l2, l3;
    JTextField tf1;
    JPasswordField pf2;
    JComboBox<String> roleComboBox;
    JButton b1, b2, b3;
    JPanel p2, p4;

    login() {
        super("Login Page");

        l1 = new JLabel("UserName");
        l2 = new JLabel("Password");
        tf1 = new JTextField(15);
        pf2 = new JPasswordField(15);

        String[] roles = {"Admin", "Customer"};
        roleComboBox = new JComboBox<>(roles);

        ImageIcon ic1 = new ImageIcon(ClassLoader.getSystemResource("images/login_icon.png"));
        Image i1 = ic1.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        b1 = new JButton("Login", new ImageIcon(i1));

        ImageIcon ic2 = new ImageIcon(ClassLoader.getSystemResource("images/cancel_icon.png"));
        Image i2 = ic2.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        b2 = new JButton("Cancel", new ImageIcon(i2));
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        ImageIcon ic3 = new ImageIcon(ClassLoader.getSystemResource("images/signup-icon.png"));
        Image i3 = ic3.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        b3 = new JButton("Signup", new ImageIcon(i3));
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Signup().setVisible(true);
                dispose();
            }
        });

        b1.addActionListener(this);

        ImageIcon ic4 = new ImageIcon(ClassLoader.getSystemResource("images/login_gif.gif"));
        Image i4 = ic4.getImage().getScaledInstance(340, 370, Image.SCALE_DEFAULT);
        l3 = new JLabel(new ImageIcon(i4));

        setLayout(new BorderLayout());

        p2 = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        p2.add(l1, gbc);

        gbc.gridy = 1;
        p2.add(tf1, gbc);

        gbc.gridy = 2;
        p2.add(l2, gbc);

        gbc.gridy = 3;
        p2.add(pf2, gbc);

        gbc.gridy = 4;
        p2.add(roleComboBox, gbc);

        p4 = new JPanel();
        p4.add(b1);
        p4.add(b2);
        p4.add(b3);

        add(l3, BorderLayout.WEST);
        add(p2, BorderLayout.CENTER);
        add(p4, BorderLayout.SOUTH);

        p2.setBackground(Color.WHITE);
        p4.setBackground(Color.WHITE);

        setSize(640, 450);
        setLocation(600, 400);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            try {
                conn c1 = new conn();
                String a = tf1.getText();
                String b = new String(pf2.getPassword());
                String role = (String) roleComboBox.getSelectedItem();
                String q = "select * from login where username = '" + a + "' and password = '" + b + "' and role = '" + role + "'";
                ResultSet rs = c1.s.executeQuery(q);
                if (rs.next()) {
                    new Project().setVisible(true);
                    this.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid login");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new login().setVisible(true);
    }
}
