package Game.PowerUp.PowerUps;

import Game.PowerUp.PowerUp;
import Game.PowerUp.PowerUpProps;

public class BarricadePowerUp extends PowerUp {
    public BarricadePowerUp () {
        this.lifeLength = -1;
    }

    @Override
    public boolean IsStackAble() {
        return true;
    }

    @Override
    public void effect(PowerUpProps powerUpProps) {
        powerUpProps.barricades += 3;
    }

    @Override
    public String getImageName() {
        return "barricadePowerUp";
    }
}
