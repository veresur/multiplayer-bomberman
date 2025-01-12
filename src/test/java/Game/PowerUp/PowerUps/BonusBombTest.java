package Game.PowerUp.PowerUps;

import Game.PowerUp.PowerUps.BonusBomb;
import Game.PowerUp.PowerUpProps;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BonusBombTest {

    @Test
    public void testEffect() {
        BonusBomb bonusBomb = new BonusBomb();
        PowerUpProps powerUpProps = new PowerUpProps();

        bonusBomb.effect(powerUpProps);

        assertEquals(2, powerUpProps.maxBombCount);
    }

    @Test
    public void testIsStackable() {
        BonusBomb bonusBomb = new BonusBomb();
        assertTrue(bonusBomb.IsStackAble());
    }

    @Test
    public void testGetImageName() {
        BonusBomb bonusBomb = new BonusBomb();
        assertEquals("addBomb", bonusBomb.getImageName());
    }
}
