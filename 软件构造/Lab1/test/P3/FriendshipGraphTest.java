package P3;

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

		assertEquals(0, graph.addVertex(rachel));
		assertTrue(graph.containsPerson(rachel));
		assertFalse(graph.containsPerson(ross));

		graph.addVertex(ross);
		assertTrue(graph.containsPerson(ross));

		graph.addVertex(ben);
		assertTrue(graph.containsPerson(ben));

		graph.addVertex(kramer);
		assertTrue(graph.containsPerson(kramer));
	}

	/**
	 * Test method Exception for {@link FriendshipGraph#addVertex(Person)}
	 */
	@Test(expected = RuntimeException.class)
	public void addVertexExceptionTest() {
		FriendshipGraph graph = new FriendshipGraph();
		Person rachel = new Person("Rachel");
		assertEquals(0, graph.addVertex(rachel));
		graph.addVertex(rachel);
	}

	/**
	 * Test method for {@link FriendshipGraph#addEdge(Person, Person)}
	 */
	@Test
	public void addEdgeTest() {
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
		assertTrue(graph.containsEdge(rachel, ross));
		assertFalse(graph.containsEdge(ross, rachel));

		graph.addEdge(ross, rachel);
		assertTrue(graph.containsEdge(ross, rachel));

		graph.addEdge(ross, ben);
		assertTrue(graph.containsEdge(ross, ben));

		graph.addEdge(ben, ross);
		assertTrue(graph.containsEdge(ben, ross));

		assertFalse(graph.containsEdge(rachel, kramer));
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
