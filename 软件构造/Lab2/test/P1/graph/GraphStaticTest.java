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
 * Tests for static methods of Graph.
 *
 * To facilitate testing multiple implementations of Graph, instance methods are
 * tested in GraphInstanceTest.
 */
public class GraphStaticTest {

	// Testing strategy
	//   empty()
	//     no inputs, only output is empty graph
	//     observe with vertices()

	@Test(expected=AssertionError.class)
	public void testAssertionsEnabled() {
 		assert false; // make sure assertions are enabled with VM argument: -ea
	}

	@Test
	public void testEmptyVerticesEmpty() {
		assertEquals("expected empty() graph to have no vertices",
								 Collections.emptySet(), Graph.empty().vertices());
	}

	/*
	 * test other vertex label types in Problem 3.2
	*/

	/**
	 * Test for {@link graph.Graph#add(Object)}
	 * covers:
	 *  vertex in the graph
	 * 	vertex not in the graph graph
	 */
	@Test
	public void testAdd() {
		Graph<Integer> graph = Graph.empty();
		assertTrue(graph.add(1));
		assertFalse(graph.add(1));
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
		Graph<Integer> graph = Graph.empty();
		graph.add(1);
		graph.add(2);
		assertEquals(0, graph.set(1, 2, 0));
		assertEquals(0, graph.set(1, 2, 1));
		assertEquals(1, graph.set(1, 2, 2));
		assertEquals(2, graph.set(1, 2, 0));
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
		Graph<Integer> graph = Graph.empty();
		assertFalse(graph.remove(1));
		graph.add(1);
		assertTrue(graph.remove(1));
		graph.add(2);
		graph.add(3);
		graph.set(2, 3, 1);
		assertTrue(graph.remove(2));
	}

	/**
	 * Test for {@link graph.Graph#vertices()}
	 * covers:
	 *  an empty graph
	 *  a graph with vertices
	 */
	@Test
	public void testVertices() {
		Graph<Integer> graph = Graph.empty();
		assertEquals(Collections.emptySet(), graph.vertices());
		graph.add(1);
		assertEquals(new HashSet<>(Arrays.asList(1)), graph.vertices());
	}

	/**
	 * Test for {@link graph.Graph#sources(Object)}
	 * covers:
	 *  no edge connected to the target
	 *  positive number of edges connected to the target
	 */
	@Test
	public void testSources() {
		Graph<Integer> graph = Graph.empty();
		graph.add(1);
		graph.add(2);
		graph.add(3);
		assertEquals(Collections.emptyMap(), graph.sources(1));
		graph.set(1, 2, 1);
		graph.set(2, 1, 2);
		graph.set(3, 1, 3);
		assertEquals(Stream.of(new AbstractMap.SimpleEntry<>(2, 2), new AbstractMap.SimpleEntry<>(3, 3))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)), graph.sources(1));
	}

	/**
	 * Test for  {@link graph.Graph#targets(Object)}
	 * covers:
	 *  no edge connected to the source
	 *  positive number of edges connected to the source
	*/
	@Test
	public void testTargerts() {

		Graph<Integer> graph = Graph.empty();
		graph.add(1);
		graph.add(2);
		graph.add(3);
		assertEquals(Collections.emptyMap(), graph.targets(1));
		graph.set(2, 1, 1);
		graph.set(1, 2, 2);
		graph.set(1, 3, 3);
		assertEquals(Stream.of(new AbstractMap.SimpleEntry<>(2, 2), new AbstractMap.SimpleEntry<>(3, 3))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)), graph.targets(1));
	}

}
