package Utils.Deserializer;

import org.junit.Test;
import static org.junit.Assert.*;

public class CoordinateDeserializerTest {

    @Test
    public void testDefaultConstructor() {
        CoordinateDeserializer coordinate = new CoordinateDeserializer();
        assertEquals(0, coordinate.X);
        assertEquals(0, coordinate.Y);
    }

    @Test
    public void testSetXNegativeValue() {
        CoordinateDeserializer coordinate = new CoordinateDeserializer();
        coordinate.setX(-5);
        assertEquals(-5, coordinate.X);
    }

    @Test
    public void testSetYNegativeValue() {
        CoordinateDeserializer coordinate = new CoordinateDeserializer();
        coordinate.setY(-10);
        assertEquals(-10, coordinate.Y);
    }

    @Test
    public void testSetXAndY() {
        CoordinateDeserializer coordinate = new CoordinateDeserializer();
        coordinate.setX(5);
        coordinate.setY(10);
        assertEquals(5, coordinate.X);
        assertEquals(10, coordinate.Y);
    }
}
