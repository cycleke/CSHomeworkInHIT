package P3.base;

/**
 * Class {@link Board} is an immutable class to describe a board.
 *
 * @author cycleke
 */
public class Board {

	private final int row, column;

	/**
	 * <p>
	 * Abstraction function:
	 * </p>
	 * f(board) = (row, column)
	 *
	 * <p>
	 * Representation invariant:
	 * </p>
	 * Row and column are positive.
	 *
	 * <p>
	 * Safety from rep exposure:
	 * </p>
	 * All fields are private.
	 */

	public Board(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

}
