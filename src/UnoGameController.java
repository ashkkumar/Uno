import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The UnoGameController class handles user actions and interacts with the UnoGameModel.
 * It implements the ActionListener interface to respond to button clicks and other actions.
 */
public class UnoGameController implements ActionListener {

    UnoGameModel model;
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
     * Attempts to play the specified card. If successful, removes the card from the player's hand,
     * checks for action cards, and returns true; otherwise, returns false.
     *
     * @param card The card to be played.
     * @return True if the card is successfully played; false otherwise.
     */
    public boolean playCard(Card card){
        if(model.selectCard(card)){
            model.getCurrentPlayer().getMyCards().remove(card);
            model.checkActionCard();
            return true;
        }
        return false;
    }
    /**
     * Creates the specified number of players in the UnoGameModel.
     *
     * @param n The number of players to create.
     */
    public void createPlayers(int n){
        model.createPlayers(n);
    }
    /**
     * Retrieves the current player from the UnoGameModel.
     *
     * @return The current player.
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
        return model.hasNotDrawn();
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
     * Retrieves the index of the current player in the UnoGameModel.
     *
     * @return The index of the current player.
     */
    public int getIndex(){
        return model.getCurrentPlayerIndex();
    }
}
