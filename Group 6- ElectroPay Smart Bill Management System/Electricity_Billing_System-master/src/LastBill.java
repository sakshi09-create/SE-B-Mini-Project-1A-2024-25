import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class LastBill extends JFrame implements ActionListener {
    JLabel l1;
    JTextArea t1;
    JButton b1;
    Choice c1;
    JPanel p1;

    LastBill() {
        setSize(500, 900);
        setLayout(new BorderLayout());

        p1 = new JPanel();

        l1 = new JLabel("Generate Bill");

        c1 = new Choice();
        try {
            conn c = new conn();
            String query = "SELECT meter_number FROM emp";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                c1.add(rs.getString("meter_number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        t1 = new JTextArea(50, 15);
        JScrollPane jsp = new JScrollPane(t1);
        t1.setFont(new Font("Senserif", Font.ITALIC, 18));

        b1 = new JButton("Generate Bill");

        p1.add(l1);
        p1.add(c1);
        add(p1, "North");
        add(jsp, "Center");
        add(b1, "South");

        b1.addActionListener(this);

        setLocation(350, 40);
    }

    public void actionPerformed(ActionEvent ae) {
        t1.setText("");
        try {
            conn c = new conn();

            String meterNumber = c1.getSelectedItem();
            ResultSet rs = c.s.executeQuery("SELECT * FROM emp WHERE meter_number='" + meterNumber + "'");

            if (rs.next()) {
                t1.append("\n    Customer Name: " + rs.getString("name"));
                t1.append("\n    Meter Number:  " + rs.getString("meter_number"));
                t1.append("\n    Address:            " + rs.getString("address"));
                t1.append("\n    State:                 " + rs.getString("state"));
                t1.append("\n    City:                   " + rs.getString("city"));
                t1.append("\n    Email:                " + rs.getString("email"));
                t1.append("\n    Phone Number:  " + rs.getString("phone"));
                t1.append("\n-------------------------------------------------------------\n");
            }

            t1.append("\nDetails of the Last Bills\n\n");

            rs = c.s.executeQuery("SELECT * FROM bill WHERE meter_number='" + meterNumber + "'");
            while (rs.next()) {
                t1.append("       " + rs.getString("month") + "           " + rs.getString("amount") + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LastBill().setVisible(true);
    }
}