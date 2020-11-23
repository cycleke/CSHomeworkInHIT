package P3.base;

/**
 * Class {@link Piece} is a mutable class to describe a piece.
 *
 * @author cycleke
 */
public class Piece {
	private String name;
	private Position position;

	/**
	 * <p>
	 * Abstraction function:
	 * </p>
	 * f(Piece) = (name, position)
	 *
	 * <p>
	 * Representation invariant:
	 * </p>
	 * Name and position are not null.
	 *
	 * <p>
	 * Safety from rep exposure:
	 * </p>
	 * All fields are private.
	 */

	public Piece(String name, Position position) {
		this.name = name;
		this.position = position;
		checkRep();
	}

	private void checkRep() {
		assert name != null;
		assert position != null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		checkRep();
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
		checkRep();
	}

	public void setPosition(int x, int y) {
		this.position = new Position(x, y);
		checkRep();
	}
}
