package Game.Engine;

import Game.Map;
import Game.Objects.GameObject;
import Utils.Coordinate;
import Utils.Props;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * The Render class represents a JPanel used for rendering game objects onto the screen.
 * It handles rendering of the game map, players, and enemies.
 */
public class Render extends JPanel {
	private Map map;

	/**
	 * Renders the specified game object image at the given coordinates on the graphics context.
	 *
	 * @param g   The Graphics object used for rendering.
	 * @param x   The x-coordinate of the object.
	 * @param y   The y-coordinate of the object.
	 * @param img The image of the game object to render.
	 */
	public static void RenderGameObjectImage(Graphics g, int x, int y, Image img) {
		g.drawImage(img, x*Props.RECT_SIZE, y*Props.RECT_SIZE, Props.RECT_SIZE, Props.RECT_SIZE, null);
	}

	/**
	 * Constructs a new Render object with the given map.
	 *
	 * @param map The map object representing the game map.
	 */
	public Render(Map map) {
		this.map = map;
		this.setSize(Props.MAP_SIZE * Props.RECT_SIZE, Props.MAP_SIZE * Props.RECT_SIZE);
		this.setBackground(Color.BLACK);
	}

	/**
	 * Updates the rendering of game objects on the panel.
	 * This method triggers a repaint of the panel.
	 */
	public void update() {
		this.repaint();
	}

	private final Image backgroundGrass = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("gameobjects/grass.png"))).getImage();

	private void renderGameObjects(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(0,0,Props.MAP_SIZE * Props.RECT_SIZE, Props.MAP_SIZE * Props.RECT_SIZE);

		for (int x = 0; x < Props.MAP_SIZE; x++) {
			for (int y = 0; y < Props.MAP_SIZE; y++) {
				RenderGameObjectImage(g, x, y, backgroundGrass);
			}
		}

		// render map layer
		for (int x = 0; x < Props.MAP_SIZE; x++) {
			for (int y = 0; y < Props.MAP_SIZE; y++) {
				GameObject obj = map.get(new Coordinate(x, y));
				if (obj != null) {
					obj.render(g, x, y);
				}
			}
		}

		// render players
		this.map.players.forEach((playerEnum, playerObject) -> {
			if (playerObject.getIsAlive()) {
				playerObject.render(g);
			}
		});

		// render enemies
		this.map.enemies.forEach((enemyObject) -> {
			enemyObject.render(g);
		});
	}

	/**
	 * Overrides the paintComponent method to perform custom painting of game objects.
	 *
	 * @param g The Graphics object used for rendering.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.renderGameObjects(g);
	}
}
