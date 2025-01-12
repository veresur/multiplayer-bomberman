package Game.Objects;

import Game.Map;
import Game.PowerUp.PowerUpList;
import Game.PowerUp.PowerUpProps;
import Game.PowerUp.PowerUps.*;
import Utils.DirectionVector;
import Utils.Enumerated.Input;

import java.util.ArrayList;

/**
 * The Player class represents a player object in the game.
 * It handles player movement, placing bombs, and managing power-ups.
 */
public class Player extends MovableObject {
    public String getImageName() {
        return "player" + playerNumber;
    }

    public boolean getIsAlive() {
        return isAlive;
    }

    private boolean isAlive;

    public int wins;

    public int getPlayerNumber() {
        return playerNumber;
    }

    private final int playerNumber;
    private int bombCount = 0;

    private boolean placedBomb = false;

    public PowerUpList powerUpList;

    public ArrayList<Bomb> bombs;
    private int ticks = 0;

    /**
     * Constructs a new Player object with the given coordinates, player number, and map.
     *
     * @param x            The initial x-coordinate of the player.
     * @param y            The initial y-coordinate of the player.
     * @param playerNumber The player number.
     * @param map          The map on which the player exists.
     */
    public Player(int x, int y, int playerNumber, Map map) {
        super(x, y, map);
        this.playerNumber = playerNumber;
        this.wins = 0;
        this.powerUpList = new PowerUpList();
        this.CreateImages();
        this.isAlive = true;
        this.bombs = new ArrayList<Bomb>();
//        this.powerUpList.AddPowerUp(new BonusBomb());
//        this.powerUpList.AddPowerUp(new Detonator());
//        this.powerUpList.AddPowerUp(new SpeedIncrease());
//        this.powerUpList.AddPowerUp(new GodMode());
//        this.powerUpList.AddPowerUp(new GhostMode());
//        this.powerUpList.AddPowerUp(new ExtendExplosionSpread());
//        this.powerUpList.AddPowerUp(new BarricadePowerUp());
    }

    /**
     * Handles the input received for the player.
     *
     * @param input The input received.
     */
    public void handleInput (Input input) {
        if (!this.isAlive) {
            return;
        }

        switch(input) {
            case UP:
                this.direction = DirectionVector.UP;
                break;
            case DOWN:
                this.direction = DirectionVector.DOWN;
                break;
            case LEFT:
                this.direction = DirectionVector.LEFT;
                break;
            case RIGHT:
                this.direction = DirectionVector.RIGHT;
                break;
            case STOP:
                this.direction = DirectionVector.STOP;
                break;
            case BOMB:
                this.placeBomb();
                break;
            case BARRICADE:
                this.placeBarricade();
                break;
            default:
                System.out.println("unhandled input");
        }
    }

    private void step () {
        GameObject nextPlace = this.map.get(this.coordinate.add(this.direction));
        boolean isFreeCoordinate = nextPlace == null;
        boolean isPickUpCoordinate = nextPlace instanceof PickUp;
        boolean isBorderWall = nextPlace instanceof Wall ? ((Wall) nextPlace).borderWall : false;

        if (isFreeCoordinate || isPickUpCoordinate || (this.powerUpList.getPowerUpProps().ghost && !isBorderWall)) {
            this.coordinate.translate(this.direction);

            if (isPickUpCoordinate) {
                this.powerUpList.AddPowerUp(((PickUp) nextPlace).getPowerUp());
                this.map.removeGameObject(nextPlace);
            }
        }
    }

    private void placeBomb() {
        if(!canPlaceBomb()) {
            if (this.powerUpList.getPowerUpProps().detonator) {
                this.bombs.forEach((b) -> {
                    b.Explode();
                });
            }
            return;
        }

        Bomb newBomb = new Bomb(this, this.map);
        this.map.placeGameObject(newBomb, coordinate);
        this.bombs.add(newBomb);

        placedBomb = true;
    }

    private void placeBarricade() {
        if (this.powerUpList.getPowerUpProps().barricades > this.map.getBarricadeCountByPlayer(this)) {
            Barricade newBarricade = new Barricade(this);
            this.map.placeGameObject(newBarricade, coordinate);
        }
    }

    private boolean canPlaceBomb() {
        return this.powerUpList.getPowerUpProps().maxBombCount > this.bombCount;
    }

    private void syncBombsCount() {
        this.bombCount = this.map.GetBombsCountByPlayer(this);
        this.placedBomb = false;
    }

    /**
     * Updates the player's state and actions.
     */
    @Override
    public void update () {
        this.powerUpList.update();
        if (!this.powerUpList.getPowerUpProps().ghost) {
            GameObject currentPlaceGameObject = this.map.get(this.coordinate);
            if (currentPlaceGameObject instanceof Wall || currentPlaceGameObject instanceof Box) {
                this.killYourSelf();
                return;
            }
        }
        this.syncBombsCount();

        if (this.powerUpList.getPowerUpProps().speedIncrease || this.ticks % 2 == 0) {
            this.step();
        }

        this.ticks += 1;
    }

    /**
     * Kills the player if they are not invincible.
     */
    public void killYourSelf () {
        if (!this.powerUpList.getPowerUpProps().god) {
            this.isAlive = false;
        }
    }

    public void respawn() {
        this.powerUpList = new PowerUpList();
        this.isAlive = true;
        this.bombs = new ArrayList<Bomb>();
    }

    public static int comparator(Object o, Object o1) {
        java.util.Map.Entry<Utils.Enumerated.Player, Player> a = (java.util.Map.Entry<Utils.Enumerated.Player, Player>)o;
        java.util.Map.Entry<Utils.Enumerated.Player, Player> b = (java.util.Map.Entry<Utils.Enumerated.Player, Player>)o1;

        if (a.getValue().wins > b.getValue().wins) {
            return -1;
        } else if (a.getValue().wins == b.getValue().wins) {
            return 0;
        } else {
            return 1;
        }
    }
}
