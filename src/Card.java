/**
 * The Card class represents a card in the Uno game.
 */
public class Card {
    /**
     * Enumeration representing the type of the card.
     */
    public enum CardType {ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, REVERSE, DRAWONE, SKIP, WILD}

    /**
     * Enumeration representing the color of the card.
     */
    public enum Colour {RED, BLUE, GREEN, YELLOW, WILD}

    private final CardType TYPE;
    private final Colour COLOUR;

    /**
     * Constructs a Card object with the specified card type and color.
     *
     * @param t The type of the card.
     * @param c The color of the card.
     */
    public Card(CardType t, Colour c) {
        this.TYPE = t;
        this.COLOUR = c;
    }

    /**
     * Get the type of the card.
     *
     * @return The type of the card.
     */
    public CardType getCardType() {

        return this.TYPE;
    }

    /**
     * Get the color of the card.
     *
     * @return The color of the card.
     */
    public Colour getColour() {

        return this.COLOUR;
    }

}
