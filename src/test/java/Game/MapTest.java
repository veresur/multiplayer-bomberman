package Game;

import Game.*;
import Game.Objects.*;
import Game.PowerUp.PowerUp;
import Game.PowerUp.PowerUps.*;
import Utils.Coordinate;
import Utils.Deserializer.EnemyDeserializer;
import Utils.Deserializer.GameObjectDeserializer;
import Utils.Deserializer.PlayerDeserializer;
import Utils.Deserializer.PowerUpDeserializer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class MapTest {
    private Map map;

    @Before
    public void setUp() {
        map = new Map(1, 2, 3);
    }

    @Test
    public void testGetBombsCountByPlayer() {
        Player player = new Player(1, 1, 1, map);
        Bomb bomb1 = new Bomb(player, map);
        Bomb bomb2 = new Bomb(player, map);
        Bomb bomb3 = new Bomb(player, map);

        map.placeGameObject(bomb1, new Coordinate(2, 2));
        map.placeGameObject(bomb2, new Coordinate(3, 3));
        map.placeGameObject(bomb3, new Coordinate(4, 4));

        assertEquals(3, map.GetBombsCountByPlayer(player));
    }

    @Test
    public void testGetGameObjectPosition() {
        Bomb bomb = new Bomb(new Player(1, 1, 1, map), map);
        map.placeGameObject(bomb, new Coordinate(3, 3));

        assertEquals(new Coordinate(3, 3), map.GetGameObjectPosition(bomb));
    }

}
