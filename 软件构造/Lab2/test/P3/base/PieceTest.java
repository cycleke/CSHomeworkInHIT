package P3.base;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * PieceTest tests {@link Piece}
 *
 * @author cycleke
 */
public class PieceTest {

	/**
	 * Test for the getters and setters of {@link Piece}
	 */
	@Test
	public void testPieceGettersAndSetters() {
		Position pos1 = new Position(0, 0);
		Position pos2 = new Position(0, 1);
		Piece piece = new Piece("King", pos1);
		assertEquals("King", piece.getName());
		assertEquals(pos1, piece.getPosition());
		piece.setName("Queen");
		assertEquals("Queen", piece.getName());
		piece.setPosition(0, 1);
		assertEquals(pos2, piece.getPosition());
		piece.setPosition(pos1);
		assertEquals(pos1, piece.getPosition());
	}
}
