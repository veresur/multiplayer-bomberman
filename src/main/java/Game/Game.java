package Game;

import GUI.Display;
import GUI.Screens.HomeScreen;
import Game.Engine.*;
import Game.Objects.Enemy;
import Game.Objects.Player;
import Utils.Props;
import Utils.Serializer.*;
import Utils.Serializer.MovableGameObject.*;
import Utils.Settings.InputSetting;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Consumer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * The Game class represents the main game panel that contains the game rendering and physics engines.
 */
public class Game extends JPanel {
	private Render renderEngine;
	private Physics physicsEngine;
	private Timer renderEngineTimer;
	private Timer physicsEngineTimer;
	private Panel gamePanel;
	private Map map;
	private Consumer<Integer> endGame;
	private Display display;

	/**
	 * Constructs a new Game object with the specified number of players, map number, and round count.
	 *
	 * @param playerCount The number of players in the game.
	 * @param mapNumber   The number representing the map to use.
	 * @param roundCount  The number of rounds to play.
	 */
	public Game(Display display, int playerCount, int mapNumber, int roundCount, Consumer<Integer> endGame) {
		this.display = display;
		this.map = new Map(mapNumber, playerCount, roundCount);
		this.endGame = endGame;
		this.init();
	}

	public Game(Display display, Map map, Consumer<Integer> endGame) {
		this.display = display;
		this.map = map;
		this.endGame = endGame;
		this.init();
	}

	private void init () {
		renderEngine = new Render(this.map);
		physicsEngine = new Physics(this.map, this::onGameEnd);
		gamePanel = new Panel(this.map, this);

		this.renderEngineTimer = new Timer(1000 / 100, e -> renderEngine.update());
		this.physicsEngineTimer = new Timer(150, e -> {
			physicsEngine.update();
			gamePanel.update();
		});

		renderEngine.setPreferredSize(new Dimension(Props.MAP_SIZE * Props.RECT_SIZE, Props.MAP_SIZE * Props.RECT_SIZE));
		renderEngine.setMaximumSize(new Dimension(Props.MAP_SIZE * Props.RECT_SIZE, Props.MAP_SIZE * Props.RECT_SIZE));
		renderEngine.setMinimumSize(new Dimension(Props.MAP_SIZE * Props.RECT_SIZE, Props.MAP_SIZE * Props.RECT_SIZE));
		renderEngine.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		gamePanel.setPreferredSize(new Dimension(Props.MAP_SIZE * Props.RECT_SIZE, 150));
		gamePanel.setMaximumSize(new Dimension(new Dimension(600, 150)));
		gamePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.add(gamePanel);
		renderEngine.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(renderEngine);

		renderEngineTimer.start();
		physicsEngineTimer.start();
	}

	private void onGameEnd(Integer winner) {
		this.physicsEngineTimer.stop();
		this.renderEngineTimer.stop();
		this.endGame.accept(winner);
	}

	/**
	 * Handles input events for the players.
	 *
	 * @param input The input setting representing the player and their action.
	 */
	public void handleInput (InputSetting input) {
		if (this.map.players.containsKey(input.player)) {
			this.map.players.get(input.player).handleInput(input.input);
		}
	}

	public void saveGameState() {
		Gson gson = new GsonBuilder()
				.registerTypeAdapter(Game.class, new GameSerializer())
				.registerTypeAdapter(Map.class, new MapSerializer())
				.registerTypeAdapter(Enemy.class, new EnemySerializer())
				.registerTypeAdapter(Player.class, new PlayerSerializer())
				.create();

		String json = gson.toJson(this);

		final JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");

		// Set the directory to the user's home or current directory
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

		int userSelection = fileChooser.showSaveDialog(null);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			// Make sure the file has the correct extension
			if (!fileToSave.getPath().toLowerCase().endsWith(".json")) {
				fileToSave = new File(fileToSave.getPath() + ".json");
			}

			try (FileWriter writer = new FileWriter(fileToSave)) {
				writer.write(json);
				JOptionPane.showMessageDialog(null, "File was saved successfully!", "File Saved", JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error occurred while saving the file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}

			this.display.ReloadScreen(new HomeScreen(display));
		}
	}

	public Map getMap () {
		return this.map;
	}
}
