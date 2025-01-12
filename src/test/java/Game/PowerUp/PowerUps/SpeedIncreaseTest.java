package Game.PowerUp.PowerUps;

import Game.PowerUp.PowerUps.SpeedIncrease;
import Game.PowerUp.PowerUpProps;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SpeedIncreaseTest {

    @Test
    public void testEffect() {
        SpeedIncrease speedIncrease = new SpeedIncrease();
        PowerUpProps powerUpProps = new PowerUpProps();

        speedIncrease.effect(powerUpProps);

        assertTrue(powerUpProps.speedIncrease);
    }

    @Test
    public void testIsStackable() {
        SpeedIncrease speedIncrease = new SpeedIncrease();
        assertFalse(speedIncrease.IsStackAble());
    }

    @Test
    public void testGetImageName() {
        SpeedIncrease speedIncrease = new SpeedIncrease();
        assertEquals("speed", speedIncrease.getImageName());
    }
}
