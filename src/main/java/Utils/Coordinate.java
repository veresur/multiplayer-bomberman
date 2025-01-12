package Utils;

/**
 * Class representing a 2D coordinate
 */
public class Coordinate {
	/**
	 * horizontal amount representation on the 2D plane
	 * (X coordinate)
	 */
	public int X;
	/**
	 * vertical amount representation on the 2D plane
	 * (Y coordinate)
	 */
	public int Y;

	public Coordinate(int X, int Y) {
		this.X = X;
		this.Y = Y;
	}

	/**
	 * Adds 2 coordinates
	 * @param b other coordinate
	 * @return Sum of this and b coordinate
	 */
	public Coordinate add (Coordinate b) {
		return new Coordinate(this.X + b.X, this.Y + b.Y);
	}

	/**
	 * @param c the coordinate which is used to translate the original coordinate with
	 */
	public void translate (Coordinate c) {
		this.X += c.X;
		this.Y += c.Y;
	}

	/**
	 * Object equals if the X and Y equals
	 * @param obj other coordinate
	 * @return if the objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Coordinate) {
			Coordinate b = (Coordinate) obj;
			return this.X == b.X && this.Y == b.Y;
		}
		throw new RuntimeException("Invalid type");
	}

	/**
	 * Get distance from b coordinate
	 * @param b other coordinate
	 * @return distance
	 */
	public int distance(Coordinate b) {
		int x = Math.abs(this.X - b.X);
		int y = Math.abs(this.Y - b.Y);
		return (int)Math.floor(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
	}

	/**
	 * @return stringified representation of X and Y
	 */
	@Override
	public String toString () {
		return "x: " + this.X + " y: " + this.Y;
	}
}
