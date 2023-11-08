import java.util.ArrayList;
import java.util.Collections;

public class UnoGameModel {
    private Deck deck;
    private ArrayList<Player> players;
    private Card startingCard;
    private Card topCard;
    private Card playedCard;

    private Player currentPlayer;

    private int playerIndex = 0;
    private boolean finished;

    public UnoGameModel(){

        createPlayer(4);
        this.deck = new Deck();
        this.startingCard = deck.draw();
        this.topCard = startingCard;
        this.finished = false;
        currentPlayer = players.get(playerIndex);
        dealCards();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    public void skip() {

        this.playerIndex = (playerIndex + 2) % players.size();

    }

    /**
     * Handles the selection of a new color for a wild card played by the current player.
     */
    public void wildCard() {

    }

    /**
     * Reverses the order of play.
     */
    public void reverse() {
        playerIndex = ((players.size() - playerIndex) % players.size());
        Collections.reverse(players);
    }

    /**
     * Draws N amount of cards for the next player
     */
    public void drawN(int numCards, int playerIndex) {
        for (int i = 0; i < numCards; i++) {
            players.get((playerIndex + 1) % players.size()).drawCard(deck);
        }
        skip();
    }

    /**
     * Checks and handles action cards like "Reverse", "Wild","Skip" and "WILD_DRAW_TWO" in the game.
     */
    public boolean checkActionCard() {
        if (topCard.getCardType().equals(Card.CardType.REVERSE)) {
            reverse();
            return true;
        } else if (topCard.getCardType().equals(Card.CardType.WILD)) {
            //System.out.println("Select a new colour: (RED, BLUE, GREEN, YELLOW)");
            wildCard();
            playerIndex++;
            return true;
        } else if (topCard.getCardType().equals(Card.CardType.SKIP)) {
            skip();
            return true;
        } else if (topCard.getCardType().equals(Card.CardType.DRAW_ONE)) {
            drawN(1, playerIndex);
            return true;
        } else if (topCard.getCardType().equals(Card.CardType.WILD_DRAW_TWO)) {
            //System.out.println("Select a new colour: (RED, BLUE, GREEN, YELLOW)");
            wildCard();
            drawN(2, playerIndex);
            return true;
        }
        return false;
    }

    public void createPlayer(int n){
        this.players = new ArrayList<Player>();
        while (n > 0){
            Player player = new Player();
            players.add(player);
            n--;
        }
    }
    public void dealCards(){
        for (int i = 0; i < 7; i++) {
            for (Player player : players) {
                player.drawCard(deck);
            }
        }
    }

    public void selectCard(Card card){
        playedCard = card;
        if (isValidChoice()){
            topCard = playedCard;
        }
    }

    public boolean isValidChoice(){
        if ((topCard.getColour() == playedCard.getColour()) || (topCard.getCardType() == playedCard.getCardType())) {
            return true;
        }
        if (playedCard.getCardType().equals(Card.CardType.WILD) || playedCard.getCardType().equals(Card.CardType.WILD_DRAW_TWO)) {
            return true;
            }
        if (startingCard.getColour().equals(Card.Colour.WILD)) {
            return true;
        }
        return false;
    }

    public void nextPlayer(){
        playerIndex++;
        if (playerIndex == players.size()){
            playerIndex = 0;
        }
    }
}
