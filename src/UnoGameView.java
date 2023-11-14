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

    private JPanel statusPane;
    private JLabel currentPlayerLabel;

    private JLabel playStatus;

    private UnoGameController controller;

    private int selectedPlayers;

    public UnoGameView() {
        this.model = new UnoGameModel(askNumberOfPlayers());
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
        currentPlayerLabel = new JLabel("Player " + (model.getCurrentPlayer().getName()) + "'s turn");

        playStatus = new JLabel("Please select a card");

        // Add components to the frame and layout configuration
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.SOUTH);
        this.add(topCard, BorderLayout.CENTER);


        // Status Pane Components and Creation
        statusPane = new JPanel();
        statusPane.setLayout(new BoxLayout(statusPane,BoxLayout.Y_AXIS));
        currentPlayerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusPane.add(currentPlayerLabel);
        statusPane.add(playStatus);
        this.add(statusPane,BorderLayout.WEST);

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
                updateView();
            }
        });

        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDrawCard(e);
            }
        });
        updateView();
        setVisible(true);
    }

    private int askNumberOfPlayers() {
        Integer[] playerOptions = { 2, 3, 4 };
        JComboBox<Integer> playerDropdown = new JComboBox<>(playerOptions);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Select number of players:"));
        panel.add(playerDropdown);

        int result = JOptionPane.showConfirmDialog(null, panel, "Number of Players", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            return (int) playerDropdown.getSelectedItem();
        }
        else {
            return 2;
            // Handle if the user cancels the selection
            // For example, close the program or take appropriate action
        }

    }

    public void updatePlayerTurnLabel(){
        currentPlayerLabel.setText("Player "+ (model.getCurrentPlayer().getName())  + "'s turn");
    }

    public void updatePlayStatus(String status){
        playStatus.setText(status);
    }
    public void updateView() {
        // change player's hand, top card, and other components

        playerHandPane.removeAll();

        ArrayList<Card> playerHand = controller.getCurrentPlayer().getMyCards();
        updatePlayerTurnLabel();
        updatePlayStatus("Please select a card");

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
        updatePlayStatus("Drew One Card");
    }

    @Override
    public void handlePlay(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        Card card = (Card) button.getClientProperty("card");
        if (controller.playCard(card)) {
            ImageIcon icon = new ImageIcon(card.getImageFilePath());
            topCard.setIcon(icon);
            updateView();
            updatePlayStatus("Good Move");
        }
        else{
            updatePlayStatus("Invalid Move");
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