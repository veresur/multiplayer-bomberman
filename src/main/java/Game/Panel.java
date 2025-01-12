package Game;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import Game.PowerUp.PowerUp;
import Game.Objects.Player;

public class Panel extends JPanel {
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JLabel counterLabel;
	private JPanel playerContainer;
	private int counter;
	private final Map map;
	private final Game game;

	public Panel(Map map, Game game) {
		this.map = map;
		this.setLayout(new BorderLayout());
		this.game = game;

		this.leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		this.rightPanel = new JPanel();

		JButton saveButton = new JButton("Save and quit");
		saveButton.addActionListener(e -> this.game.saveGameState());
		JLabel text = new JLabel("Round", SwingConstants.CENTER);
		text.setAlignmentX(CENTER_ALIGNMENT);
		leftPanel.add(text);
		this.counter = 0;
		this.counterLabel = new JLabel("0", SwingConstants.CENTER);
		this.counterLabel.setAlignmentX(CENTER_ALIGNMENT);
		leftPanel.add(this.counterLabel);
		leftPanel.add(new JLabel(" "));
		leftPanel.add(new JLabel(" "));
		saveButton.setAlignmentX(CENTER_ALIGNMENT);
		leftPanel.add(saveButton);
		this.playerContainer = new JPanel(new GridBagLayout());
		rightPanel.add(playerContainer);

		this.add(leftPanel, BorderLayout.WEST);
		this.add(rightPanel, BorderLayout.CENTER);

		this.update();
	}

	private void generateRightPanel() {
		this.playerContainer.removeAll();
		GridBagConstraints c = new GridBagConstraints();

		Player player1 = map.players.get(Utils.Enumerated.Player.PLAYER1);
		Player player2 = map.players.get(Utils.Enumerated.Player.PLAYER2);
		Player player3 = map.players.get(Utils.Enumerated.Player.PLAYER3);

		c.ipadx = 5;
		c.ipady = 5;

		c.gridx = 0;
		c.gridy = 0;
		playerContainer.add(new JLabel("Wins:"), c);

		c.gridx = 1;
		c.gridy = 0;
		playerContainer.add(new JLabel(Integer.toString(player1.wins)), c);
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx++;
		playerContainer.add(new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("gameobjects/player1_s.png")))), c);
		for (PowerUp powerUp : player1.powerUpList.getPowerUps()) {
			c.gridx++;
			if (powerUp.lifeLength < 20 && powerUp.lifeLength >= 0) {
				playerContainer.add(new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("gameobjects/timer.png")))), c);
			}

			playerContainer.add(new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("gameobjects/" + powerUp.getImageName() + ".png")))), c);
		}

		c.gridy++;

		c.gridx = 1;
		c.ipadx = 5;
		c.ipady = 5;
		playerContainer.add(new JLabel(Integer.toString(player2.wins)), c);
		c.ipadx = 0;
		c.ipady = 0;
		c.gridx++;
		playerContainer.add(new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("gameobjects/player2_s.png")))), c);
		for (PowerUp powerUp : player2.powerUpList.getPowerUps()) {
			c.gridx++;
			if (powerUp.lifeLength < 20 && powerUp.lifeLength >= 0) {
				playerContainer.add(new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("gameobjects/timer.png")))), c);
			}
			playerContainer.add(new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("gameobjects/" + powerUp.getImageName() + ".png")))), c);
		}

		if (player3 != null) {
			c.gridy++;
			c.gridx = 1;
			c.ipadx = 5;
			c.ipady = 5;
			playerContainer.add(new JLabel(Integer.toString(player3.wins)), c);
			c.ipadx = 0;
			c.ipady = 0;
			c.gridx++;
			playerContainer.add(new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("gameobjects/player3_s.png")))), c);
			for (PowerUp powerUp : player3.powerUpList.getPowerUps()) {
				c.gridx++;
				if (powerUp.lifeLength < 20 && powerUp.lifeLength >= 0) {
					playerContainer.add(new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("gameobjects/timer.png")))), c);
				}
				playerContainer.add(new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource("gameobjects/" + powerUp.getImageName() + ".png")))), c);
			}
		}



		this.playerContainer.updateUI();
	}
	public void update() {
		generateRightPanel();
		this.counterLabel.setText(map.currentRound + "/" + map.roundCount);
	}
}
