import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UnoGameView extends JFrame {
    private UnoGameModel model;
    private JPanel playerHandPane;
    private JLabel topCardLabel;
    private JButton nextButton;
    private JButton drawButton;

    public UnoGameView() {
        this.model = new UnoGameModel();

        setTitle("Uno Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);

        // Create and configure components (buttons, labels, etc.)
        playerHandPane = new JPanel();
        topCardLabel = new JLabel("Top card");
        nextButton = new JButton("Next Player");
        drawButton = new JButton("Draw Card");

        // Add components to the frame and layout configuration
        this.setLayout(new BorderLayout());
        this.add(playerHandPane, BorderLayout.SOUTH);
        this.add(topCardLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(nextButton);
        buttonPanel.add(drawButton);
        this.add(buttonPanel, BorderLayout.NORTH);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handlePlayButtonClick();
            }
        });

        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDrawButtonClick();
            }
        });

        setVisible(true);
    }

    public void updateView() {
        // change  player's hand, top card, and other components

        playerHandPane.removeAll();

        ArrayList<Card> playerHand = model.getCurrentPlayer().getMyCards();

        for (Card card : playerHand) {

            JButton cardButton = new JButton(card.toString());
            cardButton.setVisible(true);
            playerHandPane.add(cardButton);
        }
        // Repaint the player hand panel
        playerHandPane.revalidate();
        playerHandPane.repaint();
    }

    public void setPlayButtonEnabled(boolean enabled) {
        nextButton.setEnabled(enabled);
    }

    public void setDrawButtonEnabled(boolean enabled) {
        drawButton.setEnabled(enabled);
    }

    public void displayMessage(String message) {
    }

    private void handlePlayButtonClick() {
        updateView();
    }

    private void handleDrawButtonClick() {

    }

}

