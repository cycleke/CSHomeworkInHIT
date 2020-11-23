package P3.go;

import P3.base.Action;
import P3.base.Player;
import P3.base.Position;

/**
 * Class {@link RemoveAction} is an immutable class to describe an action that
 * removing a piece on position.
 *
 * @author cycleke
 */
public class RemoveAction implements Action {
	private final Position position;

	public RemoveAction(Position position) {
		this.position = position;
	}

	@Override
	public void doAction(Player player) {
		if (player.removePiece(position) == null)
			throw new RuntimeException("Can't find piece on position.");
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
		RemoveAction other = (RemoveAction) obj;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}
}
