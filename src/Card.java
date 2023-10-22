public class Card {
    public enum CardType
    {
        ONE(1),
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        REVERSE(20),
        DRAWONE(10),
        SKIP(20),
        WILD(40);

        int cardScore;

        CardType (int cardScore){
            this.cardScore = cardScore;
        }

    }
    public enum Colour {RED, BLUE, GREEN, YELLOW, WILD}

    private final CardType TYPE;
    private final Colour COLOUR;

    public Card(CardType t, Colour c) {
        this.TYPE = t;
        this.COLOUR = c;
    }

    public CardType getCardType() {

        return this.TYPE;
    }

    public Colour getColour() {

        return this.COLOUR;
    }

    @Override
    public String toString() {
        return " " + COLOUR + " " + TYPE;
    }
}
