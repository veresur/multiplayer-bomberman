package GUI.Screens;

import GUI.Display;
import Utils.Coordinate;
import Utils.Settings.InputSetting;
import Utils.Settings.InputSettingButton;
import Utils.Settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import java.util.Map;
import java.awt.event.KeyEvent;

/**
 * Settings Screen
 */
public class SettingsScreen extends Screen {
	/**
	 * @return frame size for rendering
	 */
	public Coordinate getFrameSize () {
		return new Coordinate(400, 900);
	}
	private ArrayList<InputSettingButton> buttons;

	public JLabel escapeLabel;

	/**
	 * setup UI
	 * @param display used for rendering and reloading screens
	 */
	public SettingsScreen (Display display) {
		this.buttons = new ArrayList<>();

		this.setLayout(new GridBagLayout());

		this.escapeLabel = new JLabel();
		escapeLabel.setText(" ");
		escapeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		escapeLabel.setFont(new Font("Serig", Font.PLAIN, 15));

		if (Settings.settings.isEmpty()) Settings.loadConfigFile();
		Settings.Sort();

		this.drawSettings(display);
	}

	/**
	 * @param display draws UI
	 */
	private void drawSettings (Display display) {
		this.removeAll();
		this.revalidate();
		this.repaint();
		this.buttons = new ArrayList<>();

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.ipady = 15;
		c.ipadx = 15;
		c.gridwidth = 3;

		this.add(escapeLabel, c);

		c.gridy++;
		c.gridwidth = 1;
		for (Map.Entry<Integer, InputSetting> entry : Settings.settings.entrySet()) {
			JLabel settingName = new JLabel();
			settingName.setText(entry.getValue().player.toString() + " " + entry.getValue().input.toString());

			JLabel settingKey = new JLabel();
			settingKey.setText(KeyStroke.getKeyStroke(entry.getKey(), 0).toString().split(" ")[1]);

			InputSettingButton button = new InputSettingButton();
			this.buttons.add(button);
			button.setText("click to change");
			button.inputSetting = entry.getValue();
			button.keyCode = entry.getKey();

			button.addActionListener(new ActionListener() {
				public void actionPerformed (ActionEvent event) {
					for (InputSettingButton b : buttons) {
						b.removeKeyListener(b.keyEventListener);
						b.setText("click to change");
					}

					button.setText("listening");
					escapeLabel.setText("press an unused key, or escape to cancel");

					button.addKeyListener(new KeyAdapter() {
						public void keyPressed (KeyEvent event) {
							int keyCode = event.getKeyCode();

							/*
								early return if key is already set
								set text to default with message to user
							*/
							if (Settings.settings.containsKey(keyCode)) {
								button.setText("click to change");
								escapeLabel.setText("Key is already assigned!");
								return;
							}

							if (keyCode != 27) { // 27 is the escape character
								Settings.settings.remove(button.keyCode);
								Settings.settings.put(keyCode, button.inputSetting);
								Settings.Sort();
							}

							escapeLabel.setText(" ");
							button.setText("click to change");
							drawSettings(display);

							button.removeKeyListener(this);
						}
					});
				}
			});

			c.gridx = 0;
			this.add(settingName, c);
			c.gridx = 1;
			this.add(settingKey, c);
			c.gridx = 2;
			this.add(button, c);

			c.gridy++;
		}

		c.insets = new Insets(10,0,0,0);  //top padding
		c.gridwidth = 1;
		c.gridx = 1;

		JButton backButton = new JButton();
		backButton.setText("back");
		backButton.addActionListener(e -> display.ReloadScreen(new HomeScreen(display)));
		this.add(backButton, c);

		c.gridwidth = 2;
		c.gridx = 2;
		JButton saveButton = new JButton();
		saveButton.setText("save");
		saveButton.addActionListener(e -> {
            Settings.updateConfigFile();
            display.ReloadScreen(new HomeScreen(display));
        });
		this.add(saveButton, c);
	}
}
