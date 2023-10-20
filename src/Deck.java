import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Card> deck;

    private int numDeckCards;


    public Deck(ArrayList<Card> cards){
        this.deck = cards;
        numDeckCards = cards.size();
    }

    public Deck(){
        deck = new ArrayList<Card>();
    }

    public makeCards(int n, ){

        for (int i = 0;;i++){
            for (int j = 0;;++){
                Card card = new Card(Card.cardType[i], )
            }
        }
    }

    public Card draw(){
        Random rand = new Random();
        int i = rand.nextInt(numDeckCards);
        deck.remove(i);
    }

    public boolean isEmpty(){
        if (numDeckCards == 0){
            return true;
        }
        return false;
    }
}
