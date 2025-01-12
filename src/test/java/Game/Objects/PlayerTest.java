package Game.Objects;

import Game.Map;
import Game.Objects.*;
import Game.PowerUp.PowerUpProps;
import Game.PowerUp.PowerUps.*;
import Utils.Coordinate;
import Utils.DirectionVector;
import Utils.Enumerated.Input;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class PlayerTest {

    private Player player;
    private Map map;

    @Before
    public void setUp() {
        this.map = new Map(1,2,3);
        this.player = new Player(5, 5, 1, map);
    }

    @Test
    public void testHandleInput_UP() {
        player.handleInput(Input.UP);
        assertEquals(player.getDirection(), DirectionVector.UP);
    }

    @Test
    public void testHandleInput_DOWN() {
        player.handleInput(Input.DOWN);
        assertEquals(player.getDirection(), DirectionVector.DOWN);
    }

    @Test
    public void testHandleInput_LEFT() {
        player.handleInput(Input.LEFT);
        assertEquals(player.getDirection(), DirectionVector.LEFT);
    }

    @Test
    public void testHandleInput_RIGHT() {
        player.handleInput(Input.RIGHT);
        assertEquals(player.getDirection(), DirectionVector.RIGHT);
    }

    @Test
    public void testHandleInput_STOP() {
        player.handleInput(Input.STOP);
        assertEquals(player.getDirection(), DirectionVector.STOP);
    }

    @Test
    public void testKillYourSelf() {
        player.killYourSelf();
        assertFalse(player.getIsAlive());
    }

    @Test
    public void testRespawn() {
        player.killYourSelf();
        player.respawn();
        assertTrue(player.getIsAlive());
    }
}

