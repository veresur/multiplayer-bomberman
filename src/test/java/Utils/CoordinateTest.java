package test.Utils;

import Utils.Coordinate;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CoordinateTest {

    @Test
    public void testAdd() {
        Coordinate a = new Coordinate(3, 4);
        Coordinate b = new Coordinate(2, 1);
        Coordinate sum = a.add(b);
        assertEquals(5, sum.X);
        assertEquals(5, sum.Y);
    }

    @Test
    public void testTranslate() {
        Coordinate a = new Coordinate(3, 4);
        Coordinate translation = new Coordinate(2, 1);
        a.translate(translation);
        assertEquals(5, a.X);
        assertEquals(5, a.Y);
    }

    @Test
    public void testEquals() {
        Coordinate a = new Coordinate(3, 4);
        Coordinate b = new Coordinate(3, 4);
        assertEquals(a, b);

        Coordinate c = new Coordinate(2, 1);
        assertNotEquals(a, c);
    }

    @Test
    public void testDistance() {
        Coordinate a = new Coordinate(1, 1);
        Coordinate b = new Coordinate(4, 5);
        assertEquals(5, a.distance(b));
    }

    @Test
    public void testToString() {
        Coordinate a = new Coordinate(3, 4);
        assertEquals("x: 3 y: 4", a.toString());
    }
}

