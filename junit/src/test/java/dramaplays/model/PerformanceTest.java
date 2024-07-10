package dramaplays.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PerformanceTest {

    @Test
    public void testConstructor() {
        Performance performance = new Performance("1", 100);
        assertEquals("1", performance.playID);
        assertEquals(100, performance.audience);
    }

    @Test
    public void testGetPlayID() {
        Performance performance = new Performance("2", 150);
        assertEquals("2", performance.playID);
    }

    @Test
    public void testGetAudience() {
        Performance performance = new Performance("3", 200);
        assertEquals(200, performance.audience);
    }

    @Test
    public void testSetPlayID() {
        Performance performance = new Performance("4", 250);
        performance.playID = "5";
        assertEquals("5", performance.playID);
    }

    @Test
    public void testSetAudience() {
        Performance performance = new Performance("6", 300);
        performance.audience = 400;
        assertEquals(400, performance.audience);
    }

    @Test
    public void testEquals() {
        Performance performance1 = new Performance("7", 350);
        Performance performance2 = new Performance("7", 350);
        assertNotEquals(performance1, performance2); // Since play1 and play2 are two different instances of the Play class, their references are not equal.

    }

    @Test
    public void testNotEquals() {
        Performance performance1 = new Performance("8", 400);
        Performance performance2 = new Performance("9", 450);
        assertNotEquals(performance1, performance2);
    }
}
