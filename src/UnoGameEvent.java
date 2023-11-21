import java.awt.event.ActionEvent;
import java.util.EventObject;

public class UnoGameEvent extends EventObject {

    Card card;
    /**
     * Constructs a UnoGameEvent without a specific card.
     *
     * @param uno The UnoGameModel associated with the event.
     */
    public UnoGameEvent(UnoGameModel uno){
        super(uno);
    }

    /**
     * Constructs a UnoGameEvent with a specific card.
     *
     * @param uno  The UnoGameModel associated with the event.
     * @param card The card associated with the event.
     */
    public UnoGameEvent(UnoGameModel uno, Card card){
        super(uno);
        this.card = card;
    }

    /**
     * Retrieves the card associated with the event.
     *
     * @return The card associated with the event, or null if there is no specific card.
     */
    public Card getCard() {
        return card;
    }

}
