package Utils.Settings;

import Utils.Enumerated.Input;
import Utils.Enumerated.Player;

import java.io.*;
import java.util.*;

/**
 * Class used for representing game & user Settings
 */
public class Settings {
	/**
	 * HashMap of settings where the input settings are associated with a number (keycode)
	 * for O(1) lookup efficiency
	 */
	public static HashMap<Integer, InputSetting> settings = new HashMap<>();

	/**
	 * method used for updating the predefined settings file with
	 * the users' new keybinding settings
	 */
	public static void updateConfigFile () {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(Objects.requireNonNull(Settings.class.getResource("/settings.txt")).getPath()))) {
			boolean firstLine = true;

			for (Map.Entry<Integer, InputSetting> entry : Settings.settings.entrySet()) {
				String player = entry.getValue().player.toString().toLowerCase();
				String action = entry.getValue().input.toString().toLowerCase();

				if (!firstLine) {
					writer.newLine();
				}
				writer.write(player + "." + action + " " + entry.getKey().toString());
				firstLine = false;
			}
		}
		catch (IOException e) {
			System.out.println("Something went wrong when writing config file.");
		}
	}

	/**
	 * Loads the predefined config file where user game controls are stored
	 */
	public static void loadConfigFile () {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(Settings.class.getResourceAsStream("/settings.txt"))));
			String line = br.readLine();

			while (line != null) {
				String[] splitLine = line.split(" ");
				String[] splitAction = splitLine[0].split("\\.");


				Player currentPlayer;
				switch (splitAction[0]) {
					case "player1":
						currentPlayer = Player.PLAYER1;
						break;
					case "player2":
						currentPlayer = Player.PLAYER2;
						break;
					case "player3":
						currentPlayer = Player.PLAYER3;
						break;
					default:
						currentPlayer = null;
						break;
				}

				Input currentInput;
				switch (splitAction[1]) {
					case "up":
						currentInput = Input.UP;
						break;
					case "down":
						currentInput = Input.DOWN;
						break;
					case "left":
						currentInput = Input.LEFT;
						break;
					case "right":
						currentInput = Input.RIGHT;
						break;
					case "bomb":
						currentInput = Input.BOMB;
						break;
					case "barricade":
						currentInput = Input.BARRICADE;
						break;
					default:
						currentInput = null;
						break;
				}

				if (currentPlayer != null && currentInput != null) {
					settings.put(Integer.parseInt(splitLine[1], 10), new InputSetting(currentInput, currentPlayer));
				}

				line = br.readLine();
			}

			br.close();
		}
		catch (FileNotFoundException e) {
			throw new RuntimeException("settings.txt not found");
		}
		catch (IOException e) {
			throw new RuntimeException("settings.txt couldn't be read");
		}

	}

	/**
	 * Sorts the settings hashmap
	 */
	public static void Sort() {
		List<Map.Entry<Integer, InputSetting>> list = new LinkedList<>(Settings.settings.entrySet());

		// Custom Comparator to compare Map.Entry objects based on values
		list.sort(Map.Entry.comparingByValue());

		// LinkedHashMap to preserve the insertion order while iterating
		HashMap<Integer, InputSetting> sortedHashMap = new LinkedHashMap<>();
		for (Map.Entry<Integer, InputSetting> entry : list) {
			sortedHashMap.put(entry.getKey(), entry.getValue());
		}

		Settings.settings = sortedHashMap;
	}
}
