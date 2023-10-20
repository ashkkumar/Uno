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

    public PlayerTest(){}

    @BeforeEach
    public void setUp(){
        player = null;
    }

    @AfterEach
    public void summary(){
        System.out.println("Number of Tests Completed: " + counter + "\n");
    }
    @Test
    public void test_DefaultConstructor(){
        System.out.println("Testing Default Constructor");
        ArrayList<Card> empty_list = new ArrayList<>();
        player = new Player();
        assertEquals(0, player.getNumCards());
        //assertEquals(empty_list, player.getMyCards());

    }

    @Test
    public void test_addCard(){
        System.out.println("Testing Method addCard");
    }

    @Test
    public void test_playCard(){
        System.out.println("Testing Method removeCard");
    }

    @Test
    public void test_setScore(){
        System.out.println("Testing Method setScore");
    }

    @Test
    public void test_getScore(){
        System.out.println("Testing Method getScore");
    }

    @Test
    public void test_getNumCards(){
        System.out.println("Testing Method getNumCards");
    }

    @Test
    public void test_getMyCards(){
        System.out.println("Testing Method getMyCards");
    }

    @Test
    public void test_drawCard(){
        System.out.println("Testing Method drawCard");
    }

    @Test
    public void test_setName(){
        System.out.println("Testing Method setName");
    }

    @Test
    public void test_getName(){
        System.out.println("Testing Method getName");
    }
}
