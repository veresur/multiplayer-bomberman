package Utils.Deserializer;

import org.junit.Test;
import static org.junit.Assert.*;

public class MapDeserializerTest {

    @Test
    public void testSetEnemies() {
        EnemyDeserializer[] enemies = new EnemyDeserializer[3];

        MapDeserializer map = new MapDeserializer();
        map.setEnemies(enemies);

        assertArrayEquals(enemies, map.enemies);
    }

    @Test
    public void testSetPlayers() {
        PlayerDeserializer[] players = new PlayerDeserializer[2];

        MapDeserializer map = new MapDeserializer();
        map.setPlayers(players);

        assertArrayEquals(players, map.players);
    }

    @Test
    public void testSetObjects() {
        GameObjectDeserializer[] objects = new GameObjectDeserializer[5];

        MapDeserializer map = new MapDeserializer();
        map.setObjects(objects);

        assertArrayEquals(objects, map.objects);
    }

    @Test
    public void testSetMapOption() {
        int mapOption = 1;

        MapDeserializer map = new MapDeserializer();
        map.setMapOption(mapOption);

        assertEquals(mapOption, map.mapOption);
    }

    @Test
    public void testSetCurrentRound() {
        int currentRound = 3;

        MapDeserializer map = new MapDeserializer();
        map.setCurrentRound(currentRound);

        assertEquals(currentRound, map.currentRound);
    }

    @Test
    public void testSetRoundCount() {
        int roundCount = 5;

        MapDeserializer map = new MapDeserializer();
        map.setRoundCount(roundCount);

        assertEquals(roundCount, map.roundCount);
    }

    @Test
    public void testSetPlayerCount() {
        int playerCount = 4;

        MapDeserializer map = new MapDeserializer();
        map.setPlayerCount(playerCount);

        assertEquals(playerCount, map.playerCount);
    }

}

