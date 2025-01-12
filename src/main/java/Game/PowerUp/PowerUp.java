package Game.PowerUp;

import Game.PowerUp.PowerUps.*;

import java.util.ArrayList;
import java.util.Random;

/**
 * abstract representation of power ups
 */
public abstract class PowerUp {
	/**
	 * @return true if power up is stackable
	 */
	public abstract boolean IsStackAble();

	/**
	 * @param powerUpProps the actual power up
	 */
	public abstract void effect(PowerUpProps powerUpProps);

	/**
	 * length of the power up
	 */
	public int lifeLength;

    /**
     * method used for reseting the power up
     */
    public void reset() {
		this.lifeLength = PowerUp.getInitialLifeLength();
	}

	/**
	 * @return the image's name of the associated power up
	 */
	public abstract String getImageName();

    public PowerUp () {
        this.lifeLength = 50;
    }

    /**
	 * @return a random power up of all the available power ups
	 */
	public static PowerUp getRandomPowerup () {
		ArrayList<PowerUp> powerUpPool = new ArrayList<>();
		powerUpPool.add(new BonusBomb());
		powerUpPool.add(new ExtendExplosionSpread());
		powerUpPool.add(new GhostMode());
		powerUpPool.add(new BarricadePowerUp());
		powerUpPool.add(new GodMode());
		powerUpPool.add(new Detonator());
		powerUpPool.add(new SpeedIncrease());

		return powerUpPool.get(new Random().nextInt(powerUpPool.size()));
	}

	public static int getInitialLifeLength() {
		return 100;
	}
}