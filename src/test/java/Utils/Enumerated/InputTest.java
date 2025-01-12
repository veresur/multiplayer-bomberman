package test.Utils.Enumerated;

import Utils.Enumerated.Input;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InputTest {

    @Test
    public void testIsDirection() {
        assertTrue(Input.isDirection(Input.UP));
        assertTrue(Input.isDirection(Input.DOWN));
        assertTrue(Input.isDirection(Input.LEFT));
        assertTrue(Input.isDirection(Input.RIGHT));
        assertFalse(Input.isDirection(Input.BOMB));
        assertFalse(Input.isDirection(Input.BARRICADE));
        assertFalse(Input.isDirection(Input.STOP));
    }
}
