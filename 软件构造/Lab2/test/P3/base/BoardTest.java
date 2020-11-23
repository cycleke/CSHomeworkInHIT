package P3.base;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * BoardTest tests {@link Board}
 *
 * @author cycleke
 */
public class BoardTest {

	/**
	 * Test for getters of {@link Board}
	 */
	@Test
	public void testBoardGetters() {
		Board board = new Board(8, 9);
		assertEquals(8, board.getRow());
		assertEquals(9, board.getColumn());
	}
}
