import java.util.ArrayList;
import java.util.Collections;
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

    /**
     * When this method is called, it prompts the user to enter a colour of their choice
     * to change the current card's colour
     */
    public void wildCard(){
        choice = new Scanner(System.in);

        while (true) {
            String colourChoice = choice.next();
            Card.Colour chosenColour = null;

            for (Card.Colour colour : Card.Colour.values()) {
                if (colour.name().equalsIgnoreCase(colourChoice)) {
                    //Card is valid
                    chosenColour = colour;
                    break;
                }
            }
            if (chosenColour != null) {
                currentColour = chosenColour;
                break;
            } else {
                System.out.println("Invalid colour choice. Please choose a valid colour.");
            }
        }

    }

    /**
     * Reverses the direction of player's turns
     */
    public void reverse(){
        Collections.reverse(players);
    }

    /**
     * Skips the current players turn and gives turn to next player
     */
    public void skip(){
        int currPlayerIndex = players.indexOf(currentPlayer);
        currentPlayer = players.get(currPlayerIndex + 1);
    }

    /**
     * Validates the current card with the card being played
     * @return true if the played card's colour or type matches the current card, false otherwise
     */
    public boolean isValidChoice(){
        if ((currentColour == playedCard.getColour())||(currentNumber == playedCard.getCardType())){
            return true;
        }
        else return false;
    }

    public void createPlayers(int n){
        if (n >= 2 && n <=4) {
            while (n > 0) {
                players.add(new Player());
                n--;
            }
        }
    }
}
