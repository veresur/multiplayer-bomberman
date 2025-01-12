package Utils.Deserializer;

import Utils.Coordinate;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameObjectDeserializerTest {

    @Test
    public void testSetCoord() {
        Coordinate coord = new Coordinate(5, 10);

        GameObjectDeserializer gameObject = new GameObjectDeserializer();
        gameObject.setCoord(coord);

        assertEquals(coord, gameObject.coord);
    }

    @Test
    public void testSetOwner() {
        String owner = "Player1";

        GameObjectDeserializer gameObject = new GameObjectDeserializer();
        gameObject.setOwner(owner);

        assertEquals(owner, gameObject.owner);
    }

    @Test
    public void testSetBorderWall() {
        GameObjectDeserializer gameObject = new GameObjectDeserializer();
        gameObject.setBorderWall(true);

        assertTrue(gameObject.borderWall);
    }

    @Test
    public void testSetType() {
        String type = "Bomb";

        GameObjectDeserializer gameObject = new GameObjectDeserializer();
        gameObject.setType(type);

        assertEquals(type, gameObject.type);
    }

    @Test
    public void testSetName() {
        String name = "Explosion";

        GameObjectDeserializer gameObject = new GameObjectDeserializer();
        gameObject.setName(name);

        assertEquals(name, gameObject.name);
    }
}
