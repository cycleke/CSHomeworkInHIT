package P3.base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collections;

import org.junit.Test;

/**
 * PlayerTest tests {@link Player}
 *
 * @author cycleke
 */
public class PlayerTest {

	/**
	 * Test for getters and setters of {@link Player}
	 */
	@Test
	public void testPlayerGettersAndSetters() {
		Player player = new Player("alice");
		assertEquals("alice", player.getName());
		player.setName("bob");
		assertEquals("bob", player.getName());
	}

	/**
	 * Test for {@link Player#addPiece(Piece)}
	 */
	@Test
	public void testAddPiece() {
		Player player = new Player("alice");
		assertEquals(0, player.pieces().size());
		assertTrue(player.addPiece(new Piece("King", Position.valueOf(0, 0))));
		assertTrue(player.addPiece(new Piece("Queen", Position.valueOf(0, 1))));
		assertEquals(2, player.pieces().size());
	}

	/**
	 * Test for {@link Player#removePiece(Position)} and
	 * {@link Player#removePiece(int, int)}
	 */
	@Test
	public void testRemovePiece() {
		Player player = new Player("alice");
		player.addPiece(new Piece("King", Position.valueOf(0, 0)));
		player.addPiece(new Piece("Queen", Position.valueOf(0, 1)));

		Position position = new Position(0, 0);
		Piece piece = player.removePiece(position);
		assertEquals(position, piece.getPosition());

		position = new Position(0, 1);
		piece = player.removePiece(0, 1);
		assertEquals(position, piece.getPosition());

		position = new Position(1, 1);
		assertNull(player.removePiece(1, 0));
		assertNull(player.removePiece(position));
	}

	/**
	 * Test for {@link Player#searchOnPosition(Position)} and
	 * {@link Player#searchOnPosition(int, int)}
	 */
	@Test
	public void testSearchOnPosition() {
		Player player = new Player("alice");
		player.addPiece(new Piece("King", Position.valueOf(0, 0)));
		player.addPiece(new Piece("Queen", Position.valueOf(0, 1)));

		Position position = new Position(0, 0);
		Piece piece = player.searchOnPosition(position);
		assertEquals(position, piece.getPosition());

		position = new Position(0, 1);
		piece = player.searchOnPosition(0, 1);
		assertEquals(position, piece.getPosition());

		position = new Position(1, 1);
		assertNull(player.searchOnPosition(1, 0));
		assertNull(player.searchOnPosition(position));
	}

	/**
	 * Test for {@link Player#pieces()}
	 */
	@Test
	public void testPieces() {
		Player player = new Player("alice");
		assertEquals(Collections.EMPTY_LIST, player.pieces());
		Piece king = new Piece("King", Position.valueOf(0, 0)), queen = new Piece("Queen", Position.valueOf(0, 1)),
				rook = new Piece("Rook", Position.valueOf(0, 2));
		player.addPiece(king);
		player.addPiece(queen);

		assertTrue(player.pieces().contains(king));
		assertTrue(player.pieces().contains(queen));
		assertFalse(player.pieces().contains(rook));

		player.removePiece(0, 0);
		assertFalse(player.pieces().contains(king));
		assertTrue(player.pieces().contains(queen));
		assertFalse(player.pieces().contains(rook));
	}

}
