package Game.Engine;

import Game.Map;
import Game.Objects.GameObject;
import Game.Objects.Player;
import Utils.Coordinate;
import Utils.Props;
import Game.Objects.Enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Physics representation of the game logic
 */
public class Physics {
	/**
	 * game field
	 */
	private final Consumer<Integer> endGame;
	private final Map map;
	private int ticks;

	private boolean roundEnded = false;
	private int roundEndTick;

	public Physics(Map map, Consumer<Integer> endGame) {
		this.map = map;
		this.ticks = 0;
		this.endGame = endGame;
	}

	/**
	 * updates game logic / physics when called
	 */
	public void update() {
		ArrayList<Utils.Enumerated.Player> alivePlayers = new ArrayList<>();

		for (java.util.Map.Entry<Utils.Enumerated.Player, Player> entry : this.map.players.entrySet()) {
			if (entry.getValue().getIsAlive()) {
				entry.getValue().update();
				if (entry.getValue().getIsAlive()) {
					alivePlayers.add(entry.getKey());
				}
			}
		}

		if (this.ticks % 2 == 0) {
			for (int x = 0; x < Props.MAP_SIZE; x++) {
				for (int y = 0; y < Props.MAP_SIZE; y++) {
					GameObject obj = this.map.get(new Coordinate(x, y));
					if (obj != null) {
						obj.update();
					}
				}
			}

			for (Enemy enemy : this.map.enemies) {
				enemy.update();
			}
		}

		if (this.roundEnded) {
			this.roundEndTick++;
			if (this.roundEndTick >= 6) {
				if (alivePlayers.size() == 1) {
					this.map.players.get(alivePlayers.get(0)).wins++;
				}
				this.roundEnded = false;
				this.map.currentRound++;
				if (this.map.currentRound <= this.map.roundCount) {
					this.map.StartNewRound();
				} else {
					List<java.util.Map.Entry<Utils.Enumerated.Player, Player>> orderedPlayers = this.map.players.entrySet().stream().sorted(Player::comparator).collect(Collectors.toList());
					if (orderedPlayers.get(0).getValue().wins != orderedPlayers.get(1).getValue().wins) {
						this.endGame.accept(orderedPlayers.get(0).getValue().getPlayerNumber());
					} else {
						this.endGame.accept(-1);
					}
				}
			}
		} else {
			if (alivePlayers.size() <= 1) {
				this.roundEnded = true;
				this.roundEndTick = 0;
			}
		}
		this.ticks += 1;
	}
}
