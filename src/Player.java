import java.util.ArrayList;

public class Player {

    private String name;

    private ArrayList<Card> myCards;

    private int score;

    private int numCards;
    /**
     * Constructs a new Uno player with an empty card list and a score of 0.
     */
    public Player(){

        myCards = new ArrayList<Card>();
        numCards = 0;
    }

    /**
     * Adds a card to the player's hand.
     *
     * @param card The Uno card to add to the player's hand.
     */
    public void addCard(Card card){
        myCards.add(card);
        numCards++;
    }

    /**
     * Plays a card from the player's hand.
     *
     * @param index The index of the card to play in the player's hand.
     * @return The Uno card played.
     */
    public Card playCard(int index){
        Card card = myCards.get(index);
        return card;
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
     * Gets the player's score.
     *
     * @return The player's score.
     */
    public int getScore() {
        return score;
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
     * Displays the player's cards.
     */
    public void displayCards() {
        System.out.println("Your Cards:");
        for (int i = 0; i < myCards.size(); i++){
            System.out.println(i+1 + "." + myCards.get(i).toString());
        }
    }

    /**
     * Draws a card from the deck and adds it to the player's hand.
     *
     * @param deck The Uno deck from which to draw a card.
     */
    public void drawCard(Deck deck){
        myCards.add(deck.draw());
        numCards++;
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
     * Sets the name of the player.
     *
     * @param name The name to set for the player.
     */
    public void setName(String name){
        this.name = name;
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
     * Removes a card from the player's hand by its index.
     *
     * @param n The index of the card to remove from the player's hand.
     */
    public void removeCard(int n){
        myCards.remove(n);
        numCards--;
    }
}

