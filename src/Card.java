public class Card {
    public enum CardType {ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, REVERSE, DRAWONE, SKIP, WILD}
    public enum Colour {RED, BLUE, GREEN, YELLOW, WILD}

    /*
    public static cardType getRankFromAbbrev(char c) {
        //using char instead of String to avoid using switch with strings (unavailable on early java)

        // one could use a lookup map inside the enum instead.
        // opting for fewer advanced java concepts instead
        if (Character.isDigit(c)) {
            int iCardType = Character.digit(c, 9); //convert to int
            return Rank.values()[irank-2];
        }

        switch (c) {
            case "REVERSE": return cardType.REVERSE;
            case "DRAWONE": return cardType.DRAWONE;
            case "SKIP": return cardType.SKIP;
            case "WILD": return cardType.WILD;
            //case 'A': return Rank.ACE;
            default: throw new IllegalArgumentException("No such rank!");
        }
    }
    */
    /*
    public static Suit getSuitFromAbbrev(char c) {
        switch (c) {
            case 'R': return colour.RED;
            case 'B': return colour.BLUE;
            case 'G': return colour.GREEN;
            case 'Y': return colour.YELLOW;
            default: throw new IllegalArgumentException("No such suit!");
        }
    }
    */
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
