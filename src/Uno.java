import java.util.ArrayList;
import java.util.Scanner;
/**
 * The Uno class represents the main controller for the Uno game.
 */
public class Uno {

    private ArrayList<Player> players;

    private ArrayList<Card> discardPile;

    private Deck deck;

    private Card startingCard;
    private Card playedCard;

    private Scanner choice;

    private Player currentPlayer;

    private Card.CardType currentNumber;

    private Card.Colour currentColour;

    private Player winner;

    private boolean finished;

    /**
     * Constructs a new Uno game by initializing players, discard pile, and the deck.
     */
    public Uno(){

        players = new ArrayList<Player>();
        discardPile = new ArrayList<Card>();
        deck = new Deck();

    }

    /**
     * Start and manage the Uno game.
     */
    public void play(){

        System.out.print("Enter number of players (2-4):");
        Scanner intPlayers = new Scanner(System.in);
        int n = intPlayers.nextInt();
        createPlayers(n);
        giveCards();

        currentPlayer = players.get(0);
        currentPlayer.getMyCards();

    }

    /**
     * Distribute cards to players at the beginning of the game.
     */
    public void giveCards() {
        for (int i = 0; i < 7; i++) {
            for (Player player : players) {
                player.drawCard(deck);
            }
        }
    }

    /**
     * Draw a card from the deck and add it to the current player's hand.
     */
    public void drawOne(){
        currentPlayer.drawCard(deck);
    }

    /**
     * Handle the effects of a wild card.
     */
    public void wildCard(){
        return;
    }

    /**
     * Handle the effects of a reverse card.
     */
    public void reverse(){
        return;
    }

    /**
     * Handle the effects of a skip card.
     */
    public void skip(){
        return;
    }

    /**
     * Check if the player's choice is valid.
     */
    public void isValidChoice(){

    }

    /**
     * Create and initialize player instances based on the number of players provided.
     *
     * @param n The number of players (2-4) in the game.
     */
    public void createPlayers(int n){
        if (n >= 2 && n <=4) {
            while (n > 0) {
                players.add(new Player());
                n--;
            }
        }
    }
}
