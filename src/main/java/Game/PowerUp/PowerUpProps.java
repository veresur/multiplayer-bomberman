package Game.PowerUp;

/**
 * class representing powerup properties
 */
public class PowerUpProps {
	public void setSpeedIncrease(boolean speedIncrease) {
		this.speedIncrease = speedIncrease;
	}

	public void setBarricades(int barricades) {
		this.barricades = barricades;
	}

	public void setMaxBombCount(int maxBombCount) {
		this.maxBombCount = maxBombCount;
	}

	public void setExtendedExplosionSpread(boolean extendedExplosionSpread) {
		this.extendedExplosionSpread = extendedExplosionSpread;
	}

	public void setGod(boolean god) {
		this.god = god;
	}

	public void setGhost(boolean ghost) {
		this.ghost = ghost;
	}

	public void setDetonator(boolean detonator) {
		this.detonator = detonator;
	}

	public boolean speedIncrease = false;
	public int barricades = 0;
	public int maxBombCount = 1;
	public boolean extendedExplosionSpread = false;
	public boolean god = false;
	public boolean ghost = false;
	public boolean detonator = false;


}
