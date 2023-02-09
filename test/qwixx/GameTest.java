package qwixx;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private Game g;

    @BeforeEach
    void setUp(){
        g = new Game();
        g.players.put("p1", new DisplayCard("p1"));
        g.players.put("p2", new DisplayCard("p2"));
        g.players.put("p3", new DisplayCard("p3"));
        g.iterator = g.players.keySet().iterator();
    }

    @Test
    void constructorSetsFirstActivePlayer() {
        g.setActivePlayer();
        assertEquals("p1", g.activePlayer);
    }

    @Test
    void switchesActivePlayer() {
        g.setActivePlayer();
        g.setActivePlayer();
        assertEquals("p2", g.activePlayer);
    }

    @Test
    void loopsBackToFirstPlayer() {
        g.setActivePlayer(); //1
        g.setActivePlayer(); //2
        g.setActivePlayer(); //3
        g.setActivePlayer(); //1
        g.setActivePlayer(); //2
        g.setActivePlayer(); //3
        assertEquals("p3", g.activePlayer);
    }

    @Test
    void locksRowsForAllPlayers() {
        g.players.get("p1").checkBox("red", 2);
        g.players.get("p1").checkBox("red", 3);
        g.players.get("p1").checkBox("red", 4);
        g.players.get("p1").checkBox("red", 5);
        g.players.get("p1").checkBox("red", 6);
        g.players.get("p1").checkBox("red", 12);
        g.lockRow("red");
        g.displayCards();
    }

    @Test
    void onlyLocksIfFiveChecked() {
        assertFalse(g.players.get("p1").rowIsLockable("red"));
        g.players.get("p1").checkBox("red", 2);
        g.players.get("p1").checkBox("red", 3);
        g.players.get("p1").checkBox("red", 4);
        g.players.get("p1").checkBox("red", 5);
        g.players.get("p1").checkBox("red", 6);
        assertTrue(g.players.get("p1").rowIsLockable("red"));
    }
}
