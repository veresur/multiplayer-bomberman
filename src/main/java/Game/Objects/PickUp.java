package Game.Objects;

import Game.PowerUp.PowerUp;

/**
 * physical power up representation of gameobject
 */
public class PickUp extends GameObject {
    private final PowerUp powerUp;
    public PickUp(PowerUp powerUp) {
        super(powerUp.getImageName());
        this.powerUp = powerUp;
    }

    public PowerUp getPowerUp() {
        return powerUp;
    }
}
