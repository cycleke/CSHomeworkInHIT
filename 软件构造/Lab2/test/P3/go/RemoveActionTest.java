package P3.go;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

import P3.base.Action;
import P3.base.Player;
import P3.base.Position;
import P3.chess.RemoveAction;

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
		Action putAction = new PutAction(Position.valueOf(0, 0));
		Action removeAction = new RemoveAction(Position.valueOf(0, 0));
		Player player = new Player("alice");
		putAction.doAction(player);
		removeAction.doAction(player);
		assertEquals(Collections.EMPTY_LIST, player.pieces());
	}

	@Test(expected = RuntimeException.class)
	public void testDoAction_2() {
		Action putAction = new PutAction(Position.valueOf(0, 0));
		Action removeAction = new RemoveAction(Position.valueOf(0, 1));
		Player player = new Player("alice");
		putAction.doAction(player);
		removeAction.doAction(player);
	}
}
