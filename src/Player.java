import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Represents a player in the Uno game, with attributes such as name, score, and a hand of cards.
 */
public class Player implements Serializable {

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
     * Gets whether the player can play in the current turn.
     *
     * @return True if the player can play; false otherwise.
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
     * Gets whether the player has drawn a card in the current turn.
     *
     * @return True if the player has drawn; false otherwise.
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
     * Sets whether the player can play in the current turn.
     *
     * @param bool True if the player can play; false otherwise.
     */
    public void setCanPlay(Boolean bool){
        this.canPlay = bool;
    }

    /**
     * Sets whether the player has drawn a card in the current turn.
     *
     * @param hasDrawn True if the player has drawn; false otherwise.
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
     * Gets whether the player is controlled by AI.
     *
     * @return True if the player is AI; false otherwise.
     */
    public boolean isAI(){
        return isAI;
    }

    /**
     * Sets the player to be controlled by AI.
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
     * Gets the index of the best card to play from the player's hand.
     *
     * @param type   The type of the top card on the discard pile.
     * @param colour The colour of the top card on the discard pile.
     * @return The index of the best card to play from the player's hand.
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
     * Gets the index of the best card to play from the player's hand.
     *
     * @param /type The type of the top card on the discard pile.
     * @param /colour The colour of the top card on the discard pile.
     * @return The index of the best card to play from the player's hand.
     */
    public Card getCard(int i){
        return this.myCards.get(i);
    }

    public String toXML(){
        StringBuilder xmlBuilder = new StringBuilder();


        xmlBuilder.append("\n<Player>\n");
        xmlBuilder.append("\t<Cards>\n");
        for (Card card: myCards){
            xmlBuilder.append(card.toXML());
        }
        xmlBuilder.append("\t</Cards>\n");


        xmlBuilder.append("</Player>");
        return xmlBuilder.toString();
    }


}

