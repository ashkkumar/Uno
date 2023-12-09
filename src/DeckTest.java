/**
 * Deck Class Testing
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {

    private static Deck deck;
    private static int counter;

    public DeckTest() {
    }

    /**
     * Sets test deck and counter to default values before each test
     */
    @BeforeEach
    public void setUp() {
        deck = null;
        counter = 0;
    }

    /**
     * Displays the counter value for each test after they are complete
     */
    @AfterEach
    public void summary() {
        System.out.println("Number of Tests Completed: " + counter + "\n");
    }

    @Test
    public void test_DefaultConstructor() {
        System.out.println("Testing Default Constructor...");
        deck = new Deck();
        assertEquals(112, deck.getNumDeckCards());
        // Default constructor uses makeCards method for adding to deck, tested on its own
        counter = 1;
    }

    @Test
    public void test_OverloadedConstructor() {
        System.out.println("Testing Overloaded Constructor...");
        ArrayList<Card> test_deck = new ArrayList<>();
        test_deck.add(new Card(Card.CardType.FOUR, Card.Colour.GREEN));
        test_deck.add(new Card(Card.CardType.FIVE, Card.Colour.YELLOW));
        test_deck.add(new Card(Card.CardType.THREE, Card.Colour.RED));
        test_deck.add(new Card(Card.CardType.REVERSE, Card.Colour.BLUE));
        deck = new Deck(test_deck);
        assertEquals(test_deck.size(), deck.getNumDeckCards());
        counter += 1;
        for (int i = 0; i < test_deck.size(); i++) {
            assertEquals(test_deck.get(i), deck.getCard(i));
            counter++;
        }
    }

    @Test
    public void test_getNumDeckCards() {
        System.out.println("Testing Method getNumDeckCards");
        ArrayList<Card> test_deck = new ArrayList<>();
        test_deck.add(new Card(Card.CardType.THREE, Card.Colour.RED));
        test_deck.add(new Card(Card.CardType.REVERSE, Card.Colour.GREEN));
        deck = new Deck(test_deck);
        assertEquals(2, deck.getNumDeckCards());
        deck.draw();
        assertEquals(1, deck.getNumDeckCards());
        counter = 2;
    }

    @Test
    public void test_getCard() {
        System.out.println("Testing Method getCard");
        ArrayList<Card> test_deck = new ArrayList<>();
        test_deck.add(new Card(Card.CardType.THREE, Card.Colour.RED));
        test_deck.add(new Card(Card.CardType.REVERSE, Card.Colour.GREEN));
        deck = new Deck(test_deck);
        assertEquals(Card.CardType.THREE, deck.getCard(0).getCardType());
        assertEquals(Card.Colour.RED, deck.getCard(0).getColour());
        counter = 2;
    }

    @Test
    public void test_getCards() {
        System.out.println("Testing Method getCards");
        ArrayList<Card> test_deck = new ArrayList<>();
        test_deck.add(new Card(Card.CardType.THREE, Card.Colour.RED));
        test_deck.add(new Card(Card.CardType.REVERSE, Card.Colour.GREEN));
        deck = new Deck(test_deck);
        assertEquals(test_deck, deck.getCards());
    }

    @Test
    public void test_draw() {
        System.out.println("Testing Method draw...");
        ArrayList<Card> test_deck = new ArrayList<>();
        test_deck.add(new Card(Card.CardType.THREE, Card.Colour.RED));
        test_deck.add(new Card(Card.CardType.REVERSE, Card.Colour.GREEN));
        deck = new Deck(test_deck);
        Card drawn = deck.draw();
        assertNotNull(drawn);
        assertFalse(deck.getCards().contains(drawn));
        assertEquals(1, deck.getNumDeckCards());
        counter = 3;
    }

    @Test
    public void test_isEmpty() {
        System.out.println("Testing Method isEmpty...");
        ArrayList<Card> test_deck = new ArrayList<>();
        deck = new Deck(test_deck);
        assertTrue(deck.isEmpty());
        test_deck = new ArrayList<>();
        test_deck.add(new Card(Card.CardType.THREE, Card.Colour.RED));
        deck = new Deck(test_deck);
        deck.draw();
        assertTrue(deck.isEmpty());
        counter = 2;
    }

    @Test
    public void test_makeCards() {
        System.out.println("Testing Method makeCards...");
        deck = new Deck();
        assertEquals(112, deck.getNumDeckCards());
        counter = 1;
    }
}
