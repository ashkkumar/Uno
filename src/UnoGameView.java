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

        private boolean canPlay = true;
        /**
         * Constructs the UnoGameView, initializes the UI components, and sets up event listeners.
         */
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
            currentPlayerLabel = new JLabel("Player " + (model.getCurrentPlayerIndex() + 1) + "'s turn");

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
            askNumberOfPlayers();
            updateView();
            setVisible(true);
        }

        /**
         * Updates the label displaying the current player's turn.
         */
        public void updatePlayerTurnLabel(){
            currentPlayerLabel.setText("Player "+ (model.getCurrentPlayerIndex()+1) + "'s turn");
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
         * Sets the enabled status of the "Next Player" button.
         *
         * @param enabled True to enable the button, false to disable.
         */
        public void setPlayButtonEnabled(boolean enabled) {
            nextButton.setEnabled(enabled);
        }

        /**
         * Sets the enabled status of the "Draw Card" button.
         *
         * @param enabled True to enable the button, false to disable.
         */
        public void setDrawButtonEnabled(boolean enabled) {
            drawButton.setEnabled(enabled);
        }

        /**
         * Displays a message to the user using a dialog box.
         *
         * @param message The message to be displayed.
         */
        public void displayMessage(String message) {
            // Implement how you want to display messages to the user
            JOptionPane.showMessageDialog(this, message);
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
            if (controller.playCard(card)) {
                ImageIcon icon = new ImageIcon(card.getImageFilePath());
                topCard.setIcon(icon);
                updateView();
                updatePlayStatus("Good Move");
            }
            else{
                updatePlayStatus("Invalid Move");
                //System.out.println("Invalid move");
            }

        }
        /**
         * Handles the Uno call event. Notifies the controller about the Uno call event.
         *
         * @param e The UnoGameEvent associated with the Uno call.
         */
        @Override
        public void handleUnoCall(UnoGameEvent e) {
            // Notify the controller about the Uno call event
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
                int numberOfPlayers = (int) playerDropdown.getSelectedItem();
                //model = new UnoGameModel();
                model.createPlayers(numberOfPlayers);
                model.dealCards();
            }
            else {
                // Handle if the user cancels the selection
                // For example, close the program or take appropriate action
            }

            return result;
        }
    }