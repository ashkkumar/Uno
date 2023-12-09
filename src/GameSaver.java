import java.io.*;

/**
 * This class implements all save/load functionality, as well as redo/undo.
 */
public class GameSaver {

    UnoGameModel model;

    /**
     * Initializes the GameSaver, creating an instance of the UnoGameModel
     */
    public GameSaver(UnoGameModel model) {
        this.model = model;
    }

    /**
     * Saves the current game data to an external file
     *
     * @throws IOException if the game was unable to save its data
     */
    public void save() throws IOException {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.txt")));

            setGameData(oos);

        } catch (Exception e) {
            System.out.println("could not save game");
        }

    }

    /**
     * Loads a previously saved game into the game
     *
     * @throws ClassNotFoundException when the definition of the file class is not found
     */
    public void load() throws ClassNotFoundException {

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.txt")));

            loadGameData(ois);


        } catch (IOException e) {
            System.out.println("could not load game");
        }
    }

    /**
     * Saves the current state of the game.
     */
    public void currentGame(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("currentGame.txt")));

            setGameData(oos);

        } catch (Exception e) {
            System.out.println("could not save current game");
        }

    }

    /**
     * Saves the state of the previous game.
     */
    public void previousGame(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("previousGame.txt")));

            setGameData(oos);

        } catch (Exception e) {
            System.out.println("could not save previous game");
        }
    }

    /**
     * Performs the player's previous action to restore the game state to where it was before an undo command.
     */
    public void redo(){

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("currentGame.txt")));

            loadGameData(ois);


        } catch (IOException | ClassNotFoundException e) {
            System.out.println("could not redo");
        }
    }

    /**
     * Undoes the player's current action to restore the game to the previous state before
     * an action occurred.
     */
    public void undo(){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("previousGame.txt")));

            loadGameData(ois);


        } catch (IOException | ClassNotFoundException e) {
            System.out.println("could not undo");
        }
    }

    /**
     * Sets the game data
     * @param oos the object output stream of the game
     * @throws IOException if invalid
     */
    private void setGameData(ObjectOutputStream oos) throws IOException {
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
    }

    /**
     * Loads the game data to the UnoGameModel
     * @param ois the object input stream
     * @throws IOException if invalid
     * @throws ClassNotFoundException if class not found
     */
    private void loadGameData(ObjectInputStream ois) throws IOException, ClassNotFoundException {
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
    }
}
