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
            // Handle button click event
            UnoGameEvent unoEvent = new UnoGameEvent(model);
            //model.handlePlay(unoEvent); // Update the model based on the play action
        } else if (command.equals("nextPlayer")) {
            // Handle another button click event
            UnoGameEvent unoEvent = new UnoGameEvent(model);
            //model.handleDrawCard(unoEvent); // Update the model based on the draw card action
        }
        /// Add more conditions for other actions as needed
    }
}
