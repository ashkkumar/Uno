import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class  Deck {

    private ArrayList<Card> deck;

    private int numDeckCards;

    /**
     * Constructs a deck of Uno cards from the provided ArrayList of cards.
     *
     * @param cards An ArrayList of Uno cards to initialize the deck.
     */
    public Deck(ArrayList<Card> cards){
        this.deck = cards;
        numDeckCards = cards.size();
    }
    /**
     * Constructs a new Uno deck by creating and populating it with Uno cards.
     */
    public Deck(){
        deck = new ArrayList<Card>();
        makeCards();
    }
    /**
     * Adds a full deck of Uno cards and shuffles the deck.
     */
    public void makeCards(){

        for (Card.Colour Colour: Card.Colour.values()){
            if (!Colour.equals(Card.Colour.WILD)) {
                for (Card.CardType Type : Card.CardType.values()) {
                    if (!Type.equals(Card.CardType.WILD)) {
                        deck.add(new Card(Type, Colour));
                        deck.add(new Card(Type, Colour));
                        numDeckCards += 2;
                    }
                }
            } else {
                for (int i = 4; i > 0; i--){
                    deck.add(new Card(Card.CardType.WILD, Card.Colour.WILD));
                    numDeckCards++;
                }

            }
        }
        Collections.shuffle(deck);
    }

    /**
     * Draws a card from the deck, and reduces the count of cards in the deck.
     *
     * @return The drawn Uno card.
     */
    public Card draw(){

        Card card = deck.remove(0);
        numDeckCards--;
        return card;
    }
    /**
     * Checks if the deck is empty (contains no more cards).
     *
     * @return True if the deck is empty, otherwise false.
     */
    public boolean isEmpty(){
        if (numDeckCards == 0){
            return true;
        }
        return false;
    }
    /**
     * Gets the number of cards remaining in the deck.
     *
     * @return The count of cards in the deck.
     */
    public int getNumDeckCards() {
        return numDeckCards;
    }


}
