public class Card {
    private String imageFilePath;

    private String darkFilePath;

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
        FLIP(20),
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

    public enum DarkColour {PINK,PURPLE,TEAL,ORANGE, WILD}

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
    public void setDarkFilePath(){
        String colour;
        if (this.COLOUR == Colour.RED){
            colour = "PINK";
            darkFilePath = darkColour(colour);
        } else if (this.COLOUR == Colour.BLUE){
            colour = "PURPLE";
            darkFilePath = darkColour(colour);
        } else if (this.COLOUR == Colour.GREEN){
            colour = "TEAL";
            darkFilePath = darkColour(colour);
        } else if (this.COLOUR == Colour.YELLOW){
            colour = "ORANGE";
            darkFilePath = darkColour(colour);
        } else if (this.COLOUR == Colour.WILD){
            colour = "WILD";
            darkFilePath = darkColour(colour);
        }
    }

    public String darkColour(String dark){
        if (this.TYPE.equals(CardType.DRAW_ONE)){
            return "Dark/" + dark + "_DRAW_FIVE.png";
        } else if (this.TYPE.equals(CardType.SKIP)){
            return "Dark/" + dark + "_SKIP_EVERYONE.png";
        }else if (this.TYPE.equals(CardType.WILD_DRAW_TWO)){
            return "Dark/" + dark + "_WILD_DRAW_COLOUR.png";
        } else {
            return "Dark/" + dark + "_" + this.TYPE + ".png";
        }
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
     * Gets the dark image file path
     *
     * @return the image file path
     */
    public String getDarkFilePath(){
        return this.darkFilePath;
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
