package Utils.Deserializer;

import Game.PowerUp.PowerUpProps;
import Utils.Coordinate;

public class PlayerDeserializer {
    public CoordinateDeserializer coord;
    public int playerNumber;
    public int wins;
    public PowerUpDeserializer[] powerUpList;
    public PowerUpProps powerUpProps;
    public String key;

    public void setCoord(CoordinateDeserializer coord) {
        this.coord = coord;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setPowerUpList(PowerUpDeserializer[] powerUpList) {
        this.powerUpList = powerUpList;
    }

    public void setPowerUpProps(PowerUpProps powerUpProps) {
        this.powerUpProps = powerUpProps;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
