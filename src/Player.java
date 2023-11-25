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

    public void setCanPlay(Boolean bool){
        this.canPlay = bool;
    }

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

    public boolean isAI(){
        return isAI;
    }

    public void setAITrue(){
        isAI = true;
    }

    public void printMyCards() {
        System.out.println("My Cards:");
        for (Card card : myCards) {
            System.out.println(card.toString());
        }
    }

    public int getBestCardIndex(Card.CardType type, Card.Colour colour) {
        //Card.CardType.WILD

        ArrayList<Card> playableCards = new ArrayList<>();

        for (Card card : myCards) {
            if (card.getColour() == colour || card.getCardType() == type || card.getColour() == Card.Colour.WILD) {
                playableCards.add(card);
            }
        }

        if (playableCards.isEmpty()) {
            //draw a card, there is nothing you can play

//            model.drawOne();
//            System.out.println("AI Player" + model.getCurrentPlayer() + " drew a card");

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

    public Card getCard(int i){
        return this.myCards.get(i);
    }


}

