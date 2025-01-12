package Game.PowerUp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The PowerUpList class manages a list of power-ups and their effects.
 */
public class PowerUpList {
	public List<PowerUp> getPowerUps() {
		return powerUps;
	}

	private List<PowerUp> powerUps;

	public void setPowerUpProps(PowerUpProps powerUpProps) {
		this.powerUpProps = powerUpProps;
	}

	private PowerUpProps powerUpProps;

	/**
	 * Constructs a new PowerUpList object with an empty list of power-ups and default properties.
	 */
	public PowerUpList() {
		this.powerUps = new ArrayList<>();
		this.powerUpProps = new PowerUpProps();
	}

	private void calculatePowerUpProps() {
		this.powerUpProps = new PowerUpProps();
		for (PowerUp powerUp: powerUps) {
			powerUp.effect(this.powerUpProps);
		}
	}

	/**
	 * Updates the power-up list, removing expired power-ups and recalculating properties if needed.
	 */
	public void update() {
		boolean needPropsUpdate = false;

		ArrayList<PowerUp> powerUpsToRemove = new ArrayList<>();

		for (PowerUp powerUp: powerUps) {
			powerUp.lifeLength -= 1;
			if (powerUp.lifeLength == 0) {
				powerUpsToRemove.add(powerUp);
				needPropsUpdate = true;
			}
		}

		for (PowerUp powerUpToRemove : powerUpsToRemove) {
			this.powerUps.remove(powerUpToRemove);
		}

		if (needPropsUpdate) {
			this.calculatePowerUpProps();
		}
	}

	/**
	 * Adds a power-up to the list, resetting existing power-ups if necessary.
	 *
	 * @param powerUp The power-up to add.
	 */
	public void AddPowerUp(PowerUp powerUp) {
		if (!powerUp.IsStackAble()) {
			Optional<PowerUp> powerUpInList = powerUps.stream().filter(s -> s.getClass() == powerUp.getClass()).findFirst();
			if (powerUpInList.isPresent()) {
				powerUpInList.get().reset();
				return;
			}
		}
		this.powerUps.add(powerUp);
		this.calculatePowerUpProps();
	}

	/**
	 * Gets the properties affected by the power-ups in the list.
	 *
	 * @return The power-up properties.
	 */
	public PowerUpProps getPowerUpProps() {
		return this.powerUpProps;
	}
}
