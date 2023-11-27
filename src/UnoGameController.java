import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class UnoGameController implements ActionListener {

    public UnoGameModel model;

    /**
     * Constructs a UnoGameController with the specified UnoGameModel.
     *
     * @param model The UnoGameModel to associate with the controller.
     */
    public UnoGameController(UnoGameModel model) {
        this.model = model;
    }

    /**
     * Handles action events triggered by user interactions.
     *
     * @param e The ActionEvent representing the user's action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Check the source of the action event and perform actions accordingly
        String command = e.getActionCommand();

        if (command.equals("draw")) {
            model.getCurrentPlayer().setHasDrawn(true);
            model.drawOne();
        } else if (command.equals("nextPlayer")) {
            model.nextPlayer();
        }
        /// Add more conditions for other actions as needed
    }

    /**
     * Checks the current state of the game if it is dark or light
     */
    public boolean checkDarkState(){
        return model.isDarkSide();
    }

    /**
     * Attempts to play the specified card. If successful, removes the card from the player's hand,
     * checks for action cards, and returns true; otherwise, returns false.
     *
     * @param card The card to be played.
     * @return True if the card is successfully played; false otherwise.
     */
    public boolean playCard(Card card){
        if(model.selectCard(card)){
            model.getCurrentPlayer().removeCard(card);
            model.checkActionCard();
            return true;
        }
        return false;
    }

    /***
     * Selects and Plays for an AI Player based on the number of points each card is worth
     * Checks for action card and does appropriate action
     * Removes card from hand and continues
     * @return true if played, false if drawn
     */
    public boolean playAICard(){

        int bestCardIndex = model.getCurrentPlayer().getBestCardIndex(model.getTopType(), model.getTopColour());

        if(bestCardIndex == -1){
            model.drawOne();
            int bestCardIndex2 = model.getCurrentPlayer().getBestCardIndex(model.getTopType(), model.getTopColour());

            if(bestCardIndex2 == -1){ //if the AI still can't play a card

                return false;
            }

            if(model.selectCard(model.getCurrentPlayer().getCard(bestCardIndex2))){

                Card card = model.getCurrentPlayer().getCard(bestCardIndex2);

                model.getCurrentPlayer().removeCard(model.getCurrentPlayer().getCard(bestCardIndex2));
                model.checkActionCard();
                if (model.getTopColour() == Card.Colour.WILD){
                    String[] colours = {"BLUE", "RED", "YELLOW", "GREEN"};
                    Random randNum = new Random();
                    playWild(card,Card.Colour.valueOf(colours[randNum.nextInt(4)]));
                }

                return true;
            }

            return false;

        } else{

            if(model.selectCard(model.getCurrentPlayer().getCard(bestCardIndex))){

                Card card = model.getCurrentPlayer().getCard(bestCardIndex);

                model.getCurrentPlayer().removeCard(model.getCurrentPlayer().getCard(bestCardIndex));

                model.checkActionCard();
                if (model.getTopColour() == Card.Colour.WILD){
                    String[] colours = {"BLUE", "RED", "YELLOW", "GREEN"};
                    Random randNum = new Random();
                    playWild(card,Card.Colour.valueOf(colours[randNum.nextInt(4)]));
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if there's a winner
     * @return true if there's a winner
     */
    public boolean checkForWinner() {
        System.out.println("Current Player: " + model.getCurrentPlayer().getName());
        System.out.println("Current Player Card Count: " + model.getCurrentPlayer().getNumCards());
        System.out.println("Points = " + model.getCurrentPlayer().getScore());
        if (model.checkWinner()) {
            System.out.println("Points = " + model.getCurrentPlayer().getScore());
            System.out.println("Winner found");
            return true;
        }
        else
            System.out.println("No winner found");
        return false;
    }

    /**
     * keep playing if the winner doesn't reach 500 points
     * @return true if they should keep playing
     */
    public boolean keepPlaying(){
        if (model.getCurrentPlayer().getScore() < 500) {
            model.notEnoughPoints();
            System.out.println("not enough points. points = " + model.getCurrentPlayer().getScore());
            return true;
        }
        else return false;
    }

    /**
     * Method for when a wild card is played, it changes the current colour
     * @param card
     * @param colour
     */
    public int playWild(Card card, Card.Colour colour){
        model.wildCard(colour);
        if (!model.getCurrentPlayer().isAI()) {
            model.getCurrentPlayer().removeCard(card);
        }

        if (card.getCardType() == Card.CardType.WILD_DRAW_TWO){
            if (checkDarkState()){
                int drawnCards = 0;
                while (model.drawN(1,getIndex()).getColour() != model.getTopColour()){
                    drawnCards ++;
                }
                drawnCards++;
                model.skip();
                return drawnCards;
            } else {
                model.drawN(2, getIndex());
                model.skip();
                return 2;
            }
        }
        return 0;
    }

    /**
     * Creates the specified number of players in the UnoGameModel.
     *
     * @param n The number of players to create.
     */
    public void createPlayers(int n, int numAI){
        model.createPlayers(n, numAI);
    }

    /**
     * Gets the current player of the game
     *
     * @return Player, the current player of the game
     */
    public Player getCurrentPlayer(){
        return model.getCurrentPlayer();
    }

    /**
     * Checks if the current player has drawn a card.
     *
     * @return True if the current player has not drawn; false otherwise.
     */
    public boolean hasDrawn(){
        return model.hasDrawn();
    }

    /**
     * Sets the current player's "has drawn" status to true.
     */
    public void setHasDrawn(){
        model.getCurrentPlayer().setHasDrawn(true);
    }

    /**
     * Performs a wild card action with the specified color.
     *
     * @param colour The color chosen for the wild card.
     */
    public void wildCard(Card.Colour colour){
        model.wildCard(colour);
    }

    /**
     * checks the action card
     */
    public void checkActionCard(){
        model.checkActionCard();
    }

    /**
     * Retrieves the index of the current player in the UnoGameModel.
     *
     * @return The index of the current player.
     */
    public int getIndex(){
        return model.getCurrentPlayerIndex();
    }

    /**
     * Flips state of the game from light to dark and vise versa
     */
    public void flipGame(){
        model.flip();
        getCurrentPlayer().setCanPlay(true);
    }

}
