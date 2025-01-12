package Game.PowerUp;

import Game.PowerUp.*;
import Game.PowerUp.PowerUps.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class PowerUpTest {

    @Test
    public void testReset() {
        PowerUp powerUp = new BonusBomb();
        powerUp.lifeLength = 0;

        powerUp.reset();

        assertEquals(100, powerUp.lifeLength);
    }

    @Test
    public void testGetRandomPowerup() {
        PowerUp powerUp = PowerUp.getRandomPowerup();
        assertNotNull(powerUp);
    }
}
