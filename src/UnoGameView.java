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
    /**
     * Constructs the UnoGameView, initializes the UI components, and sets up event listeners.
     */
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
        String imagePath = "images/" + startCard +".jpg";
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
    /**
     * Asks the user to select the number of players before starting the game.
     *
     * @return The result of the user selection.
     */
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
    /**
     * Asks the user to select the colour of the wild car they are playing
     */
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

    /**
     * Checks the starting card at the beginning of the game
     */
    public void checkStartCard(){

       if (startingCard.getColour() == Card.Colour.WILD){
            nextButton.setEnabled(true);
            drawButton.setEnabled(false);
            controller.getCurrentPlayer().setCanPlay(false);
            updatePlayStatus("Player 1 Started with wild card!");
            askWildCard(startingCard);
        }
       /*
       else if (startingCard.getCardType() == Card.CardType.SKIP);
            nextButton.setEnabled(true);
            drawButton.setEnabled(false);
            controller.getCurrentPlayer().setCanPlay(false);
        }else if (startingCard.getCardType() == Card.CardType.DRAW_ONE){
           //nextButton.setEnabled(false);
           //drawButton.setEnabled(false);
           controller.getCurrentPlayer().setCanPlay(false);
           controller.setHasDrawn();
           updatePlayStatus("Player 1 must draw a card!");
           updateView();
       }
       */


    }

    /**
     * Shows a popup that congratulates the winner of the game
     */
    private void showWinnerPopup() {
        String message = "Congratulations, player " + model.getCurrentPlayer().getName() + "! You are the winner!";
        JOptionPane.showMessageDialog(null, message, "Winner!", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Shows a popup that tells the users to continue playing because winner requirements
     * were not met
     */
    private void showKeepPlayingPopup(){
        String message = "Not enough points scored! Keep playing!";
        JOptionPane.showMessageDialog(null, message, "Continue Game", JOptionPane.INFORMATION_MESSAGE);

    }
    /**
     * Updates the label displaying the current player's turn.
     */
    public void updatePlayerTurnLabel(){
        currentPlayerLabel.setText("Player "+ (model.getCurrentPlayer().getName())  + "'s turn");

    }
    /**
     * Updates the label displaying the play status.
     *
     * @param status The status message to be displayed.
     */
    public void updatePlayStatus(String status){
        playStatus.setText(status);
    }
    /**
     * Updates the label displaying the colour status.
     *
     */
    public void updateColourStatus(){
        colourStatus.setText("Colour: " + model.getTopColour().name());
    }

    /**
     * Updates the view of the Uno game. Refreshes the player's hand, top card, and other components
     * based on the current state of the game. Enables or disables buttons as necessary and sets up
     * action listeners for player card buttons. Repaints the player hand panel to reflect the changes.
     */
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
            cardButton.setVisible(true);
            playerHandPane.add(cardButton);
        }
        // Repaint the player hand panel
        playerHandPane.revalidate();
        playerHandPane.repaint();
    }

    /**
     * Handles the draw card action. Invokes the corresponding action in the controller,
     * updates the view, and displays a message indicating that the player drew one card.
     *
     * @param e The ActionEvent associated with the draw card action.
     */
    @Override
    public void handleDrawCard(ActionEvent e) {
        controller.actionPerformed(e);
        updateView();
        updatePlayStatus("Drew One Card");
    }

    /**
     * Handles the play card action. Retrieves the selected card from the button's client property,
     * checks if it's a valid move using the controller, updates the top card and view accordingly.
     * Displays a message indicating if the move was valid or invalid.
     *
     * @param e The ActionEvent associated with the play card action.
     */
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
        } else {
            updatePlayStatus("Invalid Move");
        }
        if (controller.checkForWinner()) {
                /*
                if (controller.keepPlaying()) {
                    showKeepPlayingPopup();

                 */
            showWinnerPopup();
            nextButton.setEnabled(false);
            drawButton.setEnabled(false);

        }
    }

    /**
     * Handles the next turn action. Invokes the corresponding action in the controller,
     * updates the view to reflect the new state of the game.
     *
     * @param e The ActionEvent associated with the next turn action.
     */
    @Override
    public void handleNextTurn(ActionEvent e) {
        controller.actionPerformed(e);
        updateView();
        firstRound = false;
    }
}