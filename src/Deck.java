import java.util.ArrayList;
import java.util.Random;

/**
 * The Deck class represents the deck of Uno cards.
 */
public class Deck {

    private ArrayList<Card> deck;
    private int numDeckCards;

    /**
     * Constructs a deck with the specified list of cards.
     *
     * @param cards The list of cards to use for the deck.
     */
    public Deck(ArrayList<Card> cards) {
        this.deck = cards;
        numDeckCards = cards.size();
    }

    /**
     * Constructs a new Uno deck and initializes it with standard Uno cards.
     */
    public Deck() {
        deck = new ArrayList<Card>();
        makeCards();
    }

    /**
     * Generate and add standard Uno cards to the deck.
     */
    public void makeCards() {
        for (Card.Colour Colour : Card.Colour.values()) {
            if (!Colour.equals(Card.Colour.WILD)) {
                for (Card.CardType Type : Card.CardType.values()) {
                    if (!Type.equals(Card.CardType.WILD)) {
                        deck.add(new Card(Type, Colour));
                        deck.add(new Card(Type, Colour));
                        numDeckCards += 2;
                    }
                }
            } else {
                for (int i = 4; i > 0; i--) {
                    deck.add(new Card(Card.CardType.WILD, Card.Colour.WILD));
                    numDeckCards++;
                }
            }
        }
    }

    /**
     * Draw a card randomly from the deck and remove it.
     *
     * @return The drawn card.
     */
    public Card draw() {
        Random rand = new Random();
        int i = rand.nextInt(numDeckCards);
        Card card = deck.remove(i);
        numDeckCards--;
        return card;
    }

    /**
     * Check if the deck is empty.
     *
     * @return true if the deck is empty, false otherwise.
     */
    public boolean isEmpty() {
        return numDeckCards == 0;
    }

    /**
     * Get the number of cards in the deck.
     *
     * @return The number of cards in the deck.
     */
    public int getNumDeckCards() {
        return numDeckCards;
    }
}
