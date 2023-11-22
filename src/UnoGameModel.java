import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnoGameModel {

    private boolean darkSide = false;
    private Deck deck;
    private ArrayList<Player> players;
    private Card startingCard;
    private Card topCard;
    private Card playedCard;

    private Player currentPlayer;

    private int playerIndex = 0;
    private boolean finished;

    private final List<UnoViewHandler> views;
    private Card.Colour playedColour;
    private Card.CardType playedType;
    private Card.Colour topColour;
    private Card.CardType topType;

    /**
     * Initializes the UnoGameModel, creating players, deck, and starting card, and deals the initial set of cards.
     */
    public UnoGameModel(int playerCount) {
        initializeGame(playerCount);

        views = new ArrayList<>();
    }

    /**
     * Initializes the Uno game by creating players, initializing the deck, and dealing the initial set of cards.
     * This method sets up the starting card, current player, and other game-related attributes.
     */
    private void initializeGame(int playerCount) {
        createPlayers(playerCount);
        this.deck = new Deck();
        this.startingCard = deck.draw();
        this.topCard = startingCard;
        this.topColour = topCard.getColour();
        this.topType = topCard.getCardType();
        this.finished = false;
        currentPlayer = players.get(playerIndex);
        dealCards();
    }

    /**
     * Gets the current player in the game.
     *
     * @return The current player.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Gets the colour of the top card
     * @return top colour
     */
    public Card.Colour getTopColour(){
        return this.topColour;
    }

    /**
     * Gets the card that has been played.
     *
     * @return The played card.
     */
    public Card getPlayedCard(){
        return this.playedCard;
    }

    /**
     * Gets the starting card of the game.
     *
     * @return The starting card.
     */
    public Card getStartingCard(){
        return this.startingCard;
    }

    /**
     * Advances the game to the next player in a circular manner.
     * This method updates the player index to the next player in the list.
     */
    public void skip() {
        this.playerIndex = (playerIndex + 1) % players.size();
    }

    /**
     * Handles the selection of a new color for a wild card.
     *
     * @param chosenColour The chosen color for the wild card.
     */
    public void wildCard(Card.Colour chosenColour) {
        // Handle the selection of a new colour for a wild c
        topColour = chosenColour;
        topType = Card.CardType.WILD;
        currentPlayer.setCanPlay(false);

    }

    /**
     * Flips the state of the game
     */
    public void flip(){
        if (topCard.getCardType() ==  Card.CardType.FLIP){
            darkSide = !darkSide;
            currentPlayer.setCanPlay(false);
        }
    }

    /**
     * Reverses the order of players in the game.
     * This method updates the player index and reverses the list of players.
     */
    public void reverse() {
        if (players.size() != 2){
            playerIndex = ((players.size() - playerIndex) % players.size()) - 1;
        }
        Collections.reverse(players);
    }

    /**
     * Draws a specified number of cards for the next player in the game.
     *
     * @param numCards     The number of cards to draw.
     * @param playerIndex  The index of the player drawing the cards.
     * @return Card the card that was drawn
     */

    public Card drawN(int numCards, int playerIndex) {
        if (numCards == 1){
            return players.get((playerIndex + 1) % players.size()).drawCard(deck);
        } else {
            for (int i = 0; i < numCards; i++) {
                players.get((playerIndex + 1) % players.size()).drawCard(deck);
            }
            return null;
        }
    }

    /**
     * Draws one card from the deck and adds it to the current player's hand.
     *
     * @return The card drawn from the deck.
     */
    public Card drawOne(){

        Card card = deck.draw();
        currentPlayer.addCard(card);
        return card;
    }

    /**
     * Checks if the top card played has any action associated with it (e.g., reverse, skip, draw two).
     * This method handles the logic for action cards and updates the game state accordingly.
     *
     * @return True if the top card has an action associated with it; false otherwise.
     */
    public boolean checkActionCard() {
        if (topCard.getCardType().equals(Card.CardType.REVERSE)) {
            reverse();
            return true;
        } else if (topCard.getCardType().equals(Card.CardType.SKIP)) {
            if (isDarkSide()){
                this.playerIndex = (playerIndex + players.size() -1 ) % players.size();
            } else {
                skip();
            }
            return true;
        } else if (topCard.getCardType().equals(Card.CardType.DRAW_ONE)) {
            if (isDarkSide()){
                drawN(5, playerIndex);
                skip();
                return true;
            } else {
                drawN(1, playerIndex);
                skip();
            }
            return true;
        } else if (topCard.getCardType().equals(Card.CardType.FLIP)){
            flip();
            return true;
        }
        return false;
    }

    /**
     * Gets the index of the current player in the game.
     *
     * @return The index of the current player.
     */
    public int getCurrentPlayerIndex(){
        return playerIndex;
    }

    /**
     * Creates a specified number of players in the game.
     *
     * @param n The number of players to create.
     */
    public void createPlayers(int n) {
        this.players = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Player player = new Player();
            player.setName(Integer.toString(i+1));
            players.add(player);
        }
    }

    /**
     * Deals the initial set of cards to each player in the game.
     * This method is called at the beginning of the game to distribute cards to players.
     */
    public void dealCards() {
        for (int i = 0; i < 7; i++) {
            for (Player player : players) {
                player.drawCard(deck);
            }
        }
    }

    /**
     * Selects a card to play in the game.
     *
     * @param card The card to be played.
     * @return True if the card is a valid choice and the player can play it; false otherwise.
     */
    public boolean selectCard(Card card) {
        playedCard = card;
        if (isValidChoice() && currentPlayer.canPlay()) {
            topCard = playedCard;
            topColour = topCard.getColour();
            topType = topCard.getCardType();
            currentPlayer.setCanPlay(false);
            return true;
        }
        return false;
    }

    /**
     * Checks whether the played card is a valid choice to be played on the current top card.
     * This method evaluates the color and type of the played card against the color and type of the top card
     * to determine if the move is valid according to Uno rules.
     *
     * @return True if the played card is a valid choice; false otherwise.
     */
    public boolean isValidChoice() {
        if (playedCard == null || topCard == null) {
            return false;
        }

        playedColour = playedCard.getColour();
        playedType = playedCard.getCardType();

        if ((topColour == playedColour || topType == playedType) && (playedColour != null || playedType != null)) {
            return true;
        }

        if (playedType == Card.CardType.WILD || playedType == Card.CardType.WILD_DRAW_TWO) {
            return true;
        }

        return false;
    }

    /**
     * Method used when a last card is played but the player doesn't
     * have enough winning points
     */
    public void notEnoughPoints(){
        for (Player i : players) {
            i.getMyCards().clear();
        }
        deck = new Deck();
        dealCards();
    }

    /**
     * Checks if the game is on the dark or light side
     *
     * @return True if the dark is on the dark side false otherwise
     */
    public boolean isDarkSide(){
        return darkSide;
    }

    /**
     * Gets whether the current player has drawn a card.
     *
     * @return True if the current player has  drawn a card; false otherwise.
     */
    public boolean hasDrawn(){
        return currentPlayer.getHasDrawn();
    }

    /**
     * Moves to the next layer in the game.
     * Resets the playability and draw status of the current player.
     */
    public void nextPlayer() {
        currentPlayer.setCanPlay(true);
        currentPlayer.setHasDrawn(false);
        playerIndex++;
        if (playerIndex>= players.size()) {
            playerIndex = 0;
        }
        currentPlayer = players.get(playerIndex);
    }

    /**
     * Checks if a player has won the game by emptying their hand and calculates their score.
     *
     * @return True if there is a winner and false otherwise
     */
    public boolean checkWinner() {
        if (currentPlayer.getNumCards() == 0) {
            int score = 0;
            for (Player player : players) {
                for (Card card : player.getMyCards()) {
                    score += card.getCardType().cardScore;
                }
            }
            score += currentPlayer.getScore();
            currentPlayer.setScore(score);
            return true;
        }
        return false;
    }

}
