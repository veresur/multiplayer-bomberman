package Game.PowerUp.PowerUps;

import Game.PowerUp.PowerUps.BarricadePowerUp;
import Game.PowerUp.PowerUpProps;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BarricadePowerUpTest {

    @Test
    public void testEffect() {
        BarricadePowerUp barricadePowerUp = new BarricadePowerUp();
        PowerUpProps powerUpProps = new PowerUpProps();

        barricadePowerUp.effect(powerUpProps);

        assertEquals(3, powerUpProps.barricades);
    }

    @Test
    public void testIsStackable() {
        BarricadePowerUp barricadePowerUp = new BarricadePowerUp();
        assertTrue(barricadePowerUp.IsStackAble());
    }

    @Test
    public void testGetImageName() {
        BarricadePowerUp barricadePowerUp = new BarricadePowerUp();
        assertEquals("barricadePowerUp", barricadePowerUp.getImageName());
    }
}
