package Game.Objects;

import Game.Engine.Render;
import Game.Map;
import Utils.Coordinate;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * The Bomb class represents a bomb game object that can explode, causing damage to nearby objects.
 */
public class Bomb extends GameObject {

    private static final Image explosionX = new ImageIcon(Objects.requireNonNull(Bomb.class.getClassLoader().getResource("gameobjects/ex_x.png"))).getImage();
    private static final Image explosionY = new ImageIcon(Objects.requireNonNull(Bomb.class.getClassLoader().getResource("gameobjects/ex_y.png"))).getImage();
    private static final Image explosionCenter = new ImageIcon(Objects.requireNonNull(Bomb.class.getClassLoader().getResource("gameobjects/ex_center.png"))).getImage();
    private static final Image explosionLeft = new ImageIcon(Objects.requireNonNull(Bomb.class.getClassLoader().getResource("gameobjects/ex_left.png"))).getImage();
    private static final Image explosionRight = new ImageIcon(Objects.requireNonNull(Bomb.class.getClassLoader().getResource("gameobjects/ex_right.png"))).getImage();
    private static final Image explosionUp = new ImageIcon(Objects.requireNonNull(Bomb.class.getClassLoader().getResource("gameobjects/ex_up.png"))).getImage();
    private static final Image explosionDown = new ImageIcon(Objects.requireNonNull(Bomb.class.getClassLoader().getResource("gameobjects/ex_down.png"))).getImage();

    private transient int[] explosionSpread; // x-, y-, y+, x+
    private transient boolean[] explosionSpreadBroken;
    private transient Map map;
    private transient int tick = 0;

    private static final int explosionStartTick = 20;
    private static final int explosionSpreadTick = 5;

    private transient int spreadLength = 3;

    private final Player owner;

    public Player GetOwner() {
        return owner;
    }

    @Override
    protected String getImageName() {
        return "bomb";
    }

    /**
     * Constructs a new Bomb object with the given owner and map.
     *
     * @param owner The player who owns the bomb.
     * @param map   The map on which the bomb exists.
     */
    public Bomb(Player owner, Map map) {
        this.owner = owner;
        this.map = map;
        this.explosionSpread = new int[] {0, 0, 0, 0};
        this.explosionSpreadBroken = new boolean[] {false, false, false, false};
        if (this.owner.powerUpList.getPowerUpProps().extendedExplosionSpread) {
            this.spreadLength += 1;
        }
    }

    private void explodeGameObjectOnCoordinate(Coordinate cord) {
        GameObject gameObject = this.map.get(cord);
        if (gameObject == null) {
            return;
        }

        switch (gameObject.getClass().getSimpleName()) {
            case "Bomb":
                ((Bomb)gameObject).Explode();
                break;
            case "Box":
                map.replaceGameObject(gameObject, cord);
                break;
            case "Barricade":
                map.removeGameObject(gameObject);
                break;
            default:
                break;
        }
    }

    private void explodeMovableGameObjectOnCoordinate(Coordinate cord) {
        Player playerToRemove = null;
        for (Player player : this.map.players.values()) {
            if (player.coordinate.equals(cord)) {
                playerToRemove = player;
                break;
            }
        }

        if (playerToRemove != null) {
            playerToRemove.killYourSelf();
        }

        Enemy enemyToRemove = null;
        for(Enemy enemy : this.map.enemies) {
            if(enemy.coordinate.equals(cord)) {
                enemyToRemove = enemy;
                break;
            }
        }

        if(enemyToRemove != null) {
            this.map.enemies.remove(enemyToRemove);
        }
    }

    private void checkCollisions(Coordinate currentPosition) {
        explodeMovableGameObjectOnCoordinate(currentPosition);

        for (int i = 0; i < this.explosionSpread[0]; i++) {
            Coordinate diff = new Coordinate(-1 * (i+1), 0);
            explodeGameObjectOnCoordinate(currentPosition.add(diff));
            explodeMovableGameObjectOnCoordinate(currentPosition.add(diff));

        }

        for (int i = 0; i < this.explosionSpread[1]; i++) {
            Coordinate diff = new Coordinate(0, -1 * (i+1));
            explodeGameObjectOnCoordinate(currentPosition.add(diff));
            explodeMovableGameObjectOnCoordinate(currentPosition.add(diff));

        }

        for (int i = 0; i < this.explosionSpread[2]; i++) {
            Coordinate diff = new Coordinate(0, (i+1));
            explodeGameObjectOnCoordinate(currentPosition.add(diff));
            explodeMovableGameObjectOnCoordinate(currentPosition.add(diff));

        }

        for (int i = 0; i < this.explosionSpread[3]; i++) {
            Coordinate diff = new Coordinate((i+1), 0);
            explodeGameObjectOnCoordinate(currentPosition.add(diff));
            explodeMovableGameObjectOnCoordinate(currentPosition.add(diff));

        }
    }

    /**
     * Explodes the bomb.
     * Triggers the explosion of the bomb at the next tick.
     */
    public void Explode() {
        if (this.tick < explosionStartTick) {
            this.tick = explosionStartTick;
        }
    }
    private void explode() {
        int currentExplosionSpread = (tick - explosionStartTick) / explosionSpreadTick;
        // TODO: only calculate spread when its updated
        if (currentExplosionSpread > spreadLength) {
            map.removeGameObject(this);
            return;
        }

        Coordinate position = this.map.GetGameObjectPosition(this);

        int left = explosionSpread[0];
        int up = explosionSpread[1];
        int down = explosionSpread[2];
        int right = explosionSpread[3];

        if (currentExplosionSpread - 1 == left && !explosionSpreadBroken[0]) {
            if (this.map.isCoordinateNotWall(position.add(new Coordinate(-1 * (left+1), 0)))) {
                if (this.map.get(position.add(new Coordinate(-1 * (left+1), 0))) instanceof Box) {
                    explosionSpreadBroken[0] = true;
                }
                left++;
            }
        }

        if (currentExplosionSpread - 1 == right && !explosionSpreadBroken[3]) {
            if (this.map.isCoordinateNotWall(position.add(new Coordinate(right+1, 0)))) {
                if (this.map.get(position.add(new Coordinate(right+1, 0))) instanceof Box) {
                    explosionSpreadBroken[3] = true;
                }
                right++;
            }
        }

        if (currentExplosionSpread - 1 == up && !explosionSpreadBroken[1]) {
            if (this.map.isCoordinateNotWall(position.add(new Coordinate(0, -1 * (up+1))))) {
                if (this.map.get(position.add(new Coordinate(0, -1 * (up+1)))) instanceof Box) {
                    explosionSpreadBroken[1] = true;
                }
                up++;
            }
        }

        if (currentExplosionSpread - 1 == down && !explosionSpreadBroken[2]) {
            if (this.map.isCoordinateNotWall(position.add(new Coordinate(0, down+1)))) {
                if (this.map.get(position.add(new Coordinate(0, down+1))) instanceof Box) {
                    explosionSpreadBroken[2] = true;
                }
                down++;
            }
        }

        this.explosionSpread = new int[] {left, up, down, right};
        this.checkCollisions(position);
    }

    /**
     * Updates the bomb state and triggers explosion if necessary.
     */
    public void update() {
        if (this.owner.powerUpList.getPowerUpProps().detonator) {
            if (tick >= explosionStartTick) {
                tick++;
                explode();
            }
        } else {
            tick++;
            if (tick >= explosionStartTick) {
                explode();
            }
        }
    }

    /**
     * Renders the bomb and its explosion effects on the graphics context.
     *
     * @param g The Graphics object used for rendering.
     * @param x The x-coordinate of the bomb.
     * @param y The y-coordinate of the bomb.
     */
    @Override
    public void render(Graphics g, int x, int y) {
        super.render(g, x, y);
        if (this.tick < explosionStartTick) {
            return;
        }

        Render.RenderGameObjectImage(g, x, y, explosionCenter);

        if (this.tick < explosionStartTick + explosionSpreadTick) {
            return;
        }

        int i = 1;
        while (i < explosionSpread[0]) {
            Render.RenderGameObjectImage(g, x-i, y, explosionX);
            i++;
        }
        if (explosionSpread[0] != 0) {
            Render.RenderGameObjectImage(g, x-i, y, explosionLeft);
        }

        i = 1;
        while (i < explosionSpread[1]) {
            Render.RenderGameObjectImage(g, x, y-i, explosionY);
            i++;

        }
        if (explosionSpread[1] != 0) {
            Render.RenderGameObjectImage(g, x, y-i, explosionUp);
        }

        i = 1;
        while (i < explosionSpread[2]) {
            Render.RenderGameObjectImage(g, x, y+i, explosionY);
            i++;

        }
        if (explosionSpread[2] != 0) {
            Render.RenderGameObjectImage(g, x, y+i, explosionDown);
        }


        i = 1;
        while (i < explosionSpread[3]) {
            Render.RenderGameObjectImage(g, x+i, y, explosionX);
            i++;
        }
        if (explosionSpread[3] != 0) {
            Render.RenderGameObjectImage(g, x+i, y, explosionRight);
        }
    }
}
