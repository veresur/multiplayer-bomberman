package Utils.Deserializer;

import org.junit.Test;
import static org.junit.Assert.*;

public class EnemyDeserializerTest {

    @Test
    public void testSetCoord() {
        CoordinateDeserializer coord = new CoordinateDeserializer();
        coord.setX(5);
        coord.setY(10);

        EnemyDeserializer enemy = new EnemyDeserializer();
        enemy.setCoord(coord);

        assertEquals(coord, enemy.coord);
    }

    @Test
    public void testSetDirection() {
        CoordinateDeserializer direction = new CoordinateDeserializer();
        direction.setX(1);
        direction.setY(-1);

        EnemyDeserializer enemy = new EnemyDeserializer();
        enemy.setDirection(direction);

        assertEquals(direction, enemy.direction);
    }

    @Test
    public void testSetCoordNull() {
        EnemyDeserializer enemy = new EnemyDeserializer();
        enemy.setCoord(null);

        assertNull(enemy.coord);
    }

    @Test
    public void testSetDirectionNull() {
        EnemyDeserializer enemy = new EnemyDeserializer();
        enemy.setDirection(null);

        assertNull(enemy.direction);
    }

    @Test
    public void testSetCoordAndDirection() {
        CoordinateDeserializer coord = new CoordinateDeserializer();
        coord.setX(5);
        coord.setY(10);

        CoordinateDeserializer direction = new CoordinateDeserializer();
        direction.setX(1);
        direction.setY(-1);

        EnemyDeserializer enemy = new EnemyDeserializer();
        enemy.setCoord(coord);
        enemy.setDirection(direction);

        assertEquals(coord, enemy.coord);
        assertEquals(direction, enemy.direction);
    }

    @Test
    public void testSetCoordAndDirectionEquality() {
        CoordinateDeserializer coord = new CoordinateDeserializer();
        coord.setX(5);
        coord.setY(10);

        EnemyDeserializer enemy = new EnemyDeserializer();
        enemy.setCoord(coord);
        enemy.setDirection(coord);

        assertEquals(coord, enemy.coord);
        assertEquals(coord, enemy.direction);
    }
}