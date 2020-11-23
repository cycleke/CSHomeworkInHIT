/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 *
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 *
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {

	/*
	 * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
	 */
	@Override
	public Graph<String> emptyInstance() {
		return new ConcreteEdgesGraph<>();
	}

	/*
	 * Testing ConcreteEdgesGraph...
	 */

	/**
	 * tests for {@link graph.ConcreteEdgesGraph#toString}
	 *
	 * Testing strategy for ConcreteEdgesGraph.toString()
	 *  covers:
	 *   empty graph
	 *   graph with vertex
	 *   graph with edge
	 */
	@Test
	public void testConcreteEdgesGraphToString() {
		Graph<String> graph = emptyInstance();
		assertEquals("Vertices: []\nEdges: []", graph.toString());
		graph.add("a");
		graph.add("b");
		assertEquals("Vertices: [a, b]\nEdges: []", graph.toString());
		graph.set("a", "b", 1);
		assertEquals("Vertices: [a, b]\nEdges: [(a -> b : 1)]", graph.toString());
	}


	/*
	 * Testing Edge...
	 */

	@Test
	public void testEdgeGetMethods() {
		Edge<String> edge = new Edge<>("a", "b", 1);
		assertEquals("a", edge.getSource());
		assertEquals("b", edge.getTarget());
		assertEquals(1, edge.getWeight());
	}

	@Test
	public void testEdgeToString() {
		Edge<String> edge = new Edge<>("a", "b", 1);
		assertEquals("(a -> b : 1)", edge.toString());
	}
}
