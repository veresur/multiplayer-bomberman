package Game.Objects;

import Game.Map;
import Utils.Coordinate;
import Utils.DirectionVector;

import java.util.ArrayList;
import java.util.Random;

/**
 * enemy representation of a movable game object
 */
public class Enemy extends MovableObject {
	/**
	 * @param x
	 * @param y
	 * @param map
	 */
	public Enemy(int x, int y, Map map) {
		super(x, y, map);
		this.rand = new Random();
	}

	/**
	 * @return the image to be rendered for enemy
	 */
	public String getImageName() {
		return "enemy";
	}

	private final Random rand;

	/**
	 * moves the enemy on the map based on direction & speed
	 */
	private void move() {
		// checking if moving in current direction would be valid
		boolean moveFree = this.map.isCoordinateFree(this.coordinate.add(this.direction));

		if (!moveFree) {
			// cannot move in current direction, changing direction randomly

			ArrayList<Coordinate> freeDirections = this.map.getFreeDirectionsAround(this.coordinate);
			int randomDirectionIndex = rand.nextInt( freeDirections.size());
			this.direction = freeDirections.get(randomDirectionIndex);
		} else {
			this.coordinate.translate(this.direction);


			if (rand.nextInt(5) == 0) {
				// changing direction
				ArrayList<Coordinate> freeDirections = this.map.getFreeDirectionsAround(this.coordinate);

				// removing current direction from valid directions
				freeDirections.removeIf(c -> c.equals(this.direction));

				// setting new direction randomly
				int randomDirectionIndex = rand.nextInt(freeDirections.size());
				this.direction = freeDirections.get(randomDirectionIndex);
			}
		}
	}

	/**
	 * checks if enemy is close to a player and should kill it
	 * if so then kills it
	 */
	private void checkForKills () {
		for (Player player : this.map.players.values()) {
			if (this.getPosition().equals(player.getPosition())) {
				player.killYourSelf();
			}
			else {
				for (Coordinate cord : DirectionVector.Directions) {
					if (this.getPosition().add(cord).equals(player.getPosition())) {
						player.killYourSelf();
					}
				}
			}
		}
	}

	@Override
	public void update() {
		this.move();
		this.checkForKills();
	}
}
