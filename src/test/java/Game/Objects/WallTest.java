package Game.Objects;

import Game.Objects.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class WallTest {

    @Test
    public void testGetImageName() {
        Wall wall = new Wall();
        assertEquals("wall", wall.getImageName());
    }

    @Test
    public void testBorderWallConstructor() {
        Wall wall = new Wall(true);
        assertTrue(wall.borderWall);
    }
}

