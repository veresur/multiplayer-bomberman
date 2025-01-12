package Game.PowerUp.PowerUps;

import Game.PowerUp.PowerUp;
import Game.PowerUp.PowerUpProps;

public class Detonator extends PowerUp {
    public Detonator () {
        this.lifeLength = -1;
    }

    @Override
    public boolean IsStackAble() {
        return false;
    }

    @Override
    public void effect(PowerUpProps powerUpProps) {
        powerUpProps.detonator = true;
    }

    @Override
    public String getImageName() {
        return "detonator";
    }
}
