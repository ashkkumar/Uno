import java.util.ArrayList;

/**
 * The Player class represents a player in the Uno game.
 */
public class Player {

    private String name;
    private ArrayList<Card> myCards;
    private int score;
    private int numCards;

    /**
     * Constructs a new Uno player with an empty hand and an initial score of 0.
     */
    public Player() {
        myCards = new ArrayList<Card>();
        numCards = 0;
    }

    /**
     * Add a card to the player's hand.
     *
     * @param card The card to add to the player's hand.
     */
    public void addCard(Card card) {
        myCards.add(card);
        numCards++;
    }

    /**
     * Remove a card from the player's hand.
     *
     * @param card The card to remove from the player's hand.
     */
    public void removeCard(Card card) {
        myCards.remove(card);
        numCards--;
    }

    /**
     * Set the player's score.
     *
     * @param score The score to set for the player.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Get the player's score.
     *
     * @return The player's score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Get the number of cards in the player's hand.
     *
     * @return The number of cards in the player's hand.
     */
    public int getNumCards() {
        return numCards;
    }

    /**
     * Display the cards in the player's hand, including their color and type.
     */
    public void getMyCards() {
        System.out.println("Your Cards:");
        for (int i = 0; i < myCards.size(); i++) {
            System.out.println(i + 1 + ". " + myCards.get(i).getColour() + " " + myCards.get(i).getCardType());
        }
    }

    /**
     * Draw a card from the deck and add it to the player's hand.
     *
     * @param deck The deck from which to draw a card.
     */
    public void drawCard(Deck deck) {
        myCards.add(deck.draw());
        numCards++;
    }
}
