package Utils.Deserializer;

import Utils.Coordinate;

public class GameObjectDeserializer {
    public void setCoord(Coordinate coord) {
        this.coord = coord;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setBorderWall(boolean borderWall) {
        this.borderWall = borderWall;
    }

    public Coordinate coord;
    public String owner;
    public boolean borderWall;

    public void setType(String type) {
        this.type = type;
    }

    public String type;

    public void setName(String name) {
        this.name = name;
    }

    public String name;
}
