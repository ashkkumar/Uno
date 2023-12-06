import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnoGameModel implements Serializable {

    private boolean darkSide = false;
    private Deck deck;

    private Deck discardPile;
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

    public GameSaver save;

    /**
     * Initializes the UnoGameModel, creating players, deck, and starting card, and deals the initial set of cards.
     */
    public UnoGameModel(int playerCount, int aiPlayerCount) {
        initializeGame(playerCount, aiPlayerCount);

        views = new ArrayList<>();
    }

    /**
     * Initializes the Uno game by creating players, initializing the deck, and dealing the initial set of cards.
     * This method sets up the starting card, current player, and other game-related attributes.
     */
    private void initializeGame(int playerCount, int aiPlayers) {

        createPlayers(playerCount, aiPlayers);

        this.deck = new Deck();
        this.discardPile = new Deck();
        this.startingCard = deck.draw();
        this.topCard = startingCard;
        this.topColour = topCard.getColour();
        this.topType = topCard.getCardType();
        this.finished = false;
        currentPlayer = players.get(playerIndex);
        this.save = new GameSaver(this);

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
     * Gets the type of the top card
     * @return top Type
     */

    public Card.CardType getTopType(){return this.topType;}

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
            reshuffleDeck();
            return players.get((playerIndex + 1) % players.size()).drawCard(deck);
        } else {
            for (int i = 0; i < numCards; i++) {
                reshuffleDeck();
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
        reshuffleDeck();
        Card card = deck.draw();
        currentPlayer.addCard(card);
        if (currentPlayer.isAI()){
            currentPlayer.setCanPlay(false);
        }
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
     * Creates a specified number of players in the game, including AI players.
     *
     * @param n     The number of human players to create.
     * @param numAI The number of AI players to create.
     */

    public void createPlayers(int n, int numAI) {
        this.players = new ArrayList<>();

        for (int i = 0; i < n + numAI; i++) {
            Player player = new Player();
            player.setName(Integer.toString(i + 1));
            players.add(player);
            }

        for (int i = players.size() - numAI; i < players.size(); i++) {
            players.get(i).setAITrue(); // set the AI field for the AI players to true
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
            discardPile.addCard(topCard);
            currentPlayer.setCanPlay(false);
            return true;
        }
        return false;
    }

    /**
     * Gets the top card in the game.
     * @return The top card.
     */
    public Card getTopCard(){
        return topCard;
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
            i.setNumCards(0);
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
     * Moves to the next player in the game.
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

        // Check if next player is AI, Block buttons off so that humans can't interfere with AI turn.
        if (currentPlayer.isAI()){
            currentPlayer.setHasDrawn(true);
        }
    }

    /**
     * When deck is empty use discard pile for new deck
     */
    public void reshuffleDeck(){
        if (this.deck.isEmpty()){ // ex draw 5 and only 2 cards left will check if empty after every draw
            deck = discardPile;
            discardPile = new Deck(); // clear discard pile after
        }
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
                    if (isDarkSide()){
                        if (card.getCardType() == Card.CardType.SKIP || card.getCardType() == Card.CardType.DRAW_ONE
                        || card.getCardType() == Card.CardType.WILD_DRAW_TWO){
                            score += card.getScore() + 10;
                        }
                    }
                    score += card.getScore();
                }
            }
            score += currentPlayer.getScore();
            currentPlayer.setScore(score);
            return true;
        }
        return false;

    }

    /**
     * Gets the list of players in the game.
     * @return The list of players.
     */

    public ArrayList<Player> getPlayers(){
        return players;
    }

    /**
     * Saves the current game data
     */
    public void save() throws IOException {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.txt")));

            GameData data = new GameData();

            data.setDeck(this.deck);
            data.setCurrentPlayer(this.currentPlayer);
            data.setDarkSide(this.darkSide);
            data.setDiscardPile(this.discardPile);
            data.setFinished(this.finished);
            data.setPlayedCard(this.playedCard);
            data.setPlayedColour(this.playedColour);
            data.setPlayedType(this.playedType);
            data.setPlayerIndex(this.playerIndex);
            data.setPlayers(this.players);
            data.setStartingCard(this.startingCard);
            data.setTopCard(this.topCard);

            oos.writeObject(data);

        } catch(Exception e){
            System.out.println("could not save game");
        }

    }

    public void load() throws ClassNotFoundException {

        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.txt")));

            GameData data = (GameData) ois.readObject();

            this.darkSide = data.isDarkSide();
            this.discardPile = data.getDiscardPile();
            this.topCard = data.getTopCard();
            this.topType = data.getTopType();
            this.topColour = data.getTopColour();
            this.playerIndex = data.getPlayerIndex();
            this.currentPlayer = data.getCurrentPlayer();
            this.finished = data.isFinished();
            this.players = data.getPlayers();
            this.startingCard = data.getStartingCard();
            this.deck = data.getDeck();
            this.playedCard = data.getPlayedCard();

        } catch (IOException e){
            System.out.println("could not load game");
        }
    }

    public Deck getDeck() {
        return this.deck;
    }

    public Deck getDiscardPile() {
        return this.discardPile;
    }

    public boolean isFinished() {
        return this.finished;
    }

    public void setDarkSide(boolean darkSide) {
        this.darkSide = darkSide;
    }

    public void setDiscardPile(Deck discardPile) {
        this.discardPile = discardPile;
    }

    public void setTopCard(Card topCard) {
        this.topCard = topCard;
    }

    public void setTopType(Card.CardType type) {
        this.topType = type;
    }

    public void setTopColour(Card.Colour topColour) {
        this.topColour = topColour;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void setPlayedColour(Card.Colour playedColour) {
        this.playedColour = playedColour;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    public void setPlayedType(Card.CardType playedType) {
        this.playedType = playedType;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setPlayedCard(Card playedCard) {
        this.playedCard = playedCard;
    }

    public void setStartingCard(Card startingCard) {
        this.startingCard = startingCard;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
