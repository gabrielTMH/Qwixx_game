package qwixx;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DieTest {

    private Die d;

    @BeforeEach
    void setUp(){
        d = new Die();
    }

    @Test
    void rolledNumberIsBetween1and6() {
        d.roll();
        assertTrue(d.getValue() >= 1);
        assertTrue(d.getValue() <= 6);
    }

    @Test
    void all6NumbersEquallyLikely(){
        int[] counts = new int[7];
        for (int i = 0; i < 600; i++) {
            d.roll();
            counts[d.getValue()]++;
        }
        for (int i = 1; i <= 6; i++) {
            assertTrue(counts[i]>50);
            assertTrue(counts[i]<150);

        }

    }
}