package P3.base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * PositionTest tests {@link Position}
 *
 * @author cycleke
 */
public class PositionTest {

	/**
	 * Test for getters of {@link Position}
	 */
	@Test
	public void testPositionGetters() {
		Position position = new Position(0, 1);
		assertEquals(0, position.getX());
		assertEquals(1, position.getY());
	}

	/**
	 * Test for static method of {@link Position} :
	 * {@link Position#valueOf(int, int)}
	 */
	@Test
	public void testStaticMethod() {
		Position position = Position.valueOf(0, 1);
		assertEquals(0, position.getX());
		assertEquals(1, position.getY());
	}

	/**
	 * Test for {@link Position#hashCode()} and {@link Position#equals(Object)}
	 */
	@Test
	public void testHashCodeAndEquals() {
		Position pos1 = new Position(0, 0);
		Position pos2 = new Position(0, 1);
		Position pos3 = new Position(1, 0);
		Position pos4 = new Position(1, 1);
		Position pos5 = new Position(0, 0);

		assertTrue(pos1.hashCode() == pos5.hashCode());
		assertTrue(pos1.equals(pos1));
		assertTrue(pos1.equals(pos5));
		assertFalse(pos1.equals(pos2));
		assertFalse(pos1.equals(pos3));
		assertFalse(pos1.equals(pos4));
		assertFalse(pos1.equals(null));
		assertFalse(pos1.equals("pos1"));
	}
}
