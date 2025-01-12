package Utils.Deserializer;

import org.junit.Test;
import static org.junit.Assert.*;

public class GameDeserializerTest {
    @Test
    public void testSetMapNull() {
        GameDeserializer game = new GameDeserializer();
        game.setMap(null);

        assertNull(game.map);
    }
}