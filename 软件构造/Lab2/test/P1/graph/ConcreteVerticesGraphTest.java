/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 *
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 *
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {

	/*
	 * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
	 */
	@Override
	public Graph<String> emptyInstance() {
		return new ConcreteVerticesGraph<>();
	}

	/*
	 * Testing ConcreteVerticesGraph...
	 */

	/**
	 * tests for ConcreteVerticesGraph.toString()
	 *
	 * Testing strategy for ConcreteVerticesGraph.toString()
	 *  covers:
	 *   empty graph
	 *   graph with vertex
	 *   graph with edge
	 */
	@Test
	public void testConcreteVerticesGraphToString() {
		final Graph<String> graph = emptyInstance();
		assertEquals("", graph.toString());
		graph.add("a");
		graph.add("b");
		assertEquals("Label: a\nTargets:\nSources:\n##########\nLabel: b\nTargets:\nSources:\n##########\n",
				graph.toString());
		graph.set("a", "b", 1);
		assertEquals(
				"Label: a\nTargets:\n<b , 1>\nSources:\n##########\nLabel: b\nTargets:\nSources:\n<a , 1>\n##########\n",
				graph.toString());
	}


	/*
	 * Testing Vertex...
	 */

	@Test
	public void testVertexGetAndSetMethods() {
		final Vertex<String> a = new Vertex<String>("a"), b = new Vertex<String>("b");
		assertEquals("a", a.getLabel());
		assertEquals(Collections.emptyMap(), b.getTargets());
		assertEquals(Collections.emptyMap(), b.getSources());

		a.setSourceEdge("b", 1);
		b.setTargetEdge("a", 1);
		assertTrue(a.connectFrom("b"));
		assertEquals(1, a.getSourceEdgeWeight("b"));
		assertEquals(1, b.getTargetEdgeWeight("a"));
		assertEquals(Stream.of(new AbstractMap.SimpleEntry<>("b", 1))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)), a.getSources());
		assertEquals(Stream.of(new AbstractMap.SimpleEntry<>("a", 1))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)), b.getTargets());

		b.setTargetEdge("a", 0);
		assertFalse(b.connectTo("a"));
	}

	@Test
	public void testVertexToString() {
		final Vertex<String> vertex = new Vertex<String>("vertex");
		vertex.setTargetEdge("target", 1);
		assertEquals("Label: vertex\nTargets:\n<target , 1>\nSources:\n", vertex.toString());
	}
}
