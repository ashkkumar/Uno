import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnoGameController implements ActionListener {

    UnoGameModel model;
    UnoGameView view;
    public UnoGameController(UnoGameModel model, UnoGameView view){
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
