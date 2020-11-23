package P2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * FriendshipGraphTest
 *
 * Test FriendshipGraph and Person
 */
public class FriendshipGraphTest {

	/**
	 * Test method for {@link FriendshipGraph#addVertex(Person)}
	 */
	@Test
	public void addVertexTest() {
		FriendshipGraph graph = new FriendshipGraph();
 		Person ross = new Person("Ross");
		Person rachel = new Person("Rachel");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");

		assertTrue(graph.addVertex(rachel));
		assertFalse(graph.addVertex(rachel));
		assertTrue(graph.addVertex(ross));
		assertTrue(graph.addVertex(ben));
		assertTrue(graph.addVertex(kramer));
	}

	/**
	 * Test method for {@link FriendshipGraph#getDistance(Person, Person)}
	 */
	@Test
	public void getDistanceTest() {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		Person ross = new Person("Ross");
		Person ben = new Person("Ben");
		Person kramer = new Person("Kramer");

		graph.addVertex(rachel);
		graph.addVertex(ross);
		graph.addVertex(ben);
		graph.addVertex(kramer);

		graph.addEdge(rachel, ross);
		graph.addEdge(ross, rachel);
		graph.addEdge(ross, ben);
		graph.addEdge(ben, ross);

		assertEquals(1, graph.getDistance(rachel, ross));
		assertEquals(2, graph.getDistance(rachel, ben));
		assertEquals(0, graph.getDistance(rachel, rachel));
		assertEquals(-1, graph.getDistance(rachel, kramer));
	}
}
