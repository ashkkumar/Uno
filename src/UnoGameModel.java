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

    public UnoGameModel() {
        initializeGame();
        views = new ArrayList<>();
    }

    private void initializeGame() {
        createPlayers(4);
        this.deck = new Deck();
        this.startingCard = deck.draw();
        this.topCard = startingCard;
        this.finished = false;
        currentPlayer = players.get(playerIndex);
        dealCards();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Card getStartingCard(){
        return this.startingCard;
    }

    public void skip() {
        this.playerIndex = (playerIndex + 2) % players.size();
    }

    public void wildCard(Card.Colour chosenColor) {
        // Handle the selection of a new color for a wild card
        //playedCard.setColour(chosenColor);
    }

    public void reverse() {
        playerIndex = ((players.size() - playerIndex) % players.size());
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
        } else if (topCard.getCardType().equals(Card.CardType.WILD)) {
            //UnoViewHandler.notifyAllViews(new UnoGameEvent(this)); // Notify views about the Wild card
            // Handle wildCard logic in the view and update the playedCard accordingly
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

    public int getCurrentPlayerIndex(){
        return playerIndex;
    }

    public void createPlayers(int n) {
        this.players = new ArrayList<>();
        while (n > 0) {
            Player player = new Player();
            players.add(player);
            n--;
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
            currentPlayer.setCanPlay(false);
            return true;
        }
        return false;
    }

    public boolean isValidChoice() {
        if (playedCard == null || topCard == null) {
            return false;
        }

        Card.Colour playedColor = playedCard.getColour();
        Card.CardType playedType = playedCard.getCardType();
        Card.Colour topColor = topCard.getColour();
        Card.CardType topType = topCard.getCardType();

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

    public boolean hasNotDrawn(){
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
