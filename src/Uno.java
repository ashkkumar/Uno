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

    private Player nextPlayer;
    private Player currentPlayer;

    private Card.CardType currentNumber;

    private Card.Colour currentColour;

    private Player winner;

    int playerIndex;

    private boolean finished;

    /**
     * Constructs a new Uno game by initializing player and card-related attributes.
     */
    public Uno() {

        players = new ArrayList<Player>();
        discardPile = new ArrayList<Card>();
        deck = new Deck();

    }

    public ArrayList<Player> getPlayers(){
        return this.players;
    }

    public ArrayList<Card> getDiscardPile(){
        return this.discardPile;
    }

    public Deck getDeck(){
        return this.deck;
    }

    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }

    /**
     * Begins and manages the Uno game, including player turns, card actions, and determining the winner.
     */
    public void play() {
        playerIndex = 0;
        System.out.print("Enter number of players (2-4):");
        createPlayers();
        setPlayerNames();
        startingCard = deck.draw();
        System.out.println("Starting card:" + startingCard.toString());

        topCard = startingCard;
        currentColour = startingCard.getColour();
        currentNumber = startingCard.getCardType();
        if (currentNumber.equals(Card.CardType.REVERSE)){
            reverse();
            System.out.println("Order has been reversed");
        } else if (currentNumber.equals(Card.CardType.SKIP)){
            skip();
        } else if (currentNumber.equals(Card.CardType.DRAW_ONE)){
            drawN(1,0);
        }

        while (!finished) {

            if (playerIndex > players.size()-1){
                playerIndex = 0;
            }
            Player player = players.get(playerIndex);
            takeTurn(player);
            checkWinner();
            if (currentPlayer.getScore() >= 500){
                winner = currentPlayer;
                System.out.println("The winner is: " + winner.getName());
                finished = true;
                break;
            }


        }

    }

    /**
     * Populates each player's hand with Uno cards from the deck.
     */
    public void giveCards() {
        for (int i = 0; i < 7; i++) {
            for (Player player : players) {
                player.drawCard(deck);
            }
        }
    }

    /**
     * Handles the selection of a new color for a wild card played by the current player.
     */
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

    /**
     * Reverses the order of play.
     */
    public void reverse() {
        playerIndex = ((players.size() - playerIndex) % players.size());
            Collections.reverse(players);
    }

    /**
     * Skips the next player's turn in the game.
     */
    public void skip() {

        this.playerIndex = (playerIndex + 2) % players.size();

    }

    /**
     * Checks if the player's choice of card to play is valid based on the current top card.
     *
     * @return True if the choice is valid, false otherwise.
     */
    public boolean isValidChoice() {
        if ((currentColour == playedCard.getColour()) || (currentNumber == playedCard.getCardType())) {
            return true;
        }
        if (playedCard.getCardType().equals(Card.CardType.WILD) || playedCard.getCardType().equals(Card.CardType.WILD_DRAW_TWO)){
            return true;
        }
        if (startingCard.getColour().equals(Card.Colour.WILD)){
            return true;
        }
        return false;
    }

    /**
     * Creates a specified number of players for the Uno game.
     */
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

    /**
     * Gets the number of cards remaining in the Uno deck.
     *
     * @return The count of cards in the Uno deck.
     */
    public int getDeckCards() {
        return deck.getNumDeckCards();
    }

    /**
     * Manages a player's turn, including card selection, drawing cards, and card actions.
     *
     * @param player The current player taking their turn.
     */
    public void takeTurn(Player player) {
        currentPlayer = player;
        printTurn();

        while (true) {
            if (choice.hasNextInt()) {
                int index = choice.nextInt();

                if (index > 0 && index <= currentPlayer.getNumCards()) {
                    playedCard = currentPlayer.playCard(index - 1);

                    if (isValidChoice()) {
                        setTopCard();
                        currentPlayer.removeCard(index - 1);
                        if (!checkActionCard()) {
                            playerIndex++;
                        }
                        break;
                    } else {
                        System.out.println("Choose a valid card");
                    }
                } else if (index == 0) {
                    player.drawCard(deck);
                    playerIndex ++;
                    break;
                } else {
                    System.out.println("Invalid card index. Please choose a valid card or press 0 to draw a card.");
                    choice.nextLine(); // Consume the invalid input
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                choice.next(); // Consume the non-integer input
            }
        }
    }

    /**
     * Displays the current player's turn information, including their cards and the top card.
     */
    public void printTurn(){
        System.out.println(currentPlayer.getName() + "'s Turn.");
        System.out.println("Current Side: Light");
        currentPlayer.displayCards();
        System.out.println("\nTop Card: " + topCard.toString());

        System.out.println("Enter card index to play or 0 to draw card:");
    }

    /**
     * Draws N amount of cards for the next player
     */
    public void drawN(int numCards, int playerIndex){
        for (int i = 0; i < numCards; i++){
            players.get(playerIndex+1 % players.size()).drawCard(deck);
        }
        skip();
    }

    /**
     * Checks and handles action cards like "Reverse", "Wild","Skip" and "WILD_DRAW_TWO" in the game.
     */
    public boolean checkActionCard(){
        if (topCard.getCardType().equals(Card.CardType.REVERSE)){
            reverse();
            return true;
        }
        else if (topCard.getCardType().equals(Card.CardType.WILD)){
            System.out.println("Select a new colour: (RED, BLUE, GREEN, YELLOW)");
            wildCard();
            playerIndex ++;
            return true;
        }
        else if (topCard.getCardType().equals(Card.CardType.SKIP)) {
            skip();
            return true;
        }
        else if (topCard.getCardType().equals(Card.CardType.DRAW_ONE)){
            drawN(1,playerIndex);
            return true;
        }
        else if (topCard.getCardType().equals(Card.CardType.WILD_DRAW_TWO)){
            System.out.println("Select a new colour: (RED, BLUE, GREEN, YELLOW)");
            wildCard();
            drawN(2,playerIndex);
            return true;
        }
        return false;
    }

    /**
     * Sets the current top card to the card played by the current player.
     */
    public void setTopCard(){
        topCard = playedCard;
        currentNumber = topCard.getCardType();
        currentColour = topCard.getColour();
    }

    /**
     * Checks if a player has won the game by emptying their hand and calculates their score.
     */
    public void checkWinner(){
        if (currentPlayer.getNumCards() == 0){
            int score = 0;
            for (Player player: players){
                for (Card card: player.getMyCards()){
                    score += card.getCardType().cardScore;
                }
            }
            currentPlayer.setScore(score);
        }
    }

    /**
     * Sets the names of players who have not provided their names yet.
     */
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
