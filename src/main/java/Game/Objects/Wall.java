package Game.Objects;

/**
 * wall representation of gameobject
 */
public class Wall extends GameObject {
	@Override
	protected String getImageName() {
		return "wall";
	}

	public boolean borderWall = false;

	public Wall() {

	}
	public Wall(boolean borderWall) {
		this.borderWall = borderWall;
	}
}
