import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UnoGameView extends JFrame implements UnoViewHandler {
    private UnoGameModel model;
    private JPanel playerHandPane;
    private JButton topCard;
    private JButton nextButton;
    private JButton drawButton;

    public UnoGameView(UnoGameModel model) {
        this.model = model;

        setTitle("Uno Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);

        // Create and configure components (buttons, labels, etc.)
        playerHandPane = new JPanel();
        ImageIcon icon = new ImageIcon("src/images/BLUE_REVERSE.jpg");
        topCard = new JButton(icon);
        nextButton = new JButton("Next Player");
        drawButton = new JButton("Draw Card");
        drawButton.setActionCommand("nextPlayer");

        // Add components to the frame and layout configuration
        this.setLayout(new BorderLayout());
        this.add(playerHandPane, BorderLayout.SOUTH);
        this.add(topCard, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(nextButton);
        buttonPanel.add(drawButton);
        this.add(buttonPanel, BorderLayout.NORTH);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UnoGameEvent unoEvent = new UnoGameEvent(model);
                handleNextTurn(unoEvent);
                updateView();
            }
        });

        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UnoGameEvent unoEvent = new UnoGameEvent(model);
                handleDrawCard(unoEvent);
            }
        });

        askNumberOfPlayers();
        setVisible(true);
    }

    public void updateView() {
        // change player's hand, top card, and other components

        playerHandPane.removeAll();

        ArrayList<Card> playerHand = model.getCurrentPlayer().getMyCards();

        for (Card card: playerHand) {
            String cardName = card.toString();
            String imagePath = "src/images/" +cardName +".jpg";
            ImageIcon icon = new ImageIcon(imagePath);
            JButton cardButton = new JButton(icon);
            //cardButton.setMargin(new Insets(0, 0, 0, 0));
            cardButton.setVisible(true);
            playerHandPane.add(cardButton);
        }
        // Repaint the player hand panel
        playerHandPane.revalidate();
        playerHandPane.repaint();
    }


    private int askNumberOfPlayers() {
        Integer[] playerOptions = { 2, 3, 4 };
        JComboBox<Integer> playerDropdown = new JComboBox<>(playerOptions);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Select number of players:"));
        panel.add(playerDropdown);

        int result = JOptionPane.showConfirmDialog(null, panel, "Number of Players", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            int numberOfPlayers = (int) playerDropdown.getSelectedItem();
            model = new UnoGameModel();
        }
        else {
            // Handle if the user cancels the selection
            // For example, close the program or take appropriate action
        }

        return result;
    }

    public void setPlayButtonEnabled(boolean enabled) {
        nextButton.setEnabled(enabled);
    }

    public void setDrawButtonEnabled(boolean enabled) {
        drawButton.setEnabled(enabled);
    }

    public void displayMessage(String message) {
        // Implement how you want to display messages to the user
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void handleDrawCard(UnoGameEvent e) {
        //updateView();

    }

    @Override
    public void handlePlay(UnoGameEvent e) {
        // Notify the controller about the play event
        /*
        Card selectedCard = e.getCard(); // Assuming UnoGameEvent provides the card to be played

        // Check if the selected card can be played
        if (model.isCardPlayable(selectedCard)) {
            model.playCard(selectedCard); // Update the game model, remove the card from player's hand, etc.

            // Implement further actions based on the played card (if required)
            if (model.isGameOver()) {
                // Check if the game is over after this play
                displayMessage("Game Over! Some player has won.");
                // Implement further actions for end of game
            } else {
                handleNextTurn(new UnoGameEvent(model)); // Move to the next player's turn
                updateView(); // Update the view after the play
            }
        } else {
            displayMessage("Cannot play this card!"); // Inform the player that the selected card is not playable
        }
         */

    }

    @Override
    public void handleUnoCall(UnoGameEvent e) {
        // Notify the controller about the Uno call event
    }

    @Override
    public void handleNextTurn(UnoGameEvent e) {
        // Notify the controller about the next turn event
    }
}
