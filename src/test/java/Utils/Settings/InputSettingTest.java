package test.Utils.Settings;

import Utils.Enumerated.Input;
import Utils.Enumerated.Player;
import Utils.Settings.InputSetting;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InputSettingTest {

    @Test
    public void testConstructorAndGetters() {
        Input input = Input.RIGHT;
        Player player = Player.PLAYER1;
        InputSetting inputSetting = new InputSetting(input, player);
        assertEquals(input, inputSetting.input);
        assertEquals(player, inputSetting.player);
    }

    @Test
    public void testToString() {
        Input input = Input.LEFT;
        Player player = Player.PLAYER2;
        InputSetting inputSetting = new InputSetting(input, player);
        assertEquals("PLAYER2 LEFT", inputSetting.toString());
    }

    @Test
    public void testCompareTo() {
        InputSetting inputSetting1 = new InputSetting(Input.BARRICADE, Player.PLAYER1);
        InputSetting inputSetting2 = new InputSetting(Input.BOMB, Player.PLAYER2);
        InputSetting inputSetting3 = new InputSetting(Input.UP, Player.PLAYER3);

        assertEquals(0, inputSetting1.compareTo(inputSetting1));
        assertEquals(-1, inputSetting1.compareTo(inputSetting2));
        assertEquals(1, inputSetting2.compareTo(inputSetting1));

        assertEquals(-2, inputSetting1.compareTo(inputSetting3));
        assertEquals(-2, inputSetting1.compareTo(inputSetting3));
        assertEquals(2, inputSetting3.compareTo(inputSetting1));
    }
}
