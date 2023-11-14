import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UnoGameView extends JFrame implements UnoViewHandler {
    private UnoGameModel model;
    private JPanel playerHandPane;

    private JScrollPane scrollPane;
    private JButton topCard;
    private JButton nextButton;
    private JButton drawButton;
    private JLabel currentPlayerLabel;

    private UnoGameController controller;

    private int selectedPlayers;

    private boolean canPlay = true;

    public UnoGameView() {
        this.model = new UnoGameModel();
        this.controller = new UnoGameController(model);
        setTitle("Uno Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);

        // Create and configure components (buttons, labels, etc.)
        playerHandPane = new JPanel();
        scrollPane = new JScrollPane(playerHandPane);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        String startCard = model.getStartingCard().toString();
        String imagePath = "src/images/" + startCard +".jpg";
        ImageIcon icon = new ImageIcon(imagePath);
        topCard = new JButton(icon);
        nextButton = new JButton("Next Player");
        drawButton = new JButton("Draw Card");
        nextButton.setActionCommand("nextPlayer");
        drawButton.setActionCommand("draw");
        currentPlayerLabel = new JLabel();

        // Add components to the frame and layout configuration
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.SOUTH);
        this.add(topCard, BorderLayout.CENTER);
        this.add(currentPlayerLabel, BorderLayout.WEST);


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(nextButton);
        buttonPanel.add(drawButton);
        this.add(buttonPanel, BorderLayout.NORTH);

        //next and draw buttons
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UnoGameEvent unoEvent = new UnoGameEvent(model);
                handleNextTurn(e);
                updatePlayerTurnLabel();
                updateView();
            }
        });

        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDrawCard(e);
            }
        });
        updatePlayerTurnLabel();
        updateView();
        setVisible(true);
    }

    public void updatePlayerTurnLabel(){
        currentPlayerLabel.setText("Player "+ (model.getCurrentPlayerIndex()) + "'s turn");
    }

    public void updateView() {
        // change player's hand, top card, and other components

        playerHandPane.removeAll();

        ArrayList<Card> playerHand = controller.getCurrentPlayer().getMyCards();

        for (Card card: playerHand) {

            ImageIcon icon = new ImageIcon(card.getImageFilePath());
            JButton cardButton = new JButton(icon);
            cardButton.putClientProperty("card", card);
            cardButton.setActionCommand("play");
            cardButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handlePlay(e);
                }
            });

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
    public void handleDrawCard(ActionEvent e) {
        controller.actionPerformed(e);
        updateView();
    }

    @Override
    public void handlePlay(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        Card card = (Card) button.getClientProperty("card");
        if (controller.playCard(card)) {
            ImageIcon icon = new ImageIcon(card.getImageFilePath());
            topCard.setIcon(icon);
            updateView();
        }
        else{
            System.out.println("Invalid move");
        }

    }

    @Override
    public void handleUnoCall(UnoGameEvent e) {
        // Notify the controller about the Uno call event
    }

    @Override
    public void handleNextTurn(ActionEvent e) {
        controller.actionPerformed(e);
        updateView();
    }
}