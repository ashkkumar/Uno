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
    private JLabel colourStatus;

    private UnoGameController controller;

    private Card startingCard;

    private boolean firstRound;

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
        scrollPane.getHorizontalScrollBar().setUnitIncrement(25);
        startingCard = model.getStartingCard();
        firstRound = true;
        String startCard = model.getStartingCard().toString();
        String imagePath = "src/images/" + startCard +".jpg";
        ImageIcon icon = new ImageIcon(imagePath);
        topCard = new JButton(icon);
        topCard.putClientProperty("card", model.getStartingCard());
        nextButton = new JButton("Next Player");
        drawButton = new JButton("Draw Card");
        nextButton.setActionCommand("nextPlayer");
        drawButton.setActionCommand("draw");
        currentPlayerLabel = new JLabel("Player " + (model.getCurrentPlayer().getName()) + "'s turn");

        playStatus = new JLabel("Please select a card");
        colourStatus = new JLabel("Active Colour:" + model.getStartingCard().getColour().name());

        // Add components to the frame and layout configuration
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.SOUTH);
        this.add(topCard, BorderLayout.CENTER);


        // Status Pane Components and Creation
        statusPane = new JPanel();
        statusPane.setLayout(new BoxLayout(statusPane,BoxLayout.Y_AXIS));
        currentPlayerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        playStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
        colourStatus.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusPane.add(currentPlayerLabel);
        statusPane.add(playStatus);
        statusPane.add(colourStatus);
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
        model.checkActionCard();
        updateView();
        checkStartCard();
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
        System.exit(0);
        return 0;
    }

    private void askWildCard(Card card) {
        String[] playerOptions = { "BLUE", "YELLOW","RED", "GREEN"};
        JComboBox<String> playerDropdown = new JComboBox<>(playerOptions);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Select desired colour:"));
        panel.add(playerDropdown);

        int result = JOptionPane.showConfirmDialog(null, panel, "Number of Players", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            topCard.setIcon(new ImageIcon(card.getImageFilePath()));
            controller.playWild(card, Card.Colour.valueOf((String) playerDropdown.getSelectedItem()));
            updateView();
            if (card.getCardType() == Card.CardType.WILD) {
                updatePlayStatus("Colour has been changed!");
            } else{
                updatePlayStatus("Colour changed and next player draws 2/skips!");
            }

        }
    }

    public void checkStartCard(){
        Card card = startingCard;
        if (card.getCardType() == Card.CardType.DRAW_ONE){
            nextButton.setEnabled(true);
            drawButton.setEnabled(false);
        } else if (card.getCardType() == Card.CardType.SKIP){
            nextButton.setEnabled(true);
            drawButton.setEnabled(false);
        }
    }

    public void updatePlayerTurnLabel(){
        currentPlayerLabel.setText("Player "+ (model.getCurrentPlayer().getName())  + "'s turn");
    }

    public void updatePlayStatus(String status){
        playStatus.setText(status);
    }

    public void updateColourStatus(){
        colourStatus.setText("Colour: " + model.getTopColour().name());
    }

    public void updateView() {
        // change player's hand, top card, and other components
        playerHandPane.removeAll();

        ArrayList<Card> playerHand = controller.getCurrentPlayer().getMyCards();
        updatePlayerTurnLabel();
        updatePlayStatus("Please select a card");
        updateColourStatus();

        if (!controller.hasDrawn()){
            drawButton.setEnabled(true);
        } else{
            drawButton.setEnabled(false);
        }

        if (controller.getCurrentPlayer().canPlay() && !controller.getCurrentPlayer().getHasDrawn()){
            nextButton.setEnabled(false);
        } else{
            nextButton.setEnabled(true);
            drawButton.setEnabled(false);
        }

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
            if (!controller.getCurrentPlayer().canPlay()){
                cardButton.setEnabled(false);
            }
            if (controller.getIndex() == 0 && startingCard.getCardType() == Card.CardType.DRAW_ONE && firstRound  ||
                    controller.getIndex() == 0 && startingCard.getCardType() == Card.CardType.SKIP && firstRound){
                cardButton.setEnabled(false);
            }
            cardButton.setVisible(true);
            playerHandPane.add(cardButton);
        }
        // Repaint the player hand panel
        playerHandPane.revalidate();
        playerHandPane.repaint();
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
        if (card.getColour() == Card.Colour.WILD){
            askWildCard(card);
        } else if (controller.playCard(card)) {
            ImageIcon icon = new ImageIcon(card.getImageFilePath());
            topCard.setIcon(icon);
            updateView();
            if (card.getCardType() == Card.CardType.SKIP) {
                updatePlayStatus("Skipping Next Player's Turn!");
            } else if (card.getCardType() == Card.CardType.DRAW_ONE){
                updatePlayStatus("Next player draws and skips turn!");
            } else if (card.getCardType() == Card.CardType.REVERSE){
                updatePlayStatus("Order of players reversed!");
            } else{
                updatePlayStatus("Good move");
            }
        }
        else {
            updatePlayStatus("Invalid Move");
        }

    }

    @Override
    public void handleNextTurn(ActionEvent e) {
        firstRound = false;
        controller.actionPerformed(e);
        updateView();
    }
}