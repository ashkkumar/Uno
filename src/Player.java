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

    public void removeCard(Card card){
        myCards.remove(card);
        numCards--;
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
        for (int i = 0; i < numCards; i++){
            System.out.println(i + "." + myCards.get(i));
        }
    }

    public void drawCard(){

    }
}
