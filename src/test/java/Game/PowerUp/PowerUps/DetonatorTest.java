package Game.PowerUp.PowerUps;

import Game.PowerUp.PowerUps.Detonator;
import Game.PowerUp.PowerUpProps;
import org.junit.Test;

import static org.junit.Assert.*;

public class DetonatorTest {

    @Test
    public void testEffect() {
        Detonator detonator = new Detonator();
        PowerUpProps powerUpProps = new PowerUpProps();

        detonator.effect(powerUpProps);

        assertTrue(powerUpProps.detonator);
    }

    @Test
    public void testIsStackable() {
        Detonator detonator = new Detonator();
        assertFalse(detonator.IsStackAble());
    }

    @Test
    public void testGetImageName() {
        Detonator detonator = new Detonator();
        assertEquals("detonator", detonator.getImageName());
    }
}
