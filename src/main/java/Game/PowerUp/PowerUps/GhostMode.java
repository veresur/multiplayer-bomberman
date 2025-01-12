package Game.PowerUp.PowerUps;

import Game.PowerUp.PowerUp;
import Game.PowerUp.PowerUpProps;

public class GhostMode extends PowerUp {
    @Override
    public boolean IsStackAble() {
        return false;
    }

    @Override
    public void effect(PowerUpProps powerUpProps) {
        powerUpProps.ghost = true;
    }

    @Override
    public void reset() {

    }

    @Override
    public String getImageName() {
        return "ghost";
    }
}
