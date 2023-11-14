import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnoGameModel {
    private Deck deck;
    private ArrayList<Player> players;
    private Card startingCard;
    private Card topCard;
    private Card playedCard;

    private Player currentPlayer;

    private int playerIndex = 0;
    private boolean finished;

    private final List<UnoViewHandler> views;

    private Card.Colour playedColor;
    private Card.CardType playedType;
    private Card.Colour topColor;
    private Card.CardType topType;

    private Card.CardType currentType;
    /**
     * Initializes the UnoGameModel, creating players, deck, and starting card, and deals the initial set of cards.
     */
    public UnoGameModel() {
        initializeGame();
        views = new ArrayList<>();
    }
    /**
     * Initializes the Uno game by creating players, initializing the deck, and dealing the initial set of cards.
     * This method sets up the starting card, current player, and other game-related attributes.
     */
    private void initializeGame() {
        createPlayers(4);
        this.deck = new Deck();
        this.startingCard = deck.draw();
        this.topCard = startingCard;
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
     * @param chosenColor The chosen color for the wild card.
     */
    public void wildCard(Card.Colour chosenColor) {
        // Handle the selection of a new color for a wild c
        topColor = chosenColor;
    }
    /**
     * Reverses the order of players in the game.
     * This method updates the player index and reverses the list of players.
     */
    public void reverse() {
        playerIndex = ((players.size() - playerIndex) % players.size());
        Collections.reverse(players);
    }
    /**
     * Draws a specified number of cards for the next player in the game.
     *
     * @param numCards     The number of cards to draw.
     * @param playerIndex  The index of the player drawing the cards.
     */
    public void drawN(int numCards, int playerIndex) {
        for (int i = 0; i < numCards; i++) {
            players.get((playerIndex + 1) % players.size()).drawCard(deck);
        }
        skip();
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
            skip();
            return true;
        } else if (topCard.getCardType().equals(Card.CardType.DRAW_ONE)) {
            drawN(1, playerIndex);
            return true;
        } else if (topCard.getCardType().equals(Card.CardType.WILD_DRAW_TWO)) {
            //UnoViewHandler.notifyAllViews(new UnoGameEvent(this)); // Notify views about the Wild card
            // Handle wildCard logic in the view and update the playedCard accordingly
            drawN(2, playerIndex);
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
        while (n > 0) {
            Player player = new Player();
            players.add(player);
            n--;
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

        playedColor = playedCard.getColour();
        playedType = playedCard.getCardType();
        topColor = topCard.getColour();
        topType = topCard.getCardType();

        if ((topColor == playedColor || topType == playedType) && (playedColor != null || playedType != null)) {
            return true;
        }

        if (playedType == Card.CardType.WILD || playedType == Card.CardType.WILD_DRAW_TWO) {
            return true;
        }

        if (startingCard.getColour() == Card.Colour.WILD) {
            return true;
        }

        return false;
    }
    /**
     * Gets whether the current player has drawn a card.
     *
     * @return True if the current player has not drawn a card; false otherwise.
     */
    public boolean hasNotDrawn(){
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
        if (playerIndex == players.size()) {
            playerIndex = 0;
        }
        currentPlayer = players.get(playerIndex);
    }
    /**
     * Adds a view to the list of views observing the model.
     *
     * @param view The UnoViewHandler to be added.
     */
    public void addView(UnoViewHandler view) {
        views.add(view);
    }
    /**
     * Removes a view from the list of views observing the model.
     *
     * @param view The UnoViewHandler to be removed.
     */
    public void removeView(UnoViewHandler view) {
        views.remove(view);
    }

}
