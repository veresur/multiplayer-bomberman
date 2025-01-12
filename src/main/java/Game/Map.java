package Game;

import Game.Objects.*;
import Game.PowerUp.PowerUp;
import Game.PowerUp.PowerUps.*;
import Utils.Coordinate;
import Utils.Deserializer.EnemyDeserializer;
import Utils.Deserializer.GameObjectDeserializer;
import Utils.Deserializer.PlayerDeserializer;
import Utils.Deserializer.PowerUpDeserializer;
import Utils.DirectionVector;
import Utils.Props;

import java.util.*;

/**
 * representation of the field where characters & enemies move
 */
public class Map {
	/**
	 * Gameobject matrix of the 2D plane
	 */
	public GameObject[][] map;
	/**
	 * hashmap of logical representation and physical representation of players
	 */
	public HashMap<Utils.Enumerated.Player, Player> players;
	/**
	 * list of enemies
	 */
	public List<Enemy> enemies;

	public int currentRound = 1;
	public int roundCount = 3;
	private int mapOption = 0;
	private int playerCount = 2;

	/**
	 * @param cord coordinate on which the map is checked
	 * @return whether the map is free on the provided coordinate
	 */
	public boolean isCoordinateFree(Coordinate cord) {
		GameObject objectOnCord = this.map[cord.X][cord.Y];
		if (objectOnCord != null && ! (objectOnCord instanceof PickUp))
			return false;

		for (Enemy enemy : enemies) {
			if (enemy.getPosition().equals(cord))
				return false;
		}

		for (java.util.Map.Entry<Utils.Enumerated.Player, Player> playerEntry : players.entrySet()) {
			if (playerEntry.getValue().getPosition().equals(cord))
				return false;
		}

		return true;
	}

	/**
	 * @param cord where the map will be checked
	 * @return whether the map contains a wall on the coordinate
	 */
	public boolean isCoordinateNotWall(Coordinate cord) {
		return !(this.map[cord.X][cord.Y] instanceof Wall);
	}

	/**
	 * @param player which the bombs are associated to
	 * @return count of active bombs placed by the player
	 */
	public int GetBombsCountByPlayer(Player player) {
		int bombsCount = 0;

		for (int x = 0; x < Props.MAP_SIZE; x++) {
			for (int y = 0; y < Props.MAP_SIZE; y++) {
				if (map[x][y] != null && map[x][y].getClass() == Bomb.class) {
					if ( ((Bomb)map[x][y]).GetOwner() == player) {
						bombsCount++;
					}
				}
			}
		}

		return bombsCount;
	}

	public int getBarricadeCountByPlayer (Player player) {
		int barricadeCount = 0;

		for (int x = 0; x < Props.MAP_SIZE; x++) {
			for (int y = 0; y < Props.MAP_SIZE; y++) {
				if (map[x][y] != null && map[x][y] instanceof Barricade) {
					if ( ((Barricade)map[x][y]).getOwner() == player) {
						barricadeCount++;
					}
				}
			}
		}

		return barricadeCount;
	}

	/**
	 * @param cord coordinate of the map
	 * @return the player on coordinate
	 */
	public Player GetPlayerOnCoordinate(Coordinate cord) {

		for (java.util.Map.Entry<Utils.Enumerated.Player, Player> playerEntry : players.entrySet()) {
			if (playerEntry.getValue().getPosition().equals(cord))
				return playerEntry.getValue();
		}

		return null;
	}

	/**
	 * @param gameObject the requested object
	 * @return the coordinate of the game object
	 */
	public Coordinate GetGameObjectPosition(GameObject gameObject) {
		for (int x = 1; x < Props.MAP_SIZE-1; x++) {
			for (int y = 1; y < Props.MAP_SIZE-1; y++) {
				if (this.map[x][y] == gameObject)
					return new Coordinate(x, y);
			}
		}

		return null;
	}

	/**
	 * generates map1
	 */
	private void generateMap1() {
		Random random = new Random(13);

		for (int x = 1; x < Props.MAP_SIZE - 1; x++) {
			final int tempX = x;
			for (int y = 1; y < Props.MAP_SIZE - 1; y++) {
				final int tempY = y;
				if (players.values().stream().anyMatch(p -> {
					Coordinate pc = p.getPosition();
					return pc.X == tempX && pc.Y == tempY;
				})) continue;

				if (x % 2 == 0 && y % 2 == 0) {
					map[x][y] = new Wall();
				} else {
					boolean placeBox = random.nextInt(3) == 2;
					if (placeBox) {
						map[x][y] = new Box();
					}
				}
			}
		}

		this.enemies.add(new Enemy(6, 5, this));
	}

	/**
	 * generates map2
	 */
	private void generateMap2() {
		Random random = new Random(24);
		int msize = Props.MAP_SIZE;

		int innerSquareStart = msize / 4;
		int innerSquareEnd = (msize * 3) / 4;

		for (int x = 1; x < msize - 1; x++) {
			final int tempX = x;
			for (int y = 1; y < msize - 1; y++) {
				final int tempY = y;

				if (players.values().stream().anyMatch(p -> {
					Coordinate pc = p.getPosition();
					return pc.X == tempX && pc.Y == tempY;
				})) continue;

				if(x != msize/2 && y != msize / 2) {
					if ((x == innerSquareStart || x == innerSquareEnd) && y >= innerSquareStart && y <= innerSquareEnd) {
						map[x][y] = new Wall();
					} else if ((y == innerSquareStart || y == innerSquareEnd) && x >= innerSquareStart && x <= innerSquareEnd) {
						map[x][y] = new Wall();
					} else if ((x == innerSquareStart || x == innerSquareEnd) && (y == innerSquareStart || y == innerSquareEnd)) {
						map[x][y] = null;
					} else {
						boolean placeBox = random.nextInt(2) == 1;
						if (placeBox) {
							map[x][y] = new Box();
						}
					}
				}
			}
		}

		map[msize/2][msize/2] = new Wall();
		this.enemies.add(new Enemy(13, 7, this));
	}

	/**
	 * generates map3
	 */
	private void generateMap3() {
		final int enemyX = 13;
		final int enemyY = 7;
		for (int x = 1; x < Props.MAP_SIZE - 1; x++) {
			final int tempX = x;
			for (int y = 1; y < Props.MAP_SIZE - 1; y++) {
				final int tempY = y;
				boolean playerNearby = players.values().stream().anyMatch(p -> {
					Coordinate pc = p.getPosition();
					return Math.abs(pc.X - tempX) <= 1 && Math.abs(pc.Y - tempY) <= 1;
				});

				boolean enemyNearby = Math.abs(enemyX - tempX) <= 1 && Math.abs(enemyY - tempY) <= 1;
				if (!playerNearby && !enemyNearby) map[x][y] = new Box();
			}
		}

		this.enemies.add(new Enemy(enemyX, enemyY, this));
	}

	/**
	 * @param option selected map index
	 * @param playerCount how many players will play the game
	 */
	public Map(int option, int playerCount, int roundCount, int currentRound, PlayerDeserializer[] playersJson, EnemyDeserializer[] enemiesJson, GameObjectDeserializer[] gameObjectsJson) {
		this.currentRound = currentRound;
		this.roundCount = roundCount;
		this.mapOption = option;
		this.playerCount = playerCount;

		this.players = new HashMap<>();
		for (PlayerDeserializer playerJson : playersJson) {
			try {
				Player player = new Player(playerJson.coord.X, playerJson.coord.Y, playerJson.playerNumber, this);

				player.powerUpList.setPowerUpProps(playerJson.powerUpProps);
				for (PowerUpDeserializer powerUpJson : playerJson.powerUpList) {
					PowerUp powerUp = convertPowerUpClassNameToPowerUpObject(powerUpJson.name);
					powerUp.lifeLength = powerUpJson.lifeLength;
					player.powerUpList.AddPowerUp(powerUp);
				}

				this.players.put(Utils.Enumerated.Player.valueOf(playerJson.key), player);
			}
			catch (IllegalArgumentException e) {
				System.out.println("The provided player key does not match any enum constants.");
			}
		}

		this.enemies = new ArrayList<>();
		for (EnemyDeserializer enemyJson : enemiesJson) {
			this.enemies.add(new Enemy(enemyJson.coord.X, enemyJson.coord.Y, this));
		}

		this.map = new GameObject[Props.MAP_SIZE][Props.MAP_SIZE];
		for (GameObjectDeserializer gameObjectJson : gameObjectsJson) {
			GameObject gameObject;
			switch (gameObjectJson.name) {
				case "Wall":
					gameObject = new Wall(gameObjectJson.borderWall);
					break;
				case "Box":
					gameObject = new Box();
					break;
				case "PickUp":
					gameObject = new PickUp(this.convertPowerUpClassNameToPowerUpObject(gameObjectJson.type));
					break;
				case "Bomb":
					gameObject = new Bomb(players.get(Utils.Enumerated.Player.valueOf("PLAYER".concat(gameObjectJson.owner))), this);
					break;
				case "Barricade":
					gameObject = new Barricade(players.get(Utils.Enumerated.Player.valueOf("PLAYER".concat(gameObjectJson.owner))));
					break;
				default:
					throw new IllegalArgumentException("unknown gameObject name");
			}
			this.map[gameObjectJson.coord.X][gameObjectJson.coord.Y] = gameObject;
		}
	}
	public Map(int option, int playerCount, int roundCount) {
		this.roundCount = roundCount;
		this.mapOption = option;
		this.playerCount = playerCount;

		this.players = new HashMap<>();

		this.players.put(Utils.Enumerated.Player.PLAYER1, new Player(1, 1, 1, this));
		this.players.put(Utils.Enumerated.Player.PLAYER2, new Player(6, 7, 2, this));

		if(playerCount == 3) {
			this.players.put(Utils.Enumerated.Player.PLAYER3, new Player(9, 10, 3, this));
		}

		this.StartNewRound();
	}

	/**
	 * places Bomb or Barricade on the requested coordinate
	 * @param go gameobject
	 * @param c coordinate
	 */
	public void placeGameObject(GameObject go, Coordinate c) {
		if(go instanceof Bomb || go instanceof Barricade) this.map[c.X][c.Y] = go;
	}

	/**
	 * replaces box with powerup on the map
	 * @param go gameobject (box)
	 * @param c coordinate on the map
	 */
	public void replaceGameObject(GameObject go, Coordinate c) {
		if(!(go instanceof Box)) return;
		GameObject newPowerUp = new PickUp(PowerUp.getRandomPowerup());
		this.map[c.X][c.Y] = newPowerUp;
	}

	/**
	 * @param gameObject gameobject to be removed from the map
	 */
	public void removeGameObject(GameObject gameObject) {
		for (int x = 0; x < Props.MAP_SIZE; x++) {
			for (int y = 0; y < Props.MAP_SIZE; y++) {
				if (map[x][y] == gameObject)
					map[x][y] = null;
			}
		}
	}

	/**
	 * @param c coordinate
	 * @return gameobject on coordinate
	 */
	public GameObject get(Coordinate c) {
		return this.map[c.X][c.Y];
	}

	/**
	 * @param coordinateToCheck coordinate to be checked
	 * @return list of free coordinates around
	 */
	public ArrayList<Coordinate> getFreeDirectionsAround (Coordinate coordinateToCheck) {
		ArrayList<Coordinate> freeDirections = new ArrayList<>();

		for (Coordinate c : DirectionVector.Directions) {
			GameObject target = this.get(c.add(coordinateToCheck));
			if (target == null || target instanceof PickUp) {
				freeDirections.add(c);
			}
		}

		return freeDirections;
	}

	private static Integer[][] startPositions = {
			{1, 1},
			{6, 7},
			{9, 10}
	};

	public void StartNewRound() {
		map = new GameObject[Props.MAP_SIZE][Props.MAP_SIZE];
		this.enemies = new ArrayList<>();

		int index = 0;
		for (Player player : this.players.values()) {
			player.setPosition(new Coordinate(startPositions[index][0], startPositions[index][1]));
			player.respawn();
			index++;
		}

		for (int i = 0; i < Props.MAP_SIZE; i++) {
			map[i][0] = new Wall(true);
			map[0][i] = new Wall(true);

			map[Props.MAP_SIZE-1][i] = new Wall(true);
			map[i][Props.MAP_SIZE-1] = new Wall(true);
		}

		map[5][5] = new Box();
		switch (this.mapOption) {
			case 2:
				generateMap2();
				break;
			case 3:
				generateMap3();
				break;
			default:
				generateMap1();
		}
	}

	public int getMapOption() {
		return mapOption;
	}

	public int getPlayerCount () {
		return this.playerCount;
	}

	private PowerUp convertPowerUpClassNameToPowerUpObject (String name) {
		switch (name) {
			case "BarricadePowerUp":
				return new BarricadePowerUp();
			case "BonusBomb":
				return new BonusBomb();
			case "Detonator":
				return new Detonator();
			case "ExtendExplosionSpread":
				return new ExtendExplosionSpread();
			case "GhostMode":
				return new GhostMode();
			case "GodMode":
				return new GodMode();
			case "SpeedIncrease":
				return new SpeedIncrease();
			default:
				throw new IllegalArgumentException("unknown powerup name");
		}
	}
}
