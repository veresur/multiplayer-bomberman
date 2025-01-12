package Game.Objects;

import Game.Map;
import Game.Objects.Enemy;
import Utils.Coordinate;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;
import static org.junit.Assert.*;

public class EnemyTest {
    private Enemy enemy;
    private Map map;

    @Before
    public void setUp() {
        this.map = new Map(1,2,3);
        this.enemy = new Enemy(5, 5, map);
    }

    @Test
    public void testGetImageName() {
        assertEquals("enemy", enemy.getImageName());
    }
}
