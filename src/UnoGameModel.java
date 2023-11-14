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
    private Card.Colour playedColour;
    private Card.CardType playedType;
    private Card.Colour topColour;
    private Card.CardType topType;

    public UnoGameModel(int playerCount) {
        initializeGame(playerCount);

        views = new ArrayList<>();
    }

    private void initializeGame(int playerCount) {
        createPlayers(playerCount);
        this.deck = new Deck();
        this.startingCard = deck.draw();
        this.topCard = startingCard;
        this.topColour = topCard.getColour();
        this.topType = topCard.getCardType();
        this.finished = false;
        this.topType = topCard.getCardType();
        this.topColour = topCard.getColour();
        currentPlayer = players.get(playerIndex);
        dealCards();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Card.Colour getTopColour(){
        return this.topColour;
    }

    public Card getPlayedCard(){
        return this.playedCard;
    }

    public Card getStartingCard(){
        return this.startingCard;
    }

    public void skip() {
        this.playerIndex = (playerIndex+1) % players.size();
    }

    public void wildCard(Card.Colour chosenColour) {
        // Handle the selection of a new colour for a wild c
        topColour = chosenColour;
        currentPlayer.setCanPlay(false);

    }

    public void reverse() {
        if (players.size() != 2){
            playerIndex = ((players.size() - playerIndex) % players.size()) - 1;
        }
        Collections.reverse(players);
    }

    public void drawN(int numCards, int playerIndex) {
        for (int i = 0; i < numCards; i++) {
            players.get((playerIndex + 1) % players.size()).drawCard(deck);
        }
        skip();
    }

    public Card drawOne(){
        Card card = deck.draw();
        currentPlayer.addCard(card);
        return card;
    }

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
        }
        return false;
    }

    public int getCurrentPlayerIndex(){
        return playerIndex;
    }

    public void createPlayers(int n) {
        this.players = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Player player = new Player();
            player.setName(Integer.toString(i+1));
            players.add(player);
        }
    }

    public void dealCards() {
        for (int i = 0; i < 7; i++) {
            for (Player player : players) {
                player.drawCard(deck);
            }
        }
    }

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

        if (startingCard.getColour() == Card.Colour.WILD) {
            return true;
        }

        return false;
    }

    public boolean hasDrawn(){
        return currentPlayer.getHasDrawn();
    }

    public void nextPlayer() {
        currentPlayer.setCanPlay(true);
        currentPlayer.setHasDrawn(false);
        playerIndex++;
        if (playerIndex == players.size()) {
            playerIndex = 0;
        }
        currentPlayer = players.get(playerIndex);
    }

    public void addView(UnoViewHandler view) {
        views.add(view);
    }

    public void removeView(UnoViewHandler view) {
        views.remove(view);
    }

}
