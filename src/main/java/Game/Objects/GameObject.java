package Game.Objects;

import Utils.Props;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * representation of objects on the map
 */
public abstract class GameObject {
	/**
	 * @return image name of the object, default is grass
	 */
	protected String getImageName() {
		return "grass";
	}

	/**
	 * image of the game object
	 */
	protected final transient Image img;

	/**
	 * loads image to be rendered
	 */
	public GameObject() {
		ClassLoader classLoader = getClass().getClassLoader();
		img = new ImageIcon(Objects.requireNonNull(classLoader.getResource("gameobjects/" + getImageName() + ".png"))).getImage();
	}
	/**
	 * loads image to be rendered
	 */
	public GameObject(String imgName) {
		ClassLoader classLoader = getClass().getClassLoader();
		img = new ImageIcon(Objects.requireNonNull(classLoader.getResource("gameobjects/" + imgName + ".png"))).getImage();
	}

	/**
	 * renders the object on the UI
	 * @param g graphics
	 * @param x coordinate
	 * @param y coordinate
	 */
	public void render(Graphics g, int x, int y) {
		g.drawImage(img, x * Props.RECT_SIZE, y * Props.RECT_SIZE, Props.RECT_SIZE, Props.RECT_SIZE, null);
	}

	/**
	 * updates object on game tick
	 */
	public void update() {
		return;
	}
}
