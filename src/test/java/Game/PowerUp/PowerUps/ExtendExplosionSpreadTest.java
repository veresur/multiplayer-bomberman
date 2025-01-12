package Game.PowerUp.PowerUps;

import Game.PowerUp.PowerUps.ExtendExplosionSpread;
import Game.PowerUp.PowerUpProps;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ExtendExplosionSpreadTest {

    @Test
    public void testEffect() {
        ExtendExplosionSpread extendExplosionSpread = new ExtendExplosionSpread();
        PowerUpProps powerUpProps = new PowerUpProps();

        extendExplosionSpread.effect(powerUpProps);

        assertTrue(powerUpProps.extendedExplosionSpread);
    }

    @Test
    public void testIsStackable() {
        ExtendExplosionSpread extendExplosionSpread = new ExtendExplosionSpread();
        assertFalse(extendExplosionSpread.IsStackAble());
    }

    @Test
    public void testGetImageName() {
        ExtendExplosionSpread extendExplosionSpread = new ExtendExplosionSpread();
        assertEquals("biggerExplosion", extendExplosionSpread.getImageName());
    }
}

