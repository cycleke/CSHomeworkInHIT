package P3.base;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Class {@link Player} is a class to store the piece of a player.
 *
 * @author cycleke
 */
public class Player {
	private String name;
	private List<Piece> pieces;

	/**
	 * <p>
	 * Abstraction function:
	 * </p>
	 * f(player) = (name, {pieces of the player})
	 *
	 * <p>
	 * Representation invariant:
	 * </p>
	 * Name and pieces not null.
	 *
	 * <p>
	 * Safety from rep exposure:
	 * </p>
	 * All fields are private.
	 */

	public Player(String name) {
		this.name = name;
		pieces = new LinkedList<>();
		checkRep();
	}

	public Player(Player anotherPlayer) {
		this.name = anotherPlayer.getName();
		this.pieces = anotherPlayer.pieces();
	}

	private void checkRep() {
		assert name != null;
		assert pieces != null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Add a piece for the player
	 *
	 * @param piece the piece to add
	 * @return true if add successfully
	 */
	public boolean addPiece(Piece piece) {
		return pieces.add(piece);
	}

	/**
	 * Remove a piece located in (x, y) for the player
	 *
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the piece removed, nil if not exist
	 */
	public Piece removePiece(int x, int y) {
		return removePiece(Position.valueOf(x, y));
	}

	/**
	 * Remove a piece located in position for the player
	 *
	 * @param position
	 * @return the piece removed, nil if not exist
	 */
	public Piece removePiece(Position position) {
		for (Iterator<Piece> it = pieces.iterator(); it.hasNext();) {
			Piece piece = it.next();
			if (piece.getPosition().equals(position)) {
				it.remove();
				return piece;
			}
		}
		return null;
	}

	/**
	 * Search a piece located in (x, y) for the player
	 *
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the piece found, nil if not exist
	 */
	public Piece searchOnPosition(int x, int y) {
		return searchOnPosition(new Position(x, y));
	}

	/**
	 * Search a piece located in position for the player
	 *
	 * @param position
	 * @return the piece found, nil if not exist
	 */
	public Piece searchOnPosition(Position position) {
		for (Piece piece : pieces)
			if (piece.getPosition().equals(position))
				return piece;
		return null;
	}

	/**
	 * Return the pieces of the player
	 *
	 * @return the pieces of the player
	 */
	public List<Piece> pieces() {
		return new LinkedList<>(pieces);
	}

	/**
	 * Clear the pieces of the player
	 */
	public void clearPieces() {
		pieces.clear();
	}

}
