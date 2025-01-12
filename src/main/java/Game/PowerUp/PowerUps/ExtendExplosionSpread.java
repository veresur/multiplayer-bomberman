package Game.PowerUp.PowerUps;

import Game.PowerUp.PowerUp;
import Game.PowerUp.PowerUpProps;

/**
 * class representing the extended explosion spread of bomb
 */
public class ExtendExplosionSpread extends PowerUp {
    @Override
    public boolean IsStackAble() {
        return false;
    }

    @Override
    public void effect(PowerUpProps powerUpProps) {
        powerUpProps.extendedExplosionSpread = true;
    }

    @Override
    public void reset() {

    }

    @Override
    public String getImageName() {
        return "biggerExplosion";
    }
}
