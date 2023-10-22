public class Card {
    public enum CardType {ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, REVERSE, DRAWONE, SKIP, WILD}
    public enum Colour {RED, BLUE, GREEN, YELLOW, WILD}

    private final CardType TYPE;
    private final Colour COLOUR;

    /**
     * Creates a new Uno card with the specified card type and color.
     *
     * @param t The type of the card (e.g., NUMBER, SKIP, REVERSE).
     * @param c The color of the card (e.g., RED, BLUE, GREEN, YELLOW, or WILD).
     */
    public Card(CardType t, Colour c) {
        this.TYPE = t;
        this.COLOUR = c;
    }
    /**
     * Gets the card type of this Uno card.
     *
     * @return The card type.
     */
    public CardType getCardType() {

        return this.TYPE;
    }
    /**
     * Gets the color of the card.
     *
     * @return The card color.
     */
    public Colour getColour() {

        return this.COLOUR;
    }
    /**
     * Returns a string representation of the card, including its color and type.
     *
     * @return A string representation of the card in the format "COLOR TYPE" (e.g., "RED NUMBER").
     */
    @Override
    public String toString() {
        return " " + COLOUR + " " + TYPE;
    }
}
