package P3.chess;

import P3.base.Action;
import P3.base.Piece;
import P3.base.Player;
import P3.base.Position;

/**
 * Class {@link MoveAction} is an immutable class to describe an action that
 * moving a piece from start to goal.
 *
 * @author cycleke
 */
public class MoveAction implements Action {
	private final Position start, goal;

	public MoveAction( Position start,  Position goal) {
		this.start = start;
		this.goal = goal;
	}

	@Override
	public void doAction(Player player) {
		 Piece piece = player.searchOnPosition(start);
		if (piece == null)
			throw new RuntimeException("Can't find piece on start.");
		piece.setPosition(goal);
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 1;
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + ((goal == null) ? 0 : goal.hashCode());
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
		MoveAction other = (MoveAction) obj;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		else if (!goal.equals(other.goal))
			return false;
		return true;
	}
}
