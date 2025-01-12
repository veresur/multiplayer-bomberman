package Game.PowerUp;

import Game.PowerUp.PowerUpProps;
import org.junit.Test;

import static org.junit.Assert.*;

public class PowerUpPropsTest {

    @Test
    public void testSetSpeedIncrease() {
        PowerUpProps props = new PowerUpProps();
        props.setSpeedIncrease(true);
        assertTrue(props.speedIncrease);
    }

    @Test
    public void testSetBarricades() {
        PowerUpProps props = new PowerUpProps();
        props.setBarricades(2);
        assertEquals(2, props.barricades);
    }

    @Test
    public void testSetMaxBombCount() {
        PowerUpProps props = new PowerUpProps();
        props.setMaxBombCount(3);
        assertEquals(3, props.maxBombCount);
    }

    @Test
    public void testSetExtendedExplosionSpread() {
        PowerUpProps props = new PowerUpProps();
        props.setExtendedExplosionSpread(true);
        assertTrue(props.extendedExplosionSpread);
    }

    @Test
    public void testSetGod() {
        PowerUpProps props = new PowerUpProps();
        props.setGod(true);
        assertTrue(props.god);
    }

    @Test
    public void testSetGhost() {
        PowerUpProps props = new PowerUpProps();
        props.setGhost(true);
        assertTrue(props.ghost);
    }

    @Test
    public void testSetDetonator() {
        PowerUpProps props = new PowerUpProps();
        props.setDetonator(true);
        assertTrue(props.detonator);
    }
}
