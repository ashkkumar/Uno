public class Card {
    public enum CardType {ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, REVERSE, DRAWONE, SKIP, WILD}
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

}
