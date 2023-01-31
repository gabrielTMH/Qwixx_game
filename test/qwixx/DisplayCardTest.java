package qwixx;

//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class DisplayCardTest {

    private DisplayCard card;
    @BeforeEach
    void setUp() {
         card=new DisplayCard("test");
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
    void emptyDisplayDuringInitialization() {
        //each int array should be full of 0s when initialized
        for(String color: card.trackMap.keySet()){
            for(int box: card.trackMap.get(color)) {
                assertEquals(box, 0);
            }
        }
    }

    @Test
    void checkBoxFunctions() {
        card.checkBox("Red", 7);
        assertEquals(card.trackMap.get("Red")[5], 1);
    }

    @Test
    void hideUnavailableFunctions() {
        card.checkBox("Red", 7);
        assertEquals(card.trackMap.get("Red")[4], -1);
        assertEquals(card.trackMap.get("Red")[3], -1);
        assertEquals(card.trackMap.get("Red")[8], 0);

        card.checkBox("Green", 6);
        assertEquals(card.trackMap.get("Green")[4], -1);
        assertEquals(card.trackMap.get("Green")[3], -1);
        assertEquals(card.trackMap.get("Green")[8], 0);
    }

}
