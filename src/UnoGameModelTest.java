import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UnoGameModelTest {

    private UnoGameModel unoGameModel;

    @BeforeEach
    void setUp() {
        // Initialize the UnoGameModel before each test
        unoGameModel = new UnoGameModel(4, 2); // Assuming 4 human players and 2 AI players
    }

    @Test
    void getCurrentPlayer() {
        // Test the initial current player
        assertNotNull(unoGameModel.getCurrentPlayer());
    }

    @Test
    void getTopColour() {
        // Test the initial top color
        assertNotNull(unoGameModel.getTopColour());
    }

    @Test
    void getTopType() {
        // Test the initial top type
        assertNotNull(unoGameModel.getTopType());
    }

    @Test
    void getPlayedCard() {
        // Test the initial played card
        assertNull(unoGameModel.getPlayedCard());
    }

    @Test
    void getStartingCard() {
        // Test the initial starting card
        assertNotNull(unoGameModel.getStartingCard());
    }

    @Test
    void skip() {
        // Test the skip method
        unoGameModel.skip();
        assertEquals(1, unoGameModel.getCurrentPlayerIndex());
    }

    @Test
    void wildCard() {
        // Test the wildCard method
        unoGameModel.wildCard(Card.Colour.RED);
        assertEquals(Card.Colour.RED, unoGameModel.getTopColour());
        assertEquals(Card.CardType.WILD, unoGameModel.getTopType());
    }

    @Test
    void isNotDarkSide() {
        // Test the initial dark side status
        assertFalse(unoGameModel.isDarkSide());
    }

    @Test
    void drawOne() {
        // Save the initial number of cards in the player's hand
        int initialHandSize = unoGameModel.getCurrentPlayer().getNumCards();

        // Test the drawOne method
        unoGameModel.drawOne();

        // Check if the player's hand size increased by one
        assertEquals(initialHandSize + 1, unoGameModel.getCurrentPlayer().getNumCards());
    }
}
