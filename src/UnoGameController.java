import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnoGameController implements ActionListener {

    UnoGameModel model;

    public UnoGameController(UnoGameModel model) {
        this.model = model;
    }

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

    public boolean playCard(Card card){
        if(model.selectCard(card)){
            model.getCurrentPlayer().getMyCards().remove(card);
            model.checkActionCard();
            return true;
        }
        return false;
    }

    public void playWild(Card card, Card.Colour colour){
        model.wildCard(colour);
        model.getCurrentPlayer().getMyCards().remove(card);

        if (card.getCardType() == Card.CardType.WILD_DRAW_TWO){
            model.drawN(2,getIndex());
        }

    }

    public void createPlayers(int n){
        model.createPlayers(n);
    }

    public Player getCurrentPlayer(){
        return model.getCurrentPlayer();
    }

    public boolean hasDrawn(){
        return model.hasDrawn();
    }

    public void setHasDrawn(){
        model.getCurrentPlayer().setHasDrawn(true);
    }

    public void wildCard(Card.Colour colour){
        model.wildCard(colour);
    }

    public void checkActionCard(){
        model.checkActionCard();
    }
    public int getIndex(){
        return model.getCurrentPlayerIndex();
    }
}
