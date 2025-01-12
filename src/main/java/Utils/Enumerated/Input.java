package Utils.Enumerated;

/**
 * Input enum representing available user actions
 */
public enum Input {
	UP,
	DOWN,
	LEFT,
	RIGHT,
	BOMB,
	BARRICADE,
	STOP;

	/**
	 * @param input of the user
	 * @return if input was movement request
	 */
	public static boolean isDirection(Input input) {
		switch (input) {
			case UP:
			case DOWN:
			case LEFT:
			case RIGHT:
				return true;
			default:
				return false;
		}
	}
}
