package Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * class representing directions with coordinates
 */
public class DirectionVector {
	public static final Coordinate LEFT = new Coordinate(-1, 0);
	public static final Coordinate UP = new Coordinate(0, -1);
	public static final Coordinate RIGHT = new Coordinate(1, 0);
	public static final Coordinate DOWN = new Coordinate(0, 1);

	public static final Coordinate STOP = new Coordinate(0,0);

	/**
	 * array of directions where each direction is a static coordinate defined previously
	 */
	public static final Coordinate[] Directions = {
			LEFT,
			UP,
			RIGHT,
			DOWN
	};
}
