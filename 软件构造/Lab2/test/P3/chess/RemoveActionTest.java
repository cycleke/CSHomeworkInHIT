package P3.chess;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import P3.base.Action;
import P3.base.Piece;
import P3.base.Player;
import P3.base.Position;

/**
 * RemoveActionTest tests {@link RemoveAction}
 *
 * @author cycleke
 */
public class RemoveActionTest {
	/**
	 * Test for {@link RemoveAction#doAction(Player)}
	 */
	@Test
	public void testDoAction_1() {
		Action action = new RemoveAction(Position.valueOf(0, 0));
		Player player = new Player("alice");
		player.addPiece(new Piece("Pawn", Position.valueOf(0, 0)));
		player.addPiece(new Piece("Pawn", Position.valueOf(0, 1)));
		action.doAction(player);
		assertNull(player.searchOnPosition(0, 0));
		assertNotNull(player.searchOnPosition(0, 1));
	}

	@Test(expected = RuntimeException.class)
	public void testDoAction_2() {
		Action action = new RemoveAction(Position.valueOf(0, 0));
		Player player = new Player("alice");
		player.addPiece(new Piece("Pawn", Position.valueOf(0, 0)));
		action.doAction(player);
		assertNull(player.searchOnPosition(0, 0));
		action.doAction(player);
	}
}
