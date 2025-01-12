package test.Utils;
import Utils.Coordinate;
import Utils.DirectionVector;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DirectionVectorTest {

    @Test
    public void testDirections() {
        Coordinate left = DirectionVector.LEFT;
        assertEquals(-1, left.X);
        assertEquals(0, left.Y);

        Coordinate up = DirectionVector.UP;
        assertEquals(0, up.X);
        assertEquals(-1, up.Y);

        Coordinate right = DirectionVector.RIGHT;
        assertEquals(1, right.X);
        assertEquals(0, right.Y);

        Coordinate down = DirectionVector.DOWN;
        assertEquals(0, down.X);
        assertEquals(1, down.Y);

        Coordinate stop = DirectionVector.STOP;
        assertEquals(0, stop.X);
        assertEquals(0, stop.Y);
    }

    @Test
    public void testDirectionsArray() {
        Coordinate[] directions = DirectionVector.Directions;
        assertEquals(4, directions.length);

        assertEquals(DirectionVector.LEFT, directions[0]);
        assertEquals(DirectionVector.UP, directions[1]);
        assertEquals(DirectionVector.RIGHT, directions[2]);
        assertEquals(DirectionVector.DOWN, directions[3]);
    }
}
