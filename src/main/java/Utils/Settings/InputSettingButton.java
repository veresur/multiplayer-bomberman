package Utils.Settings;

import javax.swing.*;
import java.awt.event.KeyAdapter;

/**
 * Custom Swing Button representation
 */
public class InputSettingButton extends JButton {
    public InputSetting inputSetting;
    public Integer keyCode;
    public KeyAdapter keyEventListener;
}
