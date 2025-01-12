package GUI.Screens;

import javax.swing.*;

import GUI.Display;
import Game.*;
import Utils.Coordinate;
import Utils.Deserializer.*;
import Utils.Enumerated.Input;
import Utils.Props;
import Utils.Settings.InputSetting;
import Utils.Settings.Settings;

import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Game screen frame
 */
public class GameScreen extends Screen {
	private Display display;
	private Game game;

	/**
	 * @return frame size for render
	 */
	public Coordinate getFrameSize() {
		return new Coordinate((Props.MAP_SIZE + 2) * Props.RECT_SIZE, (Props.MAP_SIZE + 4) * Props.RECT_SIZE + 30);
	}

	public GameScreen (Display display, GameDeserializer gameDeserializer) {
		this.display = display;
		MapDeserializer mapJson = gameDeserializer.map;
		Map map = new Map(mapJson.mapOption, mapJson.playerCount,mapJson.roundCount, mapJson.currentRound, mapJson.players, mapJson.enemies, mapJson.objects);
		this.game = new Game(display, map, this::onGameEnd);
		this.init();
		this.add(this.game);
	}
	/**
	 * initializes the game and associated controllers
	 * @param display used for reloading screen
	 * @param playerCount selected option for the actual game
	 * @param mapNumber selected map for the game to be played on
	 * @param roundCount amount of rounds for the game to last
	 */
	public GameScreen(Display display, int playerCount, int mapNumber, int roundCount) {
		this.display = display;
		this.game = new Game(display, playerCount, mapNumber, roundCount, this::onGameEnd);
		this.init();
		this.add(game);
	}

	private void init () {
		this.setBackground(Color.red);
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		Settings.loadConfigFile();
		Settings.settings.forEach((keyCode, input) -> {
			this.getInputMap().put(KeyStroke.getKeyStroke(keyCode, 0, false), input.toString());
			this.getActionMap().put(input.toString(), new AbstractAction() {
				@Override
				public void actionPerformed(ActionEvent e) {
					game.handleInput(input);
				}
			});

			if (Input.isDirection(input.input)) {
				InputSetting stop = new InputSetting(Input.STOP, input.player);

				this.getInputMap().put(KeyStroke.getKeyStroke(keyCode, 0, true), stop.toString());

				this.getActionMap().put(stop.toString(), new AbstractAction() {
					@Override
					public void actionPerformed(ActionEvent e) {
						game.handleInput(stop);
					}
				});
			}

		});
	}

	private void onGameEnd(Integer winner) {
		this.display.ReloadScreen(new GameEndScreen(this.display, winner));
	}
}
