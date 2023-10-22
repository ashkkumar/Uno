/**
 * Card Class Testing
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {
    private static Card card;
    private static int counter;

    public CardTest() {
    }

    /**
     * Sets test card and counter to default values before each test
     */
    @BeforeEach
    public void setUp() {
        card = null;
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
    public void test_OverloadedConstructor() {
        System.out.println("Testing Overloaded Constructor...");
        card = new Card(Card.CardType.SIX, Card.Colour.BLUE);
        assertEquals(Card.CardType.SIX, card.getCardType());
        assertEquals(Card.Colour.BLUE, card.getColour());
        counter = 2;
    }

    @Test
    public void test_getCardType() {
        System.out.println("Testing Method getCardType...");
        card = new Card(Card.CardType.DRAW_ONE, Card.Colour.RED);
        assertEquals(Card.CardType.DRAW_ONE, card.getCardType());
        counter = 1;
    }

    @Test
    public void test_getColour() {
        System.out.println("Testing Method getColour...");
        card = new Card(Card.CardType.REVERSE, Card.Colour.YELLOW);
        assertEquals(Card.Colour.YELLOW, card.getColour());
        counter = 1;
    }

    @Test
    public void test_toString() {
        System.out.println("Testing Method toString");
        card = new Card(Card.CardType.SEVEN, Card.Colour.RED);
        assertEquals(" RED SEVEN", card.toString());
        counter = 1;
    }
}
