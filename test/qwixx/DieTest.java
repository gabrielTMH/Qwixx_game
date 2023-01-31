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
    void rollsNumbersBetween1and6() {
        d.rollAll();
        for (Integer value:
             d.dieSet.values()) {
            assertTrue(value >= 1);
            assertTrue(value <= 6);
        }
    }

    @Test
    void all6NumbersEquallyLikely(){
        int[] counts = new int[7];
        for (int i = 0; i < 600; i++) {
            d.rollAll();
            counts[d.dieSet.get("Red")]++;
        }
        for (int i = 1; i <= 6; i++) {
            assertTrue(counts[i]>50);
            assertTrue(counts[i]<150);
        }
    }
}