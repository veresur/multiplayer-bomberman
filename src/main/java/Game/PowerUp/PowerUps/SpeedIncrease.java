package Game.PowerUp.PowerUps;

import Game.PowerUp.PowerUp;
import Game.PowerUp.PowerUpProps;

public class SpeedIncrease extends PowerUp {

    @Override
    public boolean IsStackAble() {
        return false;
    }

    @Override
    public void effect(PowerUpProps powerUpProps) {
        powerUpProps.speedIncrease = true;
    }

    @Override
    public String getImageName() {
        return "speed";
    }
}
