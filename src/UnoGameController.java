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

    public void createPlayers(int n){
        model.createPlayers(n);
    }

    public Player getCurrentPlayer(){
        return model.getCurrentPlayer();
    }

    public int getIndex(){
        return model.getCurrentPlayerIndex();
    }
}
