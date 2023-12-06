import java.io.*;

public class GameSaver {

    UnoGameModel model;

    public GameSaver(UnoGameModel model) {
        this.model = model;
    }

    public void save() throws IOException {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.txt")));

            GameData data = new GameData();

            data.setDeck(model.getDeck());
            data.setCurrentPlayer(model.getCurrentPlayer());
            data.setDarkSide(model.isDarkSide());
            data.setDiscardPile(model.getDiscardPile());
            data.setFinished(model.isFinished());
            data.setPlayedCard(model.getPlayedCard());
            data.setPlayedColour(model.getPlayedCard().getColour());
            data.setPlayedType(model.getPlayedCard().getCardType());
            data.setPlayerIndex(model.getCurrentPlayerIndex());
            data.setPlayers(model.getPlayers());
            data.setStartingCard(model.getStartingCard());
            data.setTopCard(model.getTopCard());
            data.setTopColour(model.getTopColour());
            data.setTopType(model.getTopType());

            oos.writeObject(data);

        } catch (Exception e) {
            System.out.println("could not save game");
        }

    }

    public void load() throws ClassNotFoundException {

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.txt")));

            GameData data = (GameData) ois.readObject();

            model.setDeck(data.getDeck());
            model.setCurrentPlayer(data.getCurrentPlayer());
            model.setDarkSide(data.isDarkSide());
            model.setDiscardPile(data.getDiscardPile());
            model.setFinished(data.isFinished());
            model.setPlayedCard(data.getPlayedCard());
            model.setPlayedColour(data.getPlayedColour());
            model.setPlayedType(data.getPlayedType());
            model.setTopCard(data.getTopCard());
            model.setTopType(data.getTopType());
            model.setTopColour(data.getTopColour());
            model.setPlayerIndex(data.getPlayerIndex());
            model.setPlayers(data.getPlayers());
            model.setStartingCard(data.getStartingCard());


        } catch (IOException e) {
            System.out.println("could not load game");
        }
    }
}
