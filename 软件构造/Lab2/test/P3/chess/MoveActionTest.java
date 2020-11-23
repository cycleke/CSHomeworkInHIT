package P3.chess;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import P3.base.Piece;
import P3.base.Player;
import P3.base.Position;

/**
 * MoveActionTest tests {@link MoveAction}
 *
 * @author cycleke
 */
public class MoveActionTest {
	/**
	 * Test for {@link MoveAction#doAction(Player)}
	 */
	@Test
	public void testDoAction_1() {
		MoveAction action = new MoveAction(Position.valueOf(0, 0), Position.valueOf(0, 1));
		Player player = new Player("alice");
		player.addPiece(new Piece("King", Position.valueOf(0, 0)));
		player.addPiece(new Piece("Queen", Position.valueOf(1, 0)));
		action.doAction(player);
		assertNull(player.searchOnPosition(0, 0));
		assertNotNull(player.searchOnPosition(0, 1));
	}

	@Test(expected = RuntimeException.class)
	public void testDoAction_2() {
		MoveAction action = new MoveAction(Position.valueOf(0, 0), Position.valueOf(0, 1));
		Player player = new Player("alice");
		player.addPiece(new Piece("King", Position.valueOf(0, 1)));
		player.addPiece(new Piece("Queen", Position.valueOf(1, 0)));
		action.doAction(player);
	}
}
