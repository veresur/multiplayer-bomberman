package Game.Objects;

import Game.Map;
import Utils.Coordinate;
import Utils.DirectionVector;
import Utils.Props;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Objects;

/**
 * movable game object
 */
public abstract class MovableObject {

    private Dictionary<Coordinate, Image> objectImages;

    protected abstract String getImageName();

    /**
     * direction of the object it is facing
     */
    public Coordinate direction;

    /**
     * map where the game object lives
     */
    protected Map map;

    /**
     * coordinate of the gameobject
     */
    protected Coordinate coordinate;
    public MovableObject(int x, int y, Map map) {
        this.direction = DirectionVector.STOP;
        this.coordinate = new Coordinate(x, y);
        this.map = map;
        this.CreateImages();
    }

    /**
     * creates the images of the movable game object and loads them into the hashtable
     * which will handle the correct image associated to the direction the object is facing
     */
    protected void CreateImages() {
        objectImages = new Hashtable<>();

        ClassLoader classLoader = getClass().getClassLoader();
        String imageName = getImageName();

        URL imageURL = classLoader.getResource("gameobjects/" + imageName + "_u.png");
        if (imageURL != null) {
            objectImages.put(DirectionVector.UP, new ImageIcon(imageURL).getImage());
        }

        imageURL = classLoader.getResource("gameobjects/" + imageName + "_l.png");
        if (imageURL != null) {
            objectImages.put(DirectionVector.LEFT, new ImageIcon(imageURL).getImage());
        }

        imageURL = classLoader.getResource("gameobjects/" + imageName + "_d.png");
        if (imageURL != null) {
            objectImages.put(DirectionVector.DOWN, new ImageIcon(imageURL).getImage());
        }

        imageURL = classLoader.getResource("gameobjects/" + imageName + "_r.png");
        if (imageURL != null) {
            objectImages.put(DirectionVector.RIGHT, new ImageIcon(imageURL).getImage());
        }

        imageURL = classLoader.getResource("gameobjects/" + imageName + "_s.png");
        if (imageURL != null) {
            objectImages.put(DirectionVector.STOP, new ImageIcon(imageURL).getImage());
        }
    }

    /**
     * Override render for movable object for directions
     * @param g Graphics
     * @param x X coordinate
     * @param y Y coordinate
     */
    public void render(Graphics g) {
        g.drawImage(objectImages.get(direction), (this.coordinate.X) * Props.RECT_SIZE, (this.coordinate.Y) * Props.RECT_SIZE, Props.RECT_SIZE, Props.RECT_SIZE, null);
    }

    /**
     * updates the object on game tick
     */
    public void update() {
        return;
    }

    /**
     * @return coordinate of the object
     */
    public Coordinate getPosition() {
        return coordinate;
    }

    public Coordinate getDirection() {
        return this.direction;
    }

    /**
     * @param direction where the object will turn to
     */
    public void setDirection(Coordinate direction) {
        this.direction = direction;
    }

    public void setPosition(Coordinate cord) {
        this.coordinate = cord;
    }
}
