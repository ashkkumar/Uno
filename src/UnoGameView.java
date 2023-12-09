import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.lang.*;

/**
 * The main view class for the Uno game. This class extends JFrame and implements
 * the UnoViewHandler interface to handle UI components and events.
 */
public class UnoGameView extends JFrame implements UnoViewHandler {
    private UnoGameModel model;
    private JPanel playerHandPane;

    private JScrollPane scrollPane;
    private JButton topCard;
    private JButton nextButton;
    private JButton drawButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton redoButton;
    private JButton undoButton;
    private JButton replayButton;
    private JPanel statusPane;
    private JLabel currentPlayerLabel;

    private JLabel playStatus;
    private JLabel colourStatus;

    private UnoGameController controller;

    private Card startingCard;

    private int numPlayers;

    private int numAiPlayers;

    private ArrayList<Player> playersTest;
    private boolean firstRound;

    /**
     * Constructs the UnoGameView, initializes the UI components, and sets up event listeners.
     */
    public UnoGameView() {

        numPlayers = askNumberOfPlayers();
        numAiPlayers = askNumberOfAIPlayers();

        this.model = new UnoGameModel(numPlayers, numAiPlayers);

        playersTest = model.getPlayers();

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
        String startCard = model.getStartingCard().getImageFilePath();

        ImageIcon icon = new ImageIcon(startCard);
        topCard = new JButton(icon);
        topCard.putClientProperty("card", model.getStartingCard());
        nextButton = new JButton("Next Player");
        drawButton = new JButton("Draw Card");
        loadButton = new JButton("Load Game");
        saveButton = new JButton("Save Game");
        redoButton = new JButton("Redo");
        undoButton = new JButton("Undo");
        replayButton = new JButton("Replay");

        loadButton.setActionCommand("load");
        saveButton.setActionCommand("save");
        nextButton.setActionCommand("nextPlayer");
        drawButton.setActionCommand("draw");
        undoButton.setActionCommand("undo");
        redoButton.setActionCommand("redo");
        replayButton.setActionCommand("replay");

        currentPlayerLabel = new JLabel();

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
        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(undoButton);
        buttonPanel.add(redoButton);
        buttonPanel.add(replayButton);
        this.add(buttonPanel, BorderLayout.NORTH);

        //next and draw buttons
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UnoGameEvent unoEvent = new UnoGameEvent(model);
                handleNextTurn(e);
            }
        });

        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDrawCard(e);
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLoad(e);
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSave(e);
            }
        });

        redoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRedo(e);
            }
        });

        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUndo(e);
            }
        });

        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                replay();
            }
        });

        //undoButton.setEnabled(false);
        //redoButton.setEnabled(false);

        updateView();
        checkStartCard();

        setVisible(true);
    }

    /**
     * Asks the user to select the number of AI players before starting the game.
     *
     * @return The result of the user selection.
     */
    private int askNumberOfAIPlayers() {
        Integer[] playerOptions = { 0, 1, 2, 3, 4 };
        JComboBox<Integer> playerDropdown = new JComboBox<>(playerOptions);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Select number of AI players:"));
        panel.add(playerDropdown);

        int result = JOptionPane.showConfirmDialog(null, panel, "Number of AI Players", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            int selectedAIPlayers = (int) playerDropdown.getSelectedItem();

            if (selectedAIPlayers == 0 && numPlayers == 1) {
                JOptionPane.showMessageDialog(panel, "Please Select more than one player",
                        "Player Count Error", JOptionPane.ERROR_MESSAGE);
                return askNumberOfAIPlayers(); // Ask again recursively
            }

            return selectedAIPlayers;
        }

        System.exit(0);
        return 0;
    }

    /**
     * Asks the user to select the number of players before starting the game.
     *
     * @return The result of the user selection.
     */
    private int askNumberOfPlayers() {
        Integer[] playerOptions = { 1, 2, 3, 4 };
        JComboBox<Integer> playerDropdown = new JComboBox<>(playerOptions);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Select number of players:"));
        panel.add(playerDropdown);

        int result = JOptionPane.showConfirmDialog(null, panel, "Number of Human Players", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            return (int) playerDropdown.getSelectedItem();
        }

        System.exit(0);
        return 0;
    }

    /**
     * Adds replay functionality for the game. A pop-up comes up confirming the choice and a new
     * UnoGameView is generated.
     */
    private void replay(){
        String message = "Restart the game?";
        int result = JOptionPane.showConfirmDialog(null, message, "Restart Game", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION){
            this.dispose();
            new UnoGameView();
        }// git is fun
    }


    /**
     * Asks the user to select the colour of the wild car they are playing
     */
    private void askWildCard(Card card) {
        JComboBox<String> playerDropdown;
        int drawnCards = 0;
        if (controller.checkDarkState()){
            String[] playerOptions = { "PINK", "PURPLE","ORANGE", "TEAL"};
            playerDropdown = new JComboBox<>(playerOptions);
        } else {
            String[] playerOptions = {"BLUE", "YELLOW", "RED", "GREEN"};
            playerDropdown = new JComboBox<>(playerOptions);
        }

        JPanel panel = new JPanel();
        panel.add(new JLabel("Select desired colour:"));
        panel.add(playerDropdown);

        int result = JOptionPane.showConfirmDialog(null, panel, "Select Colour", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            if (controller.checkDarkState()){
                topCard.setIcon(new ImageIcon(card.getDarkFilePath()));
                if (playerDropdown.getSelectedItem() == "PINK"){
                    drawnCards = controller.playWild(card, Card.Colour.BLUE);
                } else if (playerDropdown.getSelectedItem() == "PURPLE"){
                    drawnCards = controller.playWild(card, Card.Colour.YELLOW);
                } else if (playerDropdown.getSelectedItem() == "ORANGE"){
                    drawnCards = controller.playWild(card,Card.Colour.RED);
                } else{
                    drawnCards = controller.playWild(card,Card.Colour.GREEN);
                }
            } else {
                topCard.setIcon(new ImageIcon(card.getImageFilePath()));
                drawnCards = controller.playWild(card, Card.Colour.valueOf((String) playerDropdown.getSelectedItem()));
            }
            updateView();
            if (card.getCardType() == Card.CardType.WILD) {
                updatePlayStatus("Colour has been changed!");
            } else{
                if (controller.checkDarkState()){
                    updatePlayStatus("Colour changed and next player drew " + drawnCards + " cards!");
                } else{
                    updatePlayStatus("Colour changed and next player draws 2/skips!");

                }
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
        } else if (startingCard.getCardType() == Card.CardType.SKIP){
            nextButton.setEnabled(true);
            drawButton.setEnabled(false);
            controller.getCurrentPlayer().setCanPlay(false);
            controller.setHasDrawn();
            updatePlayStatus("Player 1 must be skipped!");
            updateView();
        } else if (startingCard.getCardType() == Card.CardType.DRAW_ONE){
            nextButton.setEnabled(true);
            drawButton.setEnabled(false);
            controller.getCurrentPlayer().setCanPlay(false);
            controller.model.drawN(1,-1);
            controller.setHasDrawn();
            updatePlayStatus("Player 1 must draw a card!");
            updateView();
        } else if (startingCard.getCardType() == Card.CardType.REVERSE){
            updatePlayStatus("Order Reversed!");
            updateView();
            controller.checkActionCard();
        } else if (startingCard.getCardType() == Card.CardType.FLIP){
            updatePlayStatus("Game has been flipped!");
            controller.flipGame();
            updateView();
            ImageIcon darkIcon = new ImageIcon(model.getStartingCard().getDarkFilePath());
            topCard.setIcon(darkIcon);
        }
   }

    /**
     * Shows a popup that congratulates the winner of the game
     */
    private void showWinnerPopup() {
        String message = "Congratulations, player " + model.getCurrentPlayer().getName() + "! You are the winner!";
        JOptionPane.showMessageDialog(null, message, "Winner!", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
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
        if(model.getCurrentPlayer().isAI()){
            currentPlayerLabel.setText("AI player " + (model.getCurrentPlayer().getName()) + "'s turn");
        }else{
            currentPlayerLabel.setText("Human player " + (model.getCurrentPlayer().getName()) + "'s turn");
        }

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
        if (controller.checkDarkState()){
            if (model.getTopColour() == Card.Colour.RED){
                colourStatus.setText("Colour: ORANGE");
            } else if (model.getTopColour() == Card.Colour.YELLOW){
                colourStatus.setText("Colour: PURPLE");
            } else if (model.getTopColour() == Card.Colour.BLUE){
                colourStatus.setText("Colour: PINK");
            } else{
                colourStatus.setText("Colour: TEAL");
            }
        } else {
            colourStatus.setText("Colour: " + model.getTopColour().name());
        }
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

        if (controller.getCurrentPlayer().getCanRedo()){
            redoButton.setEnabled(true);
            undoButton.setEnabled(false);
        }
        if (controller.getCurrentPlayer().getCanUndo()){
            redoButton.setEnabled(false);
            undoButton.setEnabled(true);
        }


        for (Card card: playerHand) {
            ImageIcon icon;
            if (controller.checkDarkState()) {
                icon = new ImageIcon(card.getDarkFilePath());
            } else{
                icon = new ImageIcon(card.getImageFilePath());
            }
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
     * Checks if there is a winner in the current Uno Game. If there is no winner with all points
     * added up, the game will show a popup to keep playing and reshuffle the deck.
     */
    public void checkWinner(){
        if (controller.checkForWinner()) {
            if (controller.keepPlaying()) {
                showKeepPlayingPopup();
            } else {
                showWinnerPopup();
            }
        }
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
            ImageIcon icon;
            if (controller.checkDarkState()) {
                icon = new ImageIcon(card.getDarkFilePath());
                topCard.setIcon(icon);
            }
            else {
                icon = new ImageIcon(card.getImageFilePath());
                topCard.setIcon(icon);
            }
            updateView();
            if (card.getCardType() == Card.CardType.SKIP) {
                if (controller.checkDarkState()){
                    updatePlayStatus("Skipping all players!");
                } else {
                    updatePlayStatus("Skipping Next Player's Turn!");
                }
            } else if (card.getCardType() == Card.CardType.DRAW_ONE){
                if (controller.checkDarkState()){
                    updatePlayStatus("Next player draws 5 and skips turn!");
                } else {
                    updatePlayStatus("Next player draws and skips turn!");
                }
            } else if (card.getCardType() == Card.CardType.REVERSE){
                updatePlayStatus("Order of players reversed!");
            } else if (card.getCardType() == Card.CardType.FLIP){
                updatePlayStatus("The game has been flipped!");
            }
            else{
                updatePlayStatus("Good move");
            }
        } else {
            updatePlayStatus("Invalid Move");
        }

        checkWinner();

    }

    /**
     * Loads up a previously played game of uno and also
     * updates the view of the game
     *
     * @param e The ActionEvent associated with the load button
     */
    @Override
    public void handleLoad(ActionEvent e) {
        controller.actionPerformed(e);
        if (controller.checkDarkState()){
            topCard.setIcon(new ImageIcon(model.getTopCard().getDarkFilePath()));
        } else {
            topCard.setIcon(new ImageIcon(model.getTopCard().getImageFilePath()));
        }
        updateView();
    }

    /**
     * Saves the current game of uno
     *
     * @param e The ActionEvent associated with the save button
     */
    @Override
    public void handleSave(ActionEvent e) {
        controller.actionPerformed(e);
        updateView();
    }

    /**
     * Undos the action performed
     *
     * @param e The ActionEvent associated with the undo button
     */
    @Override
    public void handleUndo(ActionEvent e) {
        controller.actionPerformed(e);
        if (controller.checkDarkState()){
            topCard.setIcon(new ImageIcon(model.getTopCard().getDarkFilePath()));
        } else {
            topCard.setIcon(new ImageIcon(model.getTopCard().getImageFilePath()));
        }
        updateView();
    }

    /**
     * Redos the action preformed
     *
     * @param e The ActionEvent associated with the redo button
     */
    @Override
    public void handleRedo(ActionEvent e) {
        controller.actionPerformed(e);
        if (controller.checkDarkState()){
            topCard.setIcon(new ImageIcon(model.getTopCard().getDarkFilePath()));
        } else {
            topCard.setIcon(new ImageIcon(model.getTopCard().getImageFilePath()));
        }
        updateView();

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

        // Handle AI Turn
        if (controller.getCurrentPlayer().isAI()) {
            boolean playedCard = controller.playAICard(); //plays a card if possible, else draws a card
            if (controller.checkDarkState()){
                topCard.setIcon(new ImageIcon(model.getTopCard().getDarkFilePath()));
            } else {
                topCard.setIcon(new ImageIcon(model.getTopCard().getImageFilePath()));
            }
            updateView();
            if (playedCard) {
                updatePlayStatus("Ai Played a card!");
            } else{
                updatePlayStatus("Ai Drew a card!");
            }
        }
        checkWinner();
    }
}

