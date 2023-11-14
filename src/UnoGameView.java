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
