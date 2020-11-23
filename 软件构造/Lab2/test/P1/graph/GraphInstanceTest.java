/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 *
 * <p>
 * PS2 instructions: you MUST NOT add constructors, fields, or non-@Test methods
 * to this class, or change the spec of {@link #emptyInstance()}. Your tests
 * MUST only obtain Graph instances by calling emptyInstance(). Your tests MUST
 * NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {

	/**
	 * Testing strategy
	 *
	 * For {@link graph.Graph#add(Object)}
	 * covers:.
	 *  vertex in the graph
	 * 	vertex not in the graph graph
	 *
	 * For {@link graph.Graph#set(Object, Object, int)}
	 * covers:
	 *  set edge exist with weigth zero
	 *  set edge existing with positive weigth
	 *  set edge not existing with weigth zero
	 *  set edge not existing with positive weigth
	 *
	 * For {@link graph.Graph#remove(Object)}
	 * covers:
	 *  remove a vertex existing with edges
	 *  remove a vertex existing without edges
	 *  remove a vertex not existing
	 *
	 * For {@link graph.Graph#vertices()}
	 * covers:
	 *  an empty graph
	 *  a graph with vertices
	 *
	 * For {@link graph.Graph#sources(Object)}
	 * covers:
	 *  no edge connected to the target
	 *  positive number of edges connected to the target
	 *
	 * For {@link graph.Graph#targets(Object)}
	 * covers:
	 *  no edge connected to the source
	 *  positive number of edges connected to the source
	 */

	/**
	 * Overridden by implementation-specific test classes.
	 *
	 * @return a new empty graph of the particular implementation being tested
	 */
	public abstract Graph<String> emptyInstance();

	@Test(expected = AssertionError.class)
	public void testAssertionsEnabled() {
		assert false; // make sure assertions are enabled with VM argument: -ea
	}

	@Test
	public void testInitialVerticesEmpty() {
		assertEquals("expected new graph to have no vertices", Collections.emptySet(), emptyInstance().vertices());
	}

	/**
	 * Test for {@link graph.Graph#add(Object)}
	 * covers:
	 *  vertex in the graph
	 * 	vertex not in the graph graph
	 */
	@Test
	public void testAdd() {
		Graph<String> graph = emptyInstance();
		assertTrue(graph.add("a"));
		assertFalse(graph.add("a"));
	}

	/**
	 * Test for {@link graph.Graph#set(Object, Object, int)}
	 * covers:
	 *  set edge existing with weigth zero
	 *  set edge existing with positive weigth
	 *  set edge not existing with weigth zero
	 *  set edge not existing with positive weigth
	 */
	@Test
	public void testSet() {
		Graph<String> graph = emptyInstance();
		graph.add("a");
		graph.add("b");
		assertEquals(0, graph.set("a", "b", 0));
		assertEquals(0, graph.set("a", "b", 1));
		assertEquals(1, graph.set("a", "b", 2));
		assertEquals(2, graph.set("a", "b", 0));
	}

	/**
	 * Test for {@link graph.Graph#remove(Object)}
	 * covers:
	 *  remove a vertex existing with edges
	 *  remove a vertex existing without edges
	 *  remove a vertex not existing
	 */
	@Test
	public void testRemove() {
		Graph<String> graph = emptyInstance();
		assertFalse(graph.remove("a"));
		graph.add("a");
		assertTrue(graph.remove("a"));
		graph.add("b");
		graph.add("c");
		graph.set("b", "c", 1);
		assertTrue(graph.remove("b"));
	}

	/**
	 * Test for {@link graph.Graph#vertices()}
	 * covers:
	 *  an empty graph
	 *  a graph with vertices
	 */
	@Test
	public void testVertices() {
		Graph<String> graph = emptyInstance();
		assertEquals(Collections.emptySet(), graph.vertices());
		graph.add("a");
		assertEquals(new HashSet<>(Arrays.asList("a")), graph.vertices());
	}

	/**
	 * Test for {@link graph.Graph#sources(Object)}
	 * covers:
	 *  no edge connected to the target
	 *  positive number of edges connected to the target
	 */
	@Test
	public void testSources() {
		Graph<String> graph = emptyInstance();
		graph.add("a");
		graph.add("b");
		graph.add("c");
		assertEquals(Collections.emptyMap(), graph.sources("a"));
		graph.set("a", "b", 1);
		graph.set("b", "a", 2);
		graph.set("c", "a", 3);
		assertEquals(Stream.of(new AbstractMap.SimpleEntry<>("b", 2), new AbstractMap.SimpleEntry<>("c", 3))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)), graph.sources("a"));
	}

	/**
	 * Test for  {@link graph.Graph#targets(Object)}
	 * covers:
	 *  no edge connected to the source
	 *  positive number of edges connected to the source
	*/
	@Test
	public void testTargerts() {
		Graph<String> graph = emptyInstance();
		graph.add("a");
		graph.add("b");
		graph.add("c");
		assertEquals(Collections.emptyMap(), graph.targets("a"));
		graph.set("b", "a", 1);
		graph.set("a", "b", 2);
		graph.set("a", "c", 3);
		assertEquals(Stream.of(new AbstractMap.SimpleEntry<>("b", 2), new AbstractMap.SimpleEntry<>("c", 3))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)), graph.targets("a"));
	}
}
