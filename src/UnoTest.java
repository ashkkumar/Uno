/**
 * Uno Class Testing
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class UnoTest {
    private static Uno uno;
    private static int counter;

    public UnoTest(){}

    @BeforeAll
    public static void setUp(){
        uno = null;
        uno = new Uno();
    }

    @AfterEach
    public void summary(){
        System.out.println("Number of Tests Completed: " + counter + "\n");
    }
    @Test
    public void test_DefaultConstructor(){
        System.out.println("Testing Default Constructor...");
        assertEquals(new ArrayList<Player>(), uno.getPlayers());
        assertEquals(100, uno.getDeck().getNumDeckCards());
        counter = 3;
    }

    @Test
    public void test_giveCards(){
        System.out.println("Testing Method giveCards...");
        uno.getPlayers().add(new Player());
        uno.getPlayers().add(new Player());
        uno.giveCards();
        assertEquals(7,uno.getPlayers().get(0).getNumCards());
        assertEquals(7,uno.getPlayers().get(1).getNumCards());
        counter = 2;
    }


    @Test
    public void test_reverse(){
        System.out.println("Testing Method reverse...");
        ArrayList<Player> test_players = uno.getPlayers();
        Collections.reverse(test_players);
        uno.reverse();
        assertEquals(test_players,uno.getPlayers());
        counter = 1;
    }

    @Test
    public void test_getDeckCards(){
        System.out.println("Testing Method getDeckCards...");
        assertEquals(100,uno.getDeckCards());
        counter = 1;
    }

}
