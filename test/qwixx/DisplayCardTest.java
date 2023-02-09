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
        assertEquals(card.trackMap.keySet().toString(), "[Red, Yellow, Green, Blue]");
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
        card.checkBox("Red", 7);
        card.displayPlayerCard();
        assertEquals(card.trackMap.get("Red")[5], DisplayCard.BoxValues.CHECKED);
    }

    @Test
    void hideUnavailableFunctions() {
        card.checkBox("Red", 7);
        assertEquals(card.trackMap.get("Red")[4], DisplayCard.BoxValues.UNAVAILABLE);
        assertEquals(card.trackMap.get("Red")[3], DisplayCard.BoxValues.UNAVAILABLE);
        assertEquals(card.trackMap.get("Red")[8], DisplayCard.BoxValues.AVAILABLE);

        card.checkBox("Green", 6);
        assertEquals(card.trackMap.get("Green")[4], DisplayCard.BoxValues.UNAVAILABLE);
        assertEquals(card.trackMap.get("Green")[3], DisplayCard.BoxValues.UNAVAILABLE);
        assertEquals(card.trackMap.get("Green")[8], DisplayCard.BoxValues.AVAILABLE);
    }

    @Test
    void checkLeftToRight() {
        card.checkBox("Red", 6);
        card.checkBox("Red", 3);
        assertNotEquals(card.trackMap.get("Red")[3], DisplayCard.BoxValues.CHECKED);
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
