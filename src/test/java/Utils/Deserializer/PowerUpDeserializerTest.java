package Utils.Deserializer;

import org.junit.Test;
import static org.junit.Assert.*;

public class PowerUpDeserializerTest {

    @Test
    public void testSetLifeLength() {
        int lifeLength = 5;

        PowerUpDeserializer powerUp = new PowerUpDeserializer();
        powerUp.setLifeLength(lifeLength);

        assertEquals(lifeLength, powerUp.lifeLength);
    }

    @Test
    public void testSetName() {
        String name = "Speed";

        PowerUpDeserializer powerUp = new PowerUpDeserializer();
        powerUp.setName(name);

        assertEquals(name, powerUp.name);
    }

    @Test
    public void testSetLifeLengthNegative() {
        int lifeLength = -5;

        PowerUpDeserializer powerUp = new PowerUpDeserializer();
        powerUp.setLifeLength(lifeLength);

        assertEquals(lifeLength, powerUp.lifeLength);
    }

    @Test
    public void testSetNameEmpty() {
        String name = "";

        PowerUpDeserializer powerUp = new PowerUpDeserializer();
        powerUp.setName(name);

        assertEquals(name, powerUp.name);
    }

    @Test
    public void testSetNameNull() {
        String name = null;

        PowerUpDeserializer powerUp = new PowerUpDeserializer();
        powerUp.setName(name);

        assertNull(powerUp.name);
    }
}

