package qwixx;

//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DisplayCardTest {

    private DisplayCard card;
    @BeforeEach
    void setUp() {
         card = new DisplayCard("test");
    }

    @Test
    void establishesName() {
        assertEquals(card.name, "test");
    }

    @Test
    void eachDisplayHasFourColorTracks() {
        assertEquals(card.trackMap.keySet().toString(), "[red, Yellow, green, blue]");
    }

    @Test
    void boxesAvailableAfterInitilization() {
        for(String color: card.trackMap.keySet()){
            for(Object box: card.trackMap.get(color)) {
                assertEquals(box, DisplayCard.BoxValues.AVAILABLE);
            }
        }
    }

    @Test
    void checkBoxFunctions() {
        card.checkBox("red", 7);
        assertEquals(card.trackMap.get("red")[5], DisplayCard.BoxValues.CHECKED);
    }

    @Test
    void hideUnavailableFunctions() {
        card.checkBox("red", 7);
        assertEquals(card.trackMap.get("red")[4], DisplayCard.BoxValues.UNAVAILABLE);
        assertEquals(card.trackMap.get("red")[3], DisplayCard.BoxValues.UNAVAILABLE);
        assertEquals(card.trackMap.get("red")[8], DisplayCard.BoxValues.AVAILABLE);

        card.checkBox("green", 6);
        assertEquals(card.trackMap.get("green")[4], DisplayCard.BoxValues.UNAVAILABLE);
        assertEquals(card.trackMap.get("green")[3], DisplayCard.BoxValues.UNAVAILABLE);
        assertEquals(card.trackMap.get("green")[8], DisplayCard.BoxValues.AVAILABLE);
    }

    @Test
    void checkLeftToRight() {
        card.checkBox("red", 6);
        card.checkBox("red", 3);
        assertNotEquals(card.trackMap.get("red")[3], DisplayCard.BoxValues.CHECKED);
    }

    @Test
    void fiveChecksIsLockable() {
        card.checkBox("Red", 2);
        card.checkBox("Red", 3);
        card.checkBox("Red", 4);
        card.checkBox("Red", 5);
        card.checkBox("Red", 6);

        card.displayPlayerCard();
        assertEquals(true, card.rowIsLockable("Red"));

    }

    @Test
    void checkMarkPenalty() {
        card.markPenalty();
        card.markPenalty();

        assertEquals(card.penalties[0], 'X');
        assertEquals(card.penalties[1], 'X');
        assertEquals(card.penalties[2], '_');
        assertEquals(card.penalties[3], '_');
        }


}
