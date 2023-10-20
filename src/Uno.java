import java.util.ArrayList;
import java.util.Scanner;

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



    public Uno(){

        players = new ArrayList<Player>();
        discardPile = new ArrayList<Card>();
        deck = new Deck();

    }

    public void play(){

        System.out.println("");

    }

    public void giveCards(){
        return;
    }

    public void drawTwo(){
        return;
    }

    public void wildCard(){
        return;
    }

    public void reverse(){
        return;
    }

    public void skip(){
        return;
    }

    public void isValidChoice(){

    }
}
