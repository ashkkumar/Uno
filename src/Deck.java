import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class  Deck {

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
                    numDeckCards++;
                }

            }
        }
    }


    public Card draw(){
        Collections.shuffle(deck);
        Card card = deck.remove(0);
        numDeckCards--;
        return card;
    }

    public boolean isEmpty(){
        if (numDeckCards == 0){
            return true;
        }
        return false;
    }

    public int getNumDeckCards() {
        return numDeckCards;
    }

    public Card getCard(int i){
        return deck.get(i);
    }

    public ArrayList<Card> getCards(){
        return deck;
    }
}
