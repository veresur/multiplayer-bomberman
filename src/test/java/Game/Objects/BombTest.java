package Game.Objects;

import Game.Map;
import Game.Objects.Bomb;
import Game.Objects.Player;
import Utils.Coordinate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BombTest {

    private Map map;
    private Player player;

    @Before
    public void setUp() {
        map = new Map(1,2,3);
        player = new Player(0, 0, 1, map);
    }

    @Test
    public void testConstructor() {
        Bomb bomb = new Bomb(player, map);
        assertNotNull(bomb);
        assertEquals(player, bomb.GetOwner());
    }
}

