package P3.chess;

import P3.base.Action;
import P3.base.Piece;
import P3.base.Player;
import P3.base.Position;

/**
 * Class {@link PromotionAction} is an immutable class to describe an action
 * that promoting a pawn.
 *
 * @author cycleke
 */
public class PromotionAction implements Action {
	private final Position position;

	public PromotionAction(Position position) {
		this.position = position;
	}

	@Override
	public void doAction(Player player) {
		Piece piece = player.searchOnPosition(position);
		if (piece == null)
			throw new RuntimeException("Can't find piece on position.");
		if (!piece.getName().equals("Pawn"))
			throw new RuntimeException("That piece is not a pawn.");
		piece.setName("Queen");
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
		PromotionAction other = (PromotionAction) obj;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}
}
