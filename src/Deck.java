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

    public void makeCards(){

        for (Card.colour colour: Card.colour.values()){
            for (Card.cardType type: Card.cardType.values()){
                Card card = new Card(type, colour);
                deck.add(card);
                numDeckCards++;
            }
        }
    }

    public Card draw(){
        Random rand = new Random();
        int i = rand.nextInt(numDeckCards);
        Card card = deck.remove(i);
        numDeckCards--;
        return card;
    }

    public boolean isEmpty(){
        if (numDeckCards == 0){
            return true;
        }
        return false;
    }
}
