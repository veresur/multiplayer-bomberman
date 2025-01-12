package GUI.Screens;

import GUI.Display;
import Utils.Coordinate;
import Utils.Deserializer.GameDeserializer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * Home Screen UI
 */
public class HomeScreen extends Screen {
	private Display display;

	/**
	 * @return frame size to be rendered
	 */
	public Coordinate getFrameSize() {
		return new Coordinate(300, 300);
	}

	/**
	 * sets up the UI
	 * @param display to reload the appropriate screen associated to the selected action
	 */
	public HomeScreen(Display display) {
		this.display = display;
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		JButton newGame = new JButton("New game");
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				display.ReloadScreen(new StartScreen(display));
			}
		});
		JButton loadGame = new JButton("Load game");
		loadGame.addActionListener(e -> loadGameFromFile());
		JButton settings = new JButton("Settings");
		settings.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				display.ReloadScreen(new SettingsScreen(display));
			}
		});
		JButton exit = new JButton("Exit");
		exit.addActionListener(e -> {
            System.exit(0);
        });

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.ipady = 10;
		c.ipadx = 80;
		this.add(newGame, c);
		c.gridy = 1;
		this.add(loadGame, c);
		c.gridy = 2;
		this.add(settings, c);
		c.gridy = 3;
		this.add(exit, c);

		this.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40));
	}

	public void loadGameFromFile () {
		// Create and configure the file chooser
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select a game state file to load");
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home"))); // Start at user's home directory
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JSON Files", "json"));

		// Show open dialog and wait for user's selection
		int result = fileChooser.showOpenDialog(null);

		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			// Now read the selected file
			try (FileReader reader = new FileReader(selectedFile, StandardCharsets.UTF_8)) {
				Gson gson = new Gson();
				Type type = new TypeToken<GameDeserializer>(){}.getType();
				GameDeserializer deserializedGameObject = gson.fromJson(reader, type);

				display.ReloadScreen(new GameScreen(display, deserializedGameObject));
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Error occurred while reading the file.", "File Read Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(null, "No file was selected.", "Loading Cancelled", JOptionPane.WARNING_MESSAGE);
		}
	}
}
