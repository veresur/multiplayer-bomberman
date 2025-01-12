package test.Utils.Enumerated;

import Utils.Enumerated.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {

    @Test
    public void testEnumValues() {
        assertEquals(Player.PLAYER1, Player.valueOf("PLAYER1"));
        assertEquals(Player.PLAYER2, Player.valueOf("PLAYER2"));
        assertEquals(Player.PLAYER3, Player.valueOf("PLAYER3"));
    }

    @Test
    public void testToString() {
        assertEquals("PLAYER1", Player.PLAYER1.toString());
        assertEquals("PLAYER2", Player.PLAYER2.toString());
        assertEquals("PLAYER3", Player.PLAYER3.toString());
    }
}

