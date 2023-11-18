import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ATM extends JFrame implements ActionListener {
    private JTextField userInput;
    private JPasswordField pinInput;
    private JButton enterButton;
    private JLabel messageLabel;
    private double accountBalance = 1000.0; 
    private String correctPin = "1234"; 

    public ATM() {
        setTitle("ATM Machine");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 3));

        JLabel userLabel = new JLabel("Account Number:");
        panel.add(userLabel);

        userInput = new JTextField();
        panel.add(userInput);

        JLabel pinLabel = new JLabel("PIN:");
        panel.add(pinLabel);

        pinInput = new JPasswordField();
        panel.add(pinInput);

        enterButton = new JButton("Enter");
        enterButton.addActionListener(this);
        panel.add(enterButton);

        messageLabel = new JLabel("");
        panel.add(messageLabel);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enterButton) {
            String enteredPin = new String(pinInput.getPassword());
            String enteredUser = userInput.getText();

            if (enteredUser.isEmpty() || enteredPin.isEmpty()) {
                messageLabel.setText("Please enter Account Number and PIN.");
            } else {
                if (enteredPin.equals(correctPin)) {
                    openMenu();
                } else {
                    messageLabel.setText("Incorrect PIN. Try again.");
                }
            }
        }
    }

    private void openMenu() {
        JFrame menuFrame = new JFrame("ATM Menu");
        menuFrame.setSize(600, 400);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(3, 1));

        JButton balanceButton = new JButton("Check Balance");
        balanceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Your balance is: Rs" + accountBalance);
            }
        });
        menuPanel.add(balanceButton);

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String depositAmountString = JOptionPane.showInputDialog(null, "Enter amount to deposit:");
                try {
                    double depositAmount = Double.parseDouble(depositAmountString);
                    if (depositAmount > 0) {
                        accountBalance += depositAmount;
                        JOptionPane.showMessageDialog(null, "Deposit successful. New balance: $" + accountBalance);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter a valid deposit amount.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                }
            }
        });
        menuPanel.add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String withdrawAmountString = JOptionPane.showInputDialog(null, "Enter amount to withdraw:");
                try {
                    double withdrawAmount = Double.parseDouble(withdrawAmountString);
                    if (withdrawAmount > 0 && withdrawAmount <= accountBalance) {
                        accountBalance -= withdrawAmount;
                        JOptionPane.showMessageDialog(null, "Withdrawal successful. New balance: $" + accountBalance);
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient funds or invalid amount.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number.");
                }
            }
        });
        menuPanel.add(withdrawButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();
            }
        });
        menuPanel.add(exitButton);

        menuFrame.add(menuPanel);
        menuFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new ATM();
    }
}
