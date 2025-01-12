package GUI;

import GUI.Screens.GameEndScreen;
import GUI.Screens.HomeScreen;
import GUI.Screens.Screen;
import Game.Objects.Player;
import Utils.Coordinate;
import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import java.awt.*;

/**
 * class representing a shadow "frame"
 * which always shows the requested page / frame / screen
 */
public class Display {
	private Screen currentScreen;
	private JFrame frame;

	/**
	 * constructor, loads default frame setings of Swing
	 * and initially loads HomeScreen
	 */
	public Display() {
		FlatDarculaLaf.setup();
		frame = new JFrame("Bomberman Jatekocska :)");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.ReloadScreen(new HomeScreen(this));
	}

	/**
	 * changes current screen with parameter and grabs focus
	 * @param newScreen the new screen we want to see displayed
	 */
	public void ReloadScreen(Screen newScreen) {
		if (this.currentScreen != null) {
			frame.getContentPane().remove(this.currentScreen);
		}
		this.currentScreen = newScreen;
		frame.getContentPane().add(currentScreen);

		Coordinate frameSize = currentScreen.getFrameSize();
		frame.setSize(frameSize.X, frameSize.Y);
		frame.setPreferredSize(frame.getSize());
		frame.pack();
		frame.setResizable(true);
		frame.setVisible(true);

		//newScreen.requestFocus();
		newScreen.grabFocus();
	}
}
