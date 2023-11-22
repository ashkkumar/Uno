/**
 * Player Class Testing
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    private static Player player;
    private static int counter;

    public PlayerTest() {
    }

    /**
     * Sets test player and counter to default values before each test
     */
    @BeforeEach
    public void setUp() {
        player = null;
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
        ArrayList<Card> empty_list = new ArrayList<>();
        player = new Player();
        assertEquals(0, player.getNumCards());
        assertEquals(empty_list, player.getMyCards());
        counter = 2;

    }

    @Test
    public void test_getScore() {
        System.out.println("Testing Method getScore...");
        player = new Player();
        player.setScore(150);
        assertEquals(150, player.getScore());
        counter = 1;
    }

    @Test
    public void test_getNumCards() {
        System.out.println("Testing Method getNumCards...");
        player = new Player();
        player.addCard(new Card(Card.CardType.FOUR, Card.Colour.YELLOW));
        player.addCard(new Card(Card.CardType.SEVEN, Card.Colour.RED));
        player.addCard(new Card(Card.CardType.REVERSE, Card.Colour.BLUE));
        assertEquals(3, player.getNumCards());
        counter = 1;
    }

    @Test
    public void test_getMyCards() {
        System.out.println("Testing Method getMyCards...");
        ArrayList<Card> test_list = new ArrayList<>();
        test_list.add(new Card(Card.CardType.FOUR, Card.Colour.YELLOW));
        test_list.add(new Card(Card.CardType.SEVEN, Card.Colour.RED));
        test_list.add(new Card(Card.CardType.REVERSE, Card.Colour.BLUE));

        player = new Player();
        player.addCard(new Card(Card.CardType.FOUR, Card.Colour.YELLOW));
        player.addCard(new Card(Card.CardType.SEVEN, Card.Colour.RED));
        player.addCard(new Card(Card.CardType.REVERSE, Card.Colour.BLUE));
        for (int i = 0; i < test_list.size(); i++) {
            assertEquals(test_list.get(i).getCardType(), player.getMyCards().get(i).getCardType());
            assertEquals(test_list.get(i).getColour(), player.getMyCards().get(i).getColour());
            counter += 2;
        }
    }

    @Test
    public void test_getName() {
        System.out.println("Testing Method getName...");
        player = new Player();
        player.setName("Tommy");
        assertEquals("Tommy", player.getName());
        counter = 1;
    }

    @Test
    public void test_setScore() {
        System.out.println("Testing Method setScore...");
        player = new Player();
        player.setScore(50);
        assertEquals(50, player.getScore());
        player.setScore(0);
        assertEquals(0, player.getScore());
        counter = 2;
    }

    @Test
    public void test_setName() {
        System.out.println("Testing Method setName...");
        player = new Player();
        player.setName("Victor");
        assertEquals("Victor", player.getName());
        counter = 1;
    }

    @Test
    public void test_addCard() {
        System.out.println("Testing Method addCard...");
        player = new Player();
        player.addCard(new Card(Card.CardType.FOUR, Card.Colour.YELLOW));
        assertEquals(1, player.getNumCards());
        assertEquals(Card.CardType.FOUR, player.getMyCards().get(0).getCardType());
        assertEquals(Card.Colour.YELLOW, player.getMyCards().get(0).getColour());
        counter = 3;
    }

    @Test
    public void test_drawCard() {
        System.out.println("Testing Method drawCard...");
        Deck deck = new Deck();
        player = new Player();
        player.drawCard(deck);
        assertEquals(1, player.getNumCards());
        assertEquals(103, deck.getNumDeckCards());
        counter = 2;
    }

    @Test
    public void test_removeCard() {
        System.out.println("Testing Method removeCard...");
        player = new Player();
        player.addCard(new Card(Card.CardType.FOUR, Card.Colour.YELLOW));
        player.addCard(new Card(Card.CardType.SEVEN, Card.Colour.RED));
        player.addCard(new Card(Card.CardType.REVERSE, Card.Colour.BLUE));
        //player.removeCard(1);
        assertEquals(2, player.getNumCards());
        assertEquals(Card.CardType.FOUR, player.getMyCards().get(0).getCardType());
        assertEquals(Card.Colour.YELLOW, player.getMyCards().get(0).getColour());
        assertEquals(Card.CardType.REVERSE, player.getMyCards().get(1).getCardType());
        assertEquals(Card.Colour.BLUE, player.getMyCards().get(1).getColour());
        counter = 5;
    }

    @Test
    public void test_playCard() {
        System.out.println("Testing Method removeCard...");
        player = new Player();
        player.addCard(new Card(Card.CardType.FOUR, Card.Colour.YELLOW));
        player.addCard(new Card(Card.CardType.SEVEN, Card.Colour.RED));
        player.addCard(new Card(Card.CardType.REVERSE, Card.Colour.BLUE));
        Card played = player.playCard(1);
        assertEquals(Card.CardType.SEVEN, played.getCardType());
        assertEquals(Card.Colour.RED, played.getColour());
        counter = 2;
    }
}
