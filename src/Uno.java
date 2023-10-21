import java.util.ArrayList;
import java.util.Collections;
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


    public Uno() {

        players = new ArrayList<Player>();
        discardPile = new ArrayList<Card>();
        deck = new Deck();

    }

    public void play() {

        System.out.print("Enter number of players (2-4):");
        createPlayers();
        setPlayerNames();
        startingCard = deck.draw();
        System.out.println("Starting card:" + startingCard.toString());

        topCard = startingCard;
        currentColour = topCard.getColour();
        currentNumber = topCard.getCardType();

        while (!finished) {

            for (int i = 0; i < players.size(); i++){
                Player player = players.get(i);
                takeTurn(player);
                checkActionCard();
                checkWinner();
                if (currentPlayer.getScore() >= 30){
                    winner = currentPlayer;
                    System.out.println("The winner is: " + winner.getName());
                    finished = true;
                    break;
                }
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


    public void drawOne() {
        return;
    }

    public void wildCard() {

        choice.nextLine();
        while (currentColour.equals(Card.Colour.WILD)) {
            String colourChoice = choice.nextLine();
            colourChoice = colourChoice.toUpperCase(); // Convert to uppercase
            Card.Colour chosenColour = null;

            for (Card.Colour colour : Card.Colour.values()) {
                if (colour.name().equals(colourChoice)) {
                    // Card is valid
                    chosenColour = colour;
                    break;
                }
            }
            if (chosenColour != null) {
                currentColour = chosenColour;
                System.out.println(currentColour + " has been chosen");
                break;
            } else {
                System.out.println("Invalid colour choice. Please choose a valid colour.");
            }
        }

    }

    public void reverse() {
        Collections.reverse(players);
    }

    public void skip() {
        int currPlayerIndex = players.indexOf(currentPlayer);
        currentPlayer = players.get((currPlayerIndex + 1) % players.size());
    }

    public boolean isValidChoice() {
        if ((currentColour == playedCard.getColour()) || (currentNumber == playedCard.getCardType())) {
            return true;
        }
        if (playedCard.getCardType().equals(Card.CardType.WILD)){
            return true;
        }
        return false;
    }

    public void createPlayers() {
        while (players.size() < 2) {
            choice = new Scanner(System.in);
            int n = choice.nextInt();
            if (n < 2 || n > 4) {
                System.out.print("Please select a valid number of players (2-4):");
            } else{
                while (n > 0) {
                    players.add(new Player());
                    n--;
                }
                choice.nextLine();
                giveCards();
            }
        }
    }

    public int getDeckCards() {
        return deck.getNumDeckCards();
    }

    public void takeTurn(Player player) {
        currentPlayer = player;
        checkTopCard();
        printTurn();
        int index = choice.nextInt();
        if (index < currentPlayer.getNumCards()) {
            playedCard = currentPlayer.playCard(index - 1);
            if (isValidChoice()){
                setTopCard();
            } else{
                System.out.println("Choose a valid card");
                int i = choice.nextInt();
                playedCard = currentPlayer.playCard(i - 1);
                setTopCard();
            }
        } else if (index == 0){
            if (currentColour.equals(Card.Colour.WILD)){
                player.drawCard(deck);
            }else {
                player.drawCard(deck);
            }
        }
    }

    public void printTurn(){
        System.out.println(currentPlayer.getName() + "'s Turn.");
        System.out.println("Current Side: Light");
        currentPlayer.displayCards();
        System.out.println("\nTop Card: " + topCard.toString());

        System.out.println("Enter card index to play or 0 to draw card:");
    }

    public void checkTopCard(){
        if (topCard.getCardType().equals(Card.CardType.DRAWONE)){
            currentPlayer.drawCard(deck);
        }
    }

    public void checkActionCard(){
        if (topCard.getCardType().equals(Card.CardType.REVERSE)){
            reverse();
        }
        else if (topCard.getCardType().equals(Card.CardType.WILD)){
            System.out.println("Select a new colour: (RED, BLUE, GREEN, YELLOW)");
            wildCard();
        }
        else if (topCard.getCardType().equals(Card.CardType.SKIP)){
            skip();
        }
    }

    public void setTopCard(){
        topCard = playedCard;
        currentNumber = topCard.getCardType();
        currentColour = topCard.getColour();
    }

    public void checkWinner(){
        if (currentPlayer.getNumCards() == 0){
            int score = 0;
            for (Player player: players){
                for (Card card: player.getMyCards()){
                    score += 10;
                }
            }
            currentPlayer.setScore(score);
        }
    }

    public void setPlayerNames(){
        for (Player player : players) {
            if (player.getName() == null) {
                System.out.print("Enter player name: ");
                String name = choice.nextLine();
                player.setName(name);
            }
        }
    }


}
