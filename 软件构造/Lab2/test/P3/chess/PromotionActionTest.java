package P3.chess;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import P3.base.Action;
import P3.base.Piece;
import P3.base.Player;
import P3.base.Position;

/**
 * PromotionActionTest test {@link PromotionAction}
 */
public class PromotionActionTest {
	/**
	 * Test for {@link PromotionAction#doAction(Player)}
	 */
	@Test
	public void testDoAction_1() {
		Action action = new PromotionAction(Position.valueOf(0, 0));
		Player player = new Player("alice");
		player.addPiece(new Piece("Pawn", Position.valueOf(0, 0)));
		action.doAction(player);
		Piece piece = player.searchOnPosition(0, 0);
		assertEquals("Queen", piece.getName());
	}

	@Test(expected = RuntimeException.class)
	public void testDoAction_2() {
		Action action = new PromotionAction(Position.valueOf(0, 0));
		Player player = new Player("alice");
		player.addPiece(new Piece("Pawn", Position.valueOf(0, 1)));
		action.doAction(player);
	}

	@Test(expected = RuntimeException.class)
	public void testDoAction_3() {
		Action action = new PromotionAction(Position.valueOf(0, 0));
		Player player = new Player("alice");
		player.addPiece(new Piece("Rook", Position.valueOf(0, 0)));
		action.doAction(player);
	}
}
