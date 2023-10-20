import java.util.ArrayList;
import java.util.Scanner;

public class Uno {

    private ArrayList<Player> players;

    private ArrayList<Card> discardPile;

    private Deck deck;

    private Card startingCard;
    private Card playedCard;

    private Card topCard;
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
        startingCard = deck.draw();
        topCard = startingCard;

        for (Player player : players) {
            if (player.getName() == null) {
                System.out.print("Enter player name: ");
                String name = choice.nextLine();
                player.setName(name);
            }
        }

        System.out.println("Starting Card:" + startingCard.toString());

        while (!finished){

            for (Player player: players){
                takeTurn(player);
            }
            finished = true;

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
        } else {
            System.out.println("Enter a valid number of players.");
        }
    }

    public int getDeckCards(){
        return deck.getNumDeckCards();
    }

    public void changePlayer(){

    }

    public void takeTurn(Player player){
        currentPlayer = player;
        System.out.println(currentPlayer.getName() + "'s Turn.");
        System.out.println("Current Side: Light");
        currentPlayer.getMyCards();
        System.out.println("\nTop Card: " + topCard.toString());

        System.out.println("Enter card index to play or 0 to draw card:");
        int index = choice.nextInt();
        if (index > 0){

            playedCard = currentPlayer.playCard(index-1);

        } else {
            player.drawCard(deck);
            currentPlayer.getMyCards();
        }
    }
}
