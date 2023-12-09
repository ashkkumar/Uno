import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class serializes the game data so that it may be used to save/load the game at
 * a user's will.
 */
public class GameData implements Serializable {

    // Game Info
    private boolean darkSide;
    private Deck deck;
    private Deck discardPile;
    private ArrayList<Player> players;
    private Card startingCard;
    private Card topCard;
    private Card playedCard;
    private Player currentPlayer;
    private int playerIndex = 0;
    private boolean finished;
    private Card.Colour playedColour;
    private Card.CardType playedType;
    private Card.Colour topColour;
    private Card.CardType topType;


    /**
     * Sets the top card of the game
     *
     * @param topCard the top card of the game
     */
    public void setTopCard(Card topCard) {
        this.topCard = topCard;
    }

    /**
     * Sets the card that the game begins with
     *
     * @param startingCard the card at the start of the game
     */
    public void setStartingCard(Card startingCard) {
        this.startingCard = startingCard;
    }

    /**
     * Sets the ArrayList of players in the game
     *
     * @param players an Arraylist of players in the game
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * Sets game's deck of uno cards
     *
     * @param deck the deck of uno cards used by the game
     */
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    /**
     * Sets game's top card type
     *
     * @param topType the type of card
     */
    public void setTopType(Card.CardType topType) {
        this.topType = topType;
    }

    /**
     * Sets game's top card colour
     *
     * @param topColour the type of card
     */
    public void setTopColour(Card.Colour topColour) {
        this.topColour = topColour;
    }

    /**
     * Sets the type of the played card
     *
     * @param playedType the type of card
     */
    public void setPlayedType(Card.CardType playedType) {
        this.playedType = playedType;
    }

    /**
     * Sets the colour of the played card
     *
     * @param playedColour the colour of the played card
     */
    public void setPlayedColour(Card.Colour playedColour) {
        this.playedColour = playedColour;
    }

    /**
     * Sets the state of the game
     *
     * @param finished the state of the game
     */
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    /**
     * Sets game's current player index
     *
     * @param playerIndex the index of the current player
     */
    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    /**
     * Sets the current player of the game
     *
     * @param currentPlayer the current player of the game
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Sets the card played by the player
     *
     * @param playedCard the card played by the player
     */
    public void setPlayedCard(Card playedCard) {
        this.playedCard = playedCard;
    }

    /**
     * Sets the game's discard pile
     *
     * @param discardPile the deck of discarded cards
     */
    public void setDiscardPile(Deck discardPile) {
        this.discardPile = discardPile;
    }

    /**
     * Sets the game to either the light side or dark side
     *
     * @param darkSide the State of the game
     */
    public void setDarkSide(boolean darkSide) {
        this.darkSide = darkSide;
    }

    /**
     * Gets the type of the top card
     *
     * @return topType Type
     */
    public Card.CardType getTopType() {
        return topType;
    }

    /**
     * Gets the colour of the top card
     *
     * @return top colour
     */
    public Card.Colour getTopColour() {
        return topColour;
    }

    /**
     * Gets the played card type
     *
     * @return type the type of card
     */
    public Card.CardType getPlayedType() {
        return playedType;
    }
    /**
     * Gets the played card colour
     *
     * @return colour the colour of card
     */
    public Card.Colour getPlayedColour() {
        return playedColour;
    }

    /**
     * Gets the state of the game
     *
     * @return Boolean, true if the game is finished, false otherwise
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * Gets the current player index
     *
     * @return int the index of the current player
     */
    public int getPlayerIndex() {
        return playerIndex;
    }

    /**
     * Gets the current player of the game
     *
     * @return Player the current player of the game
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Gets the card played by the player
     *
     * @return Card the card played by the player
     */
    public Card getPlayedCard() {
        return playedCard;
    }

    /**
     * Gets the top card of the game
     *
     * @return Card the top card of the game
     */
    public Card getTopCard() {
        return topCard;
    }

    /**
     * Gets the starting card of the game
     *
     * @return Card the starting card of the game
     */
    public Card getStartingCard() {
        return startingCard;
    }

    /**
     * Gets the Arraylist of players in the game
     *
     * @return ArrayList an arraylist of players in the game
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Gets the deck of discarded uno cards
     *
     * @return Deck the deck of uno cards
     */
    public Deck getDiscardPile() {
        return discardPile;
    }

    /**
     * Gets the deck of uno cards used by the game
     *
     * @return Deck the deck of uno cards
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * Gets the state of the game depending on if it's
     * the dark side or the light side
     *
     * @return Boolean true if it is the false side of the game false otherwise
     */
    public boolean isDarkSide() {
        return darkSide;
    }
}
