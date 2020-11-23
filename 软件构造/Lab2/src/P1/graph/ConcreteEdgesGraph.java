/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 *
 * <p>
 * PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {

	private final Set<L> vertices = new HashSet<>();
	private final List<Edge<L>> edges = new ArrayList<>();

	/**
	 * <p>
	 * Abstraction function:
	 * </p>
	 * f(G) = {e | e is the edge in the graph G}
	 *
	 * <p>
	 * Representation invariant:
	 * </p>
	 * For all the edge in the graph, target and source are vertices in the graph,
	 * the target and source are not same, and weight is positive.
	 *
	 * <p>
	 * Safety from rep exposure:
	 * <p>
	 * All fields are private, and method are also private if possible. Public
	 * methods return copied variable(unabel to access to the private field).
	 * Elements of vertices and edges are immutable.
	 */

	public ConcreteEdgesGraph() {
	}

	/**
	 * Check representation.
	 */
	protected void checkRep() {
		for (Edge<L> e : edges) {
			assert e.getWeight() > 0;
			assert !e.getSource().equals(e.getTarget());
			assert vertices.contains(e.getSource()) && vertices.contains(e.getTarget());
		}
	}

	@Override
	public boolean add(L vertex) {
		boolean res = vertices.add(vertex);
		checkRep();
		return res;
	}

	@Override
	public int set(L source, L target, int weight) {
		if (weight < 0)
			throw new RuntimeException("weight should be positive");
		int oldWeight = 0;
		boolean vertexExist = true;

		if (!vertices.contains(source)) {
			vertices.add(source);
			vertexExist = false;
		}
		if (!vertices.contains(target)) {
			vertices.add(target);
			vertexExist = false;
		}
		if (vertexExist) {
			boolean edgeExist = false;
			for (ListIterator<Edge<L>> it = edges.listIterator(); it.hasNext();) {
				Edge<L> e = it.next();
				if (e.getSource().equals(source) && e.getTarget().equals(target)) {
					oldWeight = e.getWeight();
					if (weight > 0) {
						it.set(new Edge<L>(source, target, weight));
					} else {
						it.remove();
					}
					edgeExist = true;
					break;
				}
			}
			if (!edgeExist && weight > 0)
				edges.add(new Edge<L>(source, target, weight));
		} else if (weight > 0) {
			edges.add(new Edge<L>(source, target, weight));
		}

		checkRep();
		return oldWeight;
	}

	@Override
	public boolean remove(L vertex) {
		if (!vertices.contains(vertex))
			return false;
		vertices.remove(vertex);
		for (int i = 0; i < edges.size(); ++i) {
			Edge<L> e = edges.get(i);
			if (e.getSource().equals(vertex) || e.getTarget().equals(vertex)) {
				edges.remove(i);
				i -= 1;
			}
		}
		checkRep();
		return true;
	}

	@Override
	public Set<L> vertices() {
		return new HashSet<>(vertices);
	}

	@Override
	public Map<L, Integer> sources(L target) {
		Map<L, Integer> resultMap = new HashMap<>();
		for (Edge<L> e : edges) {
			if (e.getTarget().equals(target)) {
				resultMap.put(e.getSource(), e.getWeight());
			}
		}
		return resultMap;
	}

	@Override
	public Map<L, Integer> targets(L source) {
		Map<L, Integer> resultMap = new HashMap<>();
		for (Edge<L> e : edges) {
			if (e.getSource().equals(source)) {
				resultMap.put(e.getTarget(), e.getWeight());
			}
		}
		return resultMap;
	}

	@Override
	public String toString() {
		return "Vertices: " + vertices.toString() + "\nEdges: " + edges.toString();
	}
}

/**
 * This class is internal to the rep of ConcreteEdgesGraph.
 *
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
class Edge<L> {

	private final int weight;
	private final L source, target;

	/**
	 * <p>
	 * Abstraction function:
	 * </p>
	 * f(E) = (source, target, weight), repersent a directed edge
	 *
	 * <p>
	 * Representation invariant:
	 * </P>
	 * source != target and weight is positive
	 *
	 * <p>
	 * Safety from rep exposure:
	 * </p>
	 * All fields are private and final.
	 */

	/**
	 * The constructor of Edge
	 *
	 * @param source the source of the edge
	 * @param target the target of the edge
	 * @param weight the weight of the edge
	 */
	public Edge(L source, L target, int weight) {
		this.source = source;
		this.target = target;
		this.weight = weight;
		checkRep();
	}

	/**
	 * Check representation
	 */
	private void checkRep() {
		assert !source.equals(target);
		assert weight > 0;
	}

	/**
	 * Get the source of the edge.
	 *
	 * @return the source of the edge
	 */
	public L getSource() {
		return this.source;
	}

	/**
	 * Get the target of the edge.
	 *
	 * @return the target of the edge
	 */
	public L getTarget() {
		return this.target;
	}

	/**
	 * Get the weight of the edge.
	 *
	 * @return the weight of the edge
	 */
	public int getWeight() {
		return this.weight;
	}

	@Override
	public boolean equals(Object anObject) {
		if (anObject == this)
			return true;
		if (anObject instanceof Edge<?>) {
			return ((Edge<?>) anObject).getSource().equals(source) && ((Edge<?>) anObject).getTarget().equals(target)
				&& ((Edge<?>) anObject).getWeight() == weight;
		}
		return false;
	}

	@Override
	public String toString() {
		return "(" + source.toString() + " -> " + target.toString() + " : " + weight + ")";
	}
}
