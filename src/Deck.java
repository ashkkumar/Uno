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
        makeCards();
    }

    public void makeCards(){

        for (Card.Colour Colour: Card.Colour.values()){
            if (!Colour.equals(Card.Colour.WILD)) {
                for (Card.CardType Type : Card.CardType.values()) {
                    if (!Type.equals(Card.CardType.WILD)) {
                        deck.add(new Card(Type, Colour));
                        deck.add(new Card(Type, Colour));
                        numDeckCards += 2;
                    }
                }
            } else {
                for (int i = 4; i > 0; i--){
                    deck.add(new Card(Card.CardType.WILD, Card.Colour.WILD));
                }

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
