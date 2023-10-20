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

    private boolean finished;



    public Uno(){

        players = new ArrayList<Player>();
        discardPile = new ArrayList<Card>();
        deck = new Deck();

    }

    public void play() {

        System.out.print("Enter number of players (2-4):");
        choice = new Scanner(System.in);
        int n = choice.nextInt();
        createPlayers(n);
        choice.nextLine();
        giveCards();

        for (Player player : players) {
            if (player.getName() == null) {
                System.out.print("Enter player name: ");
                String name = choice.nextLine();
                player.setName(name);
            }
        }

    }

    public void giveCards() {
        for (int i = 0; i < 7; i++) {
            for (Player player : players) {
                player.drawCard(deck);
            }
        }
    }


    public void drawOne(){
        return;
    }

    public void wildCard(){

    }

    public void reverse(){
        return;
    }

    public void skip(){
        return;
    }

    public void isValidChoice(){

    }

    public void createPlayers(int n){
        if (n >= 2 && n <=4) {
            while (n > 0) {
                players.add(new Player());
                n--;
            }
        }
    }

    public int getDeckCards(){
        return deck.getNumDeckCards();
    }
}
