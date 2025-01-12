package Game.PowerUp.PowerUps;

import Game.PowerUp.PowerUps.GhostMode;
import Game.PowerUp.PowerUpProps;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GhostModeTest {

    @Test
    public void testEffect() {
        GhostMode ghostMode = new GhostMode();
        PowerUpProps powerUpProps = new PowerUpProps();

        ghostMode.effect(powerUpProps);

        assertTrue(powerUpProps.ghost);
    }

    @Test
    public void testIsStackable() {
        GhostMode ghostMode = new GhostMode();
        assertFalse(ghostMode.IsStackAble());
    }

    @Test
    public void testGetImageName() {
        GhostMode ghostMode = new GhostMode();
        assertEquals("ghost", ghostMode.getImageName());
    }
}
