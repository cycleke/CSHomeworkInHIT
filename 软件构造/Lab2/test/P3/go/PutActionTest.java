package P3.go;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import P3.base.Action;
import P3.base.Player;
import P3.base.Position;

/**
 * PutActionTest tests {@link PutAction}
 *
 * @author cycleke
 */
public class PutActionTest {

	/**
	 * Test for {@link PutAction#doAction(Player)}
	 */
	@Test
	public void testDoAction() {
		Action action = new PutAction(Position.valueOf(0, 0));
		Player player = new Player("alice");
		action.doAction(player);
		assertEquals(1, player.pieces().size());
	}
}
