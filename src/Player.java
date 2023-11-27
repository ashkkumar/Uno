import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Player {

    private String name;

    private ArrayList<Card> myCards;

    private int score;

    private int numCards;

    private boolean canPlay = true;

    private boolean hasDrawn = false;

    private boolean isAI = false;


    /**
     * Constructs a new Uno player with an empty card list and a score of 0.
     */
    public Player() {

        myCards = new ArrayList<Card>();
        numCards = 0;
    }

    /**
     * Gets the player's score.
     *
     * @return The player's score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Checks the canPlay variable
     * @return if the current player is able to play or not
     */
    public boolean canPlay(){
        return canPlay;
    }

    /**
     * Gets the number of cards in the player's hand.
     *
     * @return The count of cards in the player's hand.
     */
    public int getNumCards() {
        return numCards;
    }

    /**
     * Sets the number of cards in the player's hand.
     * @param num the number of cards to set.
     */
    public void setNumCards(int num){
        this.numCards = num;
    }

    /**
     * Gets the hasDrawn variable and returns it to let the system know if the current player has drawn
     * @return the hasDrawn variable
     */
    public boolean getHasDrawn(){
        return hasDrawn;
    }

    /**
     * Gets the list of cards in the player's hand.
     *
     * @return An ArrayList containing the player's cards.
     */
    public ArrayList<Card> getMyCards() {
        return myCards;
    }

    /**
     * Gets the name of the player.
     *
     * @return The name of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the player's score.
     *
     * @param score The score to set for the player.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Sets the name of the player.
     *
     * @param name The name to set for the player.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the canPlay variable to true or false
     * @param bool the value that sets canPlay
     */
    public void setCanPlay(Boolean bool){
        this.canPlay = bool;
    }

    /**
     * Sets the hasDrawn variable to true or false
     * @param hasDrawn the value to set hasDrawn to
     */
    public void setHasDrawn(boolean hasDrawn) {
        this.hasDrawn = hasDrawn;
    }

    /**
     * Adds a card to the player's hand.
     *
     * @param card The Uno card to add to the player's hand.
     */
    public void addCard(Card card) {
        myCards.add(card);
        numCards++;
    }

    /**
     * Draws a card from the deck and adds it to the player's hand.
     *
     * @param deck The Uno deck from which to draw a card.
     */
    public Card drawCard(Deck deck) {
        Card drawnCard = deck.draw();
        myCards.add(drawnCard);
        numCards++;
        return drawnCard;
    }

    /**
     * Removes a card from the player's hand by its index.
     *
     * @param card The index of the card to remove from the player's hand.
     */
    public void removeCard(Card card) {
        myCards.remove(card);
        numCards--;
    }

    /**
     * Plays a card from the player's hand.
     *
     * @param index The index of the card to play in the player's hand.
     * @return The Uno card played.
     */
    public Card playCard(int index) {
        return myCards.get(index);
    }

    /**
     * Displays the player's cards.
     */
    public void displayCards() {
        System.out.println("Your Cards:");
        for (int i = 0; i < myCards.size(); i++) {
            System.out.println(i + 1 + "." + myCards.get(i).toString());
        }
    }

    /**
     * Checks if the current player is AI or human
     * @return true for ai false for human
     */
    public boolean isAI(){
        return isAI;
    }

    /**
     * Sets the isAI variable to true
     */
    public void setAITrue(){
        isAI = true;
    }

    /**
     * Prints the list of cards of the current player
     */
    public void printMyCards() {
        System.out.println("My Cards:");
        for (Card card : myCards) {
            System.out.println(card.toString());
        }
    }

    /**
     * Gets the best playable card for the current ai player
     * @param type the type of the top card
     * @param colour the colour of the top card
     * @return the chosen index for the best card
     */
    public int getBestCardIndex(Card.CardType type, Card.Colour colour) {

        ArrayList<Card> playableCards = new ArrayList<>();

        for (Card card : myCards) {
            if (card.getColour() == colour || card.getCardType() == type || card.getColour() == Card.Colour.WILD) {
                playableCards.add(card);
            }
        }

        if (playableCards.isEmpty()) {

            return -1; //return -1 to indicate that the AI player drew a card
        }

        if (!playableCards.isEmpty()) {
            playableCards.sort(Comparator.comparingInt(Card::getScore).reversed());
            Card bestCard = playableCards.get(0);

            System.out.println("Best Card Index from the Player class: " + myCards.indexOf(bestCard));

            return myCards.indexOf(bestCard);
        } else {
            throw new RuntimeException("Unexpected error: No best card found.");
        }

    }

    /**
     * gets the specified card based off of index
     * @param i the index of the card to be returned
     * @return the card at index i
     */
    public Card getCard(int i){
        return this.myCards.get(i);
    }


}

