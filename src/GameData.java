import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    public void setTopCard(Card topCard) {
        this.topCard = topCard;
    }

    public void setStartingCard(Card startingCard) {
        this.startingCard = startingCard;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void setTopType(Card.CardType topType) {
        this.topType = topType;
    }

    public void setTopColour(Card.Colour topColour) {
        this.topColour = topColour;
    }

    public void setPlayedType(Card.CardType playedType) {
        this.playedType = playedType;
    }

    public void setPlayedColour(Card.Colour playedColour) {
        this.playedColour = playedColour;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setPlayedCard(Card playedCard) {
        this.playedCard = playedCard;
    }

    public void setDiscardPile(Deck discardPile) {
        this.discardPile = discardPile;
    }

    public void setDarkSide(boolean darkSide) {
        this.darkSide = darkSide;
    }

    public Card.CardType getTopType() {
        return topType;
    }

    public Card.Colour getTopColour() {
        return topColour;
    }

    public Card.CardType getPlayedType() {
        return playedType;
    }

    public Card.Colour getPlayedColour() {
        return playedColour;
    }

    public boolean isFinished() {
        return finished;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Card getPlayedCard() {
        return playedCard;
    }

    public Card getTopCard() {
        return topCard;
    }

    public Card getStartingCard() {
        return startingCard;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Deck getDiscardPile() {
        return discardPile;
    }

    public Deck getDeck() {
        return deck;
    }

    public boolean isDarkSide() {
        return darkSide;
    }
}
