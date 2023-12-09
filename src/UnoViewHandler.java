import java.awt.event.ActionEvent;

/**
 * The interface that all UnoViewHandlers must inherit, containing methods for handling
 * All uno game events
 */

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

    /**
     * Handles the event of loading the Uno game.
     *
     * @param e The ActionEvent associated with the play card action.
     */
    void handleLoad(ActionEvent e);

    /**
     * Handles the event of saving the Uno game.
     *
     * @param e The ActionEvent associated with the play card action.
     */
    void handleSave(ActionEvent e);

    /**
     * Handles the event of undo in the Uno game.
     *
     * @param e The ActionEvent associated with the play card action.
     */
    void handleUndo(ActionEvent e);

    /**
     * Handles the event of redo in the Uno game.
     *
     * @param e The ActionEvent associated with the play card action.
     */
    void handleRedo(ActionEvent e);




}
