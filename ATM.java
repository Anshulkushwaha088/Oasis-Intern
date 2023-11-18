import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class ATM extends JFrame implements ActionListener {
    private JTextField userIdInput;
    private JPasswordField passwordInput;
    private JButton loginButton;
    private JLabel messageLabel;
    private String currentUserId;
    private double balance = 1000.00; // Initial balance, you can modify this
    private ArrayList<String> transactionHistory;
    private Map<String, String> users; // For simplicity, using a Map as a user database (you can replace this with a database)

    public ATM() {
        setTitle("ATM Machine");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel userIdLabel = new JLabel("User ID:");
        panel.add(userIdLabel);

        userIdInput = new JTextField();
        panel.add(userIdInput);

        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel);

        passwordInput = new JPasswordField();
        panel.add(passwordInput);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        panel.add(loginButton);

        messageLabel = new JLabel("");
        panel.add(messageLabel);

        add(panel);
        setVisible(true);

        users = new HashMap<>();
        users.put("ANSH8756", "956915");
        users.put("VISH790", "875653");

        transactionHistory = new ArrayList<>();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String enteredUserId = userIdInput.getText();
            String enteredPassword = new String(passwordInput.getPassword());

            if (users.containsKey(enteredUserId) && users.get(enteredUserId).equals(enteredPassword)) {
                currentUserId = enteredUserId;
                showTransactionMenu();
            } else {
                messageLabel.setText("Invalid User ID or Password.");
            }
        }
    }

    private void showTransactionMenu() {
        getContentPane().removeAll();
        getContentPane().setLayout(new GridLayout(5, 1));

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(e -> performWithdraw());
        add(withdrawButton);

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(e -> performDeposit());
        add(depositButton);

        JButton transferButton = new JButton("Transfer");
        transferButton.addActionListener(e -> performTransfer());
        add(transferButton);

        JButton historyButton = new JButton("Transaction History");
        historyButton.addActionListener(e -> displayTransactionHistory());
        add(historyButton);

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(e -> System.exit(0));
        add(quitButton);

        revalidate();
    }

    private void performWithdraw() {
        String amountString = JOptionPane.showInputDialog("Enter amount to withdraw:");
        try {
            double amount = Double.parseDouble(amountString);
            if (amount <= 0 || amount > balance) {
                JOptionPane.showMessageDialog(this, "Invalid amount or insufficient balance.");
            } else {
                balance -= amount;
                transactionHistory.add("Withdrawal: -Rs" + amount);
                JOptionPane.showMessageDialog(this, "Successfully withdrew Rs" + amount);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid amount.");
        }
    }

    private void performDeposit() {
        String amountString = JOptionPane.showInputDialog("Enter amount to deposit:");
        try {
            double amount = Double.parseDouble(amountString);
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Invalid amount.");
            } else {
                balance += amount;
                transactionHistory.add("Deposit: +Rs" + amount);
                JOptionPane.showMessageDialog(this, "Successfully deposited Rs" + amount);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid amount.");
        }
    }

    private void performTransfer() {
        String recipient = JOptionPane.showInputDialog("Enter recipient's user ID:");
        String amountString = JOptionPane.showInputDialog("Enter amount to transfer:");
        try {
            double amount = Double.parseDouble(amountString);
            if (amount <= 0 || amount > balance) {
                JOptionPane.showMessageDialog(this, "Invalid amount or insufficient balance.");
            } else {
                balance -= amount;
                transactionHistory.add("Transfer to " + recipient + ": -Rs" + amount);
                JOptionPane.showMessageDialog(this, "Successfully transferred Rs" + amount + " to " + recipient);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid amount.");
        }
    }

    private void displayTransactionHistory() {
        StringBuilder history = new StringBuilder("Transaction History:\n");
        for (String transaction : transactionHistory) {
            history.append(transaction).append("\n");
        }
        JOptionPane.showMessageDialog(this, history.toString());
    }

    public static void main(String[] args) {
        new ATM();
    }
}
