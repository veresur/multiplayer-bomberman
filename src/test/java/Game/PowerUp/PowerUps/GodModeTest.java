package Game.PowerUp.PowerUps;

import Game.PowerUp.PowerUps.GodMode;
import Game.PowerUp.PowerUpProps;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GodModeTest {

    @Test
    public void testEffect() {
        GodMode godMode = new GodMode();
        PowerUpProps powerUpProps = new PowerUpProps();

        godMode.effect(powerUpProps);

        assertTrue(powerUpProps.god);
    }

    @Test
    public void testIsStackable() {
        GodMode godMode = new GodMode();
        assertFalse(godMode.IsStackAble());
    }

    @Test
    public void testGetImageName() {
        GodMode godMode = new GodMode();
        assertEquals("god", godMode.getImageName());
    }
}

