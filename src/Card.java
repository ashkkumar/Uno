public class Card {
    public enum CardType {ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, REVERSE, DRAWONE, SKIP, WILD}
    public enum Colour {RED, BLUE, GREEN, YELLOW, WILD}

    private final CardType TYPE;
    private final Colour COLOUR;
    /**
     * Create a card given a string shordhand formatted as:
     * "TD" should create a TEN of DIAMONDS, etc.
     */

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
