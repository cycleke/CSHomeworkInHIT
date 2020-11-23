package P3.go;

import P3.base.Action;
import P3.base.Piece;
import P3.base.Player;
import P3.base.Position;

/**
 * Class {@link PutAction} is an immutable class to describe an action to put a
 * piece on the board.
 *
 * @author cycleke
 */
class PutAction implements Action {
	private Position position;

	public PutAction(Position position) {
		this.position = position;
	}

	@Override
	public void doAction(Player player) {
		player.addPiece(new Piece("Stone", position));
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PutAction other = (PutAction) obj;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}
}
