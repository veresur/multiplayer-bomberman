package Game.Objects;

import Game.Objects.Barricade;
import Game.Objects.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BarricadeTest {

    @Test
    public void testConstructor() {
        Player player = new Player(1, 1, 1, null);
        Barricade barricade = new Barricade(player);

        assertNotNull(barricade);
        assertEquals(player, barricade.getOwner());
    }

    @Test
    public void testGetImageName() {
        Player player = new Player(1, 1, 1, null);
        Barricade barricade = new Barricade(player);

        assertEquals("barricade", barricade.getImageName());
    }
}
