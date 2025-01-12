package Game.Objects;

import Game.Objects.Box;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoxTest {

    @Test
    public void testGetImageName() {
        Box box = new Box();
        assertEquals("box", box.getImageName());
    }
}
