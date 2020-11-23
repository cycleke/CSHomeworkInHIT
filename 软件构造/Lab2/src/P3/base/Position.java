package P3.base;

/**
 * Class {@link Position} is an immutable class to descrip the position of a
 * piece.
 *
 * @author cycleke
 */
public class Position {
	private final int x, y;

	/**
	 * <p>
	 * Abstraction function:
	 * </p>
	 * f(pos) = (x, y), represent the position of a piece.
	 *
	 * <p>
	 * Representation invariant:
	 * </p>
	 * x and y are nonnegative.
	 *
	 * <p>
	 * Safety from rep exposure:
	 * </p>
	 * All fields are private and final.
	 */

	/**
	 * {@link Position} Constructor
	 *
	 * @param x
	 * @param y
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
		checkRep();
	}

	private void checkRep() {
		assert x >= 0;
		assert y >= 0;
	}

	/**
	 * Get the x coordinate.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Get the y coordinate.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Generate a {@link Position}.
	 *
	 * @param x
	 * @param y
	 */
	public static Position valueOf(int x, int y) {
		return new Position(x, y);
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Position))
			return false;
		Position other = (Position) obj;
		if (x != other.x || y != other.y)
			return false;
		return true;
	}

}
