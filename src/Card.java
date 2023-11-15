public class Card {
    private String imageFilePath;

    public enum CardType {
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
        DRAW_ONE(10),
        SKIP(20),
        WILD(40),

        WILD_DRAW_TWO(50);

        int cardScore;

        CardType(int cardScore) {
            this.cardScore = cardScore;
        }

    }

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
     * Sets the image file path of the Uno card.
     *
     */
    public void setImageFilePath(){
        this.imageFilePath = "images/" +this.toString() +".jpg";
    }

    /**
     * Gets the image file path of the Uno card.
     *
     * @return the image file path
     */
    public String getImageFilePath(){
        return this.imageFilePath;
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
        return COLOUR + "_" + TYPE;
    }
}
