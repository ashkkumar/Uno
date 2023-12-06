import java.awt.event.ActionEvent;

public interface UnoViewHandler {

    /**
     * Handles the event of advancing to the next turn in the Uno game.
     *
     * @param e The ActionEvent associated with the next turn action.
     */
    void handleNextTurn(ActionEvent e);

    /**
     * Handles the event of drawing a card in the Uno game.
     *
     * @param e The ActionEvent associated with the draw card action.
     */
    void handleDrawCard(ActionEvent e);

    /**
     * Handles the event of playing a card in the Uno game.
     *
     * @param e The ActionEvent associated with the play card action.
     */
    void handlePlay(ActionEvent e);

    void handleLoad(ActionEvent e);

    void handleSave(ActionEvent e);

}
