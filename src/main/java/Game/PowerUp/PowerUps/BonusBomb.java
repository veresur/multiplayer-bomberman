package Game.PowerUp.PowerUps;

import Game.PowerUp.PowerUp;
import Game.PowerUp.PowerUpProps;

/**
 * power up representation of extra bomb when picked up by player
 */
public class BonusBomb extends PowerUp {
    public BonusBomb () {
        this.lifeLength = -1;
    }

    @Override
    public boolean IsStackAble() {
        return true;
    }

    @Override
    public void effect(PowerUpProps powerUpProps) {
        powerUpProps.maxBombCount += 1;
    }

    @Override
    public String getImageName() {
        return "addBomb";
    }
}
