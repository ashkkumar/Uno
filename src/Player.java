import java.util.ArrayList;

public class Player {

    private String name;

    private ArrayList<Card> myCards;

    private int score;

    private int numCards;

    public Player(){

        myCards = new ArrayList<Card>();
        numCards = 0;
    }

    public void addCard(Card card){
        myCards.add(card);
        numCards++;
    }

    public Card playCard(int i){
        Card card = myCards.get(i);
        myCards.remove(i);
        numCards--;
        return card;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getNumCards() {
        return numCards;
    }

    public void getMyCards() {
        System.out.println("Your Cards:");
        for (int i = 0; i < myCards.size(); i++){
            System.out.println(i+1 + ". " + myCards.get(i).toString());
        }
    }

    public void drawCard(Deck deck){
        myCards.add(deck.draw());
        numCards++;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
