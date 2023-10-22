import java.util.ArrayList;

public class Player {

    private String name;

    private ArrayList<Card> myCards;

    private ArrayList<Card> cardsPlayed;

    private int score;

    private int numCards;

    public Player(){

        myCards = new ArrayList<Card>();
        numCards = 0;
        score = 0;
    }

    public void addCard(Card card){
        myCards.add(card);
        numCards++;
    }

    public Card playCard(int index){
        Card card = myCards.get(index);
        return card;
    }

    public void addCardsPlayed(Card cardPlayed){
        if(cardsPlayed == null){
            return;
        }
        cardsPlayed.add(cardPlayed);
    }

    public void addToScore() {
        if(cardsPlayed == null){
            return;
        }
        for( Card card: cardsPlayed){
            Card.CardType cardType = card.getCardType();
            score += cardType.cardScore;
        }
    }

    public int getScore() {
        return score;
    }

    public int getNumCards() {
        return numCards;
    }

    public void displayCards() {
        System.out.println("Your Cards:");
        for (int i = 0; i < myCards.size(); i++){
            System.out.println(i+1 + "." + myCards.get(i).toString());
        }
    }

    public void drawCard(Deck deck){
        myCards.add(deck.draw());
        numCards++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public ArrayList<Card> getMyCards() {
        return myCards;
    }

    public void removeCard(int n){
        myCards.remove(n);
        numCards--;
    }
}

