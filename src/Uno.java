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

    public void play(){

        System.out.print("Enter number of players (2-4):");
        Scanner intPlayers = new Scanner(System.in);
        int n = intPlayers.nextInt();
        createPlayers(n);
        giveCards();

        currentPlayer = players.get(0);
        currentPlayer.getMyCards();

    }

    public void giveCards() {
        for (int i = 0; i < 7; i++) {
            for (Player player : players) {
                player.drawCard(deck);
            }
        }
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

    public void createPlayers(int n){
        if (n >= 2 && n <=4) {
            while (n > 0) {
                Scanner scan = new Scanner(System.in);
                String name = scan.toString();
                players.add(new Player(name));
                n--;
            }
        }
    }
}
