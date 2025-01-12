package Utils.Deserializer;

import Game.PowerUp.PowerUpProps;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerDeserializerTest {

    @Test
    public void testSetCoord() {
        CoordinateDeserializer coord = new CoordinateDeserializer();
        coord.setX(5);
        coord.setY(10);

        PlayerDeserializer player = new PlayerDeserializer();
        player.setCoord(coord);

        assertEquals(coord, player.coord);
    }

    @Test
    public void testSetPlayerNumber() {
        int playerNumber = 1;

        PlayerDeserializer player = new PlayerDeserializer();
        player.setPlayerNumber(playerNumber);

        assertEquals(playerNumber, player.playerNumber);
    }

    @Test
    public void testSetWins() {
        int wins = 3;

        PlayerDeserializer player = new PlayerDeserializer();
        player.setWins(wins);

        assertEquals(wins, player.wins);
    }

    @Test
    public void testSetPowerUpList() {
        PowerUpDeserializer[] powerUpList = new PowerUpDeserializer[2];

        PlayerDeserializer player = new PlayerDeserializer();
        player.setPowerUpList(powerUpList);

        assertArrayEquals(powerUpList, player.powerUpList);
    }

    @Test
    public void testSetKey() {
        String key = "A";

        PlayerDeserializer player = new PlayerDeserializer();
        player.setKey(key);

        assertEquals(key, player.key);
    }
}
