package GUI.Screens;

import GUI.Display;
import Utils.Coordinate;

import javax.swing.*;

/**
 * Abstract panel representation
 */
public abstract class Screen extends JPanel {
	/**
	 * @return frame size to be rendered
	 */
	public abstract Coordinate getFrameSize();
}
