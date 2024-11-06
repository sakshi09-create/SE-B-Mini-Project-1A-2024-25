import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DummyPaymentPortal extends JFrame implements ActionListener {

    private JTextField cardNumberField, cvvField, amountField, upiField;
    private JButton payButton;
    private JLabel statusLabel;
    private JRadioButton cardOption, upiOption;
    private ButtonGroup paymentGroup;

    public DummyPaymentPortal() {
        setLayout(new GridLayout(8, 2, 10, 10));

        cardOption = new JRadioButton("Pay with Card");
        upiOption = new JRadioButton("Pay with UPI");
        paymentGroup = new ButtonGroup();
        paymentGroup.add(cardOption);
        paymentGroup.add(upiOption);
        cardOption.setSelected(true);

        add(new JLabel("Payment Method:"));
        JPanel paymentOptionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        paymentOptionsPanel.add(cardOption);
        paymentOptionsPanel.add(upiOption);
        add(paymentOptionsPanel);

        add(new JLabel("Card Number:"));
        cardNumberField = new JTextField();
        add(cardNumberField);

        add(new JLabel("CVV:"));
        cvvField = new JTextField();
        add(cvvField);

        add(new JLabel("UPI ID:"));
        upiField = new JTextField();
        upiField.setEnabled(false);
        add(upiField);

        add(new JLabel("Amount:"));
        amountField = new JTextField();
        add(amountField);

        payButton = new JButton("Pay Now");
        add(payButton);

        statusLabel = new JLabel("", SwingConstants.CENTER);
        add(statusLabel);

        payButton.addActionListener(this);
        cardOption.addActionListener(this);
        upiOption.addActionListener(this);

        setTitle("Payment Portal");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cardOption) {
            cardNumberField.setEnabled(true);
            cvvField.setEnabled(true);
            upiField.setEnabled(false);
            upiField.setText("");
        }

        if (e.getSource() == upiOption) {
            cardNumberField.setEnabled(false);
            cvvField.setEnabled(false);
            cardNumberField.setText("");
            cvvField.setText("");
            upiField.setEnabled(true);
        }

        if (e.getSource() == payButton) {
            String amount = amountField.getText();

            if (cardOption.isSelected()) {
                String cardNumber = cardNumberField.getText();
                String cvv = cvvField.getText();

                if (cardNumber.length() == 16 && cvv.length() == 3 && isNumeric(amount)) {
                    statusLabel.setText("Card Payment Successful!");
                    statusLabel.setForeground(Color.GREEN);
                } else {
                    statusLabel.setText("Card Payment Failed!");
                    statusLabel.setForeground(Color.RED);
                }

            } else if (upiOption.isSelected()) {
                String upiId = upiField.getText();

                if (upiId.contains("@") && isNumeric(amount)) {
                    statusLabel.setText("UPI Payment Successful!");
                    statusLabel.setForeground(Color.GREEN);
                } else {
                    statusLabel.setText("UPI Payment Failed!");
                    statusLabel.setForeground(Color.RED);
                }
            }
        }
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        new DummyPaymentPortal();
    }
}
