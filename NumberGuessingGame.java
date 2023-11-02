import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame {
    private int lowerBound = 1;
    private int upperBound = 100;
    private int numberToGuess;
    private int maxAttempts = 10;
    private int attempts = 0;

    private JLabel label;
    private JTextField textField;
    private JButton guessButton;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game By Anshul");
        setSize(300, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        Random random = new Random();
        numberToGuess = random.nextInt(upperBound - lowerBound + 1) + lowerBound;

        label = new JLabel("Enter your guess Number :\n");
        textField = new JTextField(15);
        guessButton = new JButton("Guess\n");

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int userGuess = Integer.parseInt(textField.getText());
                attempts++;

                if (userGuess == numberToGuess) {
                    JOptionPane.showMessageDialog(NumberGuessingGame.this,
                            "Congratulations! You guessed the number in " + attempts + " attempts.");
                    resetGame();
                } else if (userGuess < numberToGuess) {
                    JOptionPane.showMessageDialog(NumberGuessingGame.this, "Try a higher number.");
                } else {
                    JOptionPane.showMessageDialog(NumberGuessingGame.this, "Try a lower number.");
                }

                if (attempts >= maxAttempts) {
                    JOptionPane.showMessageDialog(NumberGuessingGame.this,
                            "Sorry, you've run out of attempts. The number was " + numberToGuess + ".");
                    resetGame();
                }
            }
        });

        add(label);
        add(textField);
        add(guessButton);

        setVisible(true);
    }

    private void resetGame() {
        Random random = new Random();
        numberToGuess = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
        attempts = 0;
    }

    public static void main(String[] args) {
        new NumberGuessingGame();
    }
}
