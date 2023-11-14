import java.awt.event.ActionEvent;
import java.util.EventObject;

public class UnoGameEvent extends EventObject {

    private Card card;

    public UnoGameEvent(UnoGameModel uno){
        super(uno);
    }

    public UnoGameEvent(UnoGameModel uno, Card card){
        super(uno);
        this.card = card;
    }

    public Card getCard() {
        return card;
    }

}
