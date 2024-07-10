package dramaplays.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayTest {

    @Test
    public void testConstructor() {
        Play play = new Play("Hamlet", "Tragedy");
        assertEquals("Hamlet", play.name);
        assertEquals("Tragedy", play.type);
    }

    @Test
    public void testGetName() {
        Play play = new Play("Romeo and Juliet", "Tragedy");
        assertEquals("Romeo and Juliet", play.name);
    }

    @Test
    public void testGetType() {
        Play play = new Play("Macbeth", "Tragedy");
        assertEquals("Tragedy", play.type);
    }

    @Test
    public void testSetName() {
        Play play = new Play("Othello", "Tragedy");
        play.name = "King Lear";
        assertEquals("King Lear", play.name);
    }

    @Test
    public void testSetType() {
        Play play = new Play("The Tempest", "Comedy");
        play.type = "Tragedy";
        assertEquals("Tragedy", play.type);
    }

    @Test
    public void testEquals() {
        Play play1 = new Play("Hamlet", "Tragedy");
        Play play2 = new Play("Hamlet", "Tragedy");
        assertNotEquals(play1, play2); // Since play1 and play2 are two different instances of the Play class, their references are not equals.
    }

    @Test
    public void testNotEquals() {
        Play play1 = new Play("Hamlet", "Tragedy");
        Play play2 = new Play("Macbeth", "Tragedy");
        assertNotEquals(play1, play2);
    }
}
