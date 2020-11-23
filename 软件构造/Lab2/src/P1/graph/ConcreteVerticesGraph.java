/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 *
 * <p>
 * PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {

	private final List<Vertex<L>> vertices = new ArrayList<>();

	/**
	 * <p>
	 * Abstraction function:
	 * </p>
	 * f(G) = {v | v is the vertex in the graph G}
	 *
	 * <p>
	 * Representation invariant:
	 * </p>
	 * All vertices are distinct, and the edges info exist both in sources and targets.
	 *
	 * <p>
	 * Safety from rep exposure:
	 * </p>
	 * All fields are private, and method are also private if possible. Public
	 * methods return copied variable(unabel to access to the private field).
	 * Elements of vertices are immutable.
	 */

	/**
	 * Constructor
	 */
	public ConcreteVerticesGraph() {
	}

	/**
	 * Check representation.
	 */
	protected void checkRep() {
		Map<L, Vertex<L>> map = new HashMap<>();
		for (Vertex<L> v : vertices) {
			assert !map.containsKey(v.getLabel());
			map.put(v.getLabel(), v);
		}
		for (Vertex<L> v : vertices) {
			for (Map.Entry<L, Integer> e : v.getTargets().entrySet()) {
				Vertex<L> target = map.get(e.getKey());
				assert target != null;
				Integer weight = target.getSourceEdgeWeight(v.getLabel());
				assert weight != null;
				assert weight == e.getValue();
			}
			for (Map.Entry<L, Integer> e : v.getSources().entrySet()) {
				Vertex<L> source = map.get(e.getKey());
				assert source != null;
				Integer weight = source.getTargetEdgeWeight(v.getLabel());
				assert weight != null;
				assert weight == e.getValue();
			}
		}
	}

	private Vertex<L> findVertex(L vertex) {
		for (Vertex<L> v : vertices)
			if (v.getLabel().equals(vertex))
				return v;
		return null;
	}

	private Vertex<L> findVertexOrAdd(L vertex) {
		for (Vertex<L> v : vertices)
			if (v.getLabel().equals(vertex))
				return v;
		Vertex<L> v = new Vertex<L>(vertex);
		vertices.add(v);
		return v;
	}

	@Override
	public boolean add(L vertex) {
		for (Vertex<L> v : vertices)
			if (v.getLabel().equals(vertex))
				return false;
		Vertex<L> v = new Vertex<L>(vertex);
		vertices.add(v);
		checkRep();
		return true;
	}

	@Override
	public int set(L source, L target, int weight) {
		if (weight < 0)
			throw new RuntimeException("weight should be positive");
		Vertex<L> sourceVertex = findVertexOrAdd(source);
		Vertex<L> targetVertex = findVertexOrAdd(target);

		int oldWeight = sourceVertex.setTargetEdge(target, weight);
		targetVertex.setSourceEdge(source, weight);

		checkRep();
		return oldWeight;
	}

	@Override
	public boolean remove(L vertex) {
		boolean found = false;
		for (int i = 0; i < vertices.size(); ++i) {
			if (vertices.get(i).getLabel().equals(vertex)) {
				found = true;
				vertices.remove(i);
				break;
			}
		}
		if (!found)
			return false;

		for (Vertex<L> v : vertices) {
			v.setSourceEdge(vertex, 0);
			v.setTargetEdge(vertex, 0);
		}
		checkRep();
		return true;
	}

	@Override
	public Set<L> vertices() {
		Set<L> resultSet = new HashSet<>();
		for (Vertex<L> v : vertices)
			resultSet.add(v.getLabel());
		return resultSet;
	}

	@Override
	public Map<L, Integer> sources(L target) {
		Vertex<L> targetVertex = findVertex(target);
		return (targetVertex == null) ? new HashMap<>() : targetVertex.getSources();
	}

	@Override
	public Map<L, Integer> targets(L source) {
		Vertex<L> sourceVertex = findVertex(source);
		return (sourceVertex == null) ? new HashMap<>() : sourceVertex.getTargets();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Vertex<L> v : vertices) {
			sb.append(v.toString());
			sb.append("##########\n");
		}
		return sb.toString();
	}
}

/**
 * This class is internal to the rep of ConcreteVerticesGraph.
 *
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
class Vertex<L> {

	private final L label;
	private final Map<L, Integer> sources, targets;

	/**
	 * <p>
	 * Abstraction function:
	 * </p>
	 * f(V) = (label, {(source, weight) | edges to V}, {(target, weight) | edges
	 * from V}), repersent a vertex
	 *
	 * <p>
	 * Representation invariant:
	 * </p>
	 * Label is not in sources and targets, and all weights positive.
	 *
	 * <p>
	 * Safety from rep exposure:
	 * </p>
	 * All fields are private and final.
	 */

	/**
	 * Vertex constructor
	 *
	 * @param label the label of the vertex
	 */
	public Vertex(L label) {
		this.label = label;
		this.sources = new HashMap<>();
		this.targets = new HashMap<>();
	}

	/**
	 * Check representation.
	 */
	protected void checkRep() {
		assert !sources.containsKey(label);
		assert !targets.containsKey(label);
		for (Integer weight : sources.values()) {
			assert weight > 0;
		}
		for (Integer weight : targets.values()) {
			assert weight > 0;
		}
	}

	/**
	 * Get the label of the vertex.
	 *
	 * @return the label of the vertex
	 */
	public L getLabel() {
		return label;
	}

	/**
	 * Get the edges from the vertex.
	 *
	 * @return the edges pair (source, weight) from the vertex
	 */
	public Map<L, Integer> getTargets() {
		return new HashMap<>(targets);
	}

	/**
	 * Get the edges to the vertex.
	 *
	 * @return the edges pair (source, weight) to the vertex
	 */
	public Map<L, Integer> getSources() {
		return new HashMap<>(sources);
	}

	/**
	 * Check whether there is an edge from vertex to target.
	 *
	 * @param target the target to check
	 */
	public boolean connectTo(L target) {
		return targets.containsKey(target);
	}

	/**
	 * Check whether there is an edge from source to vertex.
	 *
	 * @param target the target to check
	 */
	public boolean connectFrom(L source) {
		return sources.containsKey(source);
	}

	/**
	 * Get the weight of the edge from this vertex to target.
	 *
	 * @param target the edge's target
	 * @return the weight of the edge (zero if not exist)
	 */
	public int getTargetEdgeWeight(L target) {
		if (targets.containsKey(target))
			return targets.get(target);
		return 0;
	}

	/**
	 * Get the weight of the edge from source to this vertex.
	 *
	 * @param source the edge's source
	 * @return the weight of the edge (zero if not exist)
	 */
	public int getSourceEdgeWeight(L source) {
		if (sources.containsKey(source))
			return sources.get(source);
		return 0;
	}

	/**
	 * Add, change, or remove a weighted directed edge from this vertex to target.
	 * If weight is nonzero, add an edge or update the weight of that edge; vertices
	 * with the given labels are added to the graph if they do not already exist. If
	 * weight is zero, remove the edge if it exists (the graph is not otherwise
	 * modified).
	 *
	 * @param target the edge's target
	 * @param weight the weight of the edge
	 * @return the previous weight of the edge (zero if not exist)
	 */
	public int setTargetEdge(L target, int weight) {
		if (targets.containsKey(target)) {
			int result = (weight > 0) ? targets.replace(target, weight) : targets.remove(target);
			checkRep();
			return result;
		} else {
			if (weight > 0)
				targets.put(target, weight);
			checkRep();
			return 0;
		}
	}

	/**
	 * Add, change, or remove a weighted directed edge from source to this vertex.
	 * If weight is nonzero, add an edge or update the weight of that edge; vertices
	 * with the given labels are added to the graph if they do not already exist. If
	 * weight is zero, remove the edge if it exists (the graph is not otherwise
	 * modified).
	 *
	 * @param source the edge's source
	 * @param weight the weight of the edge
	 * @return the previous weight of the edge (zero if not exist)
	 */
	public int setSourceEdge(L source, int weight) {
		if (sources.containsKey(source)) {
			if (weight > 0)
				return sources.replace(source, weight);
			return sources.remove(source);
		} else {
			if (weight > 0)
				sources.put(source, weight);
			return 0;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Label: " + label.toString() + "\n");
		sb.append("Targets:\n");
		for (Map.Entry<?, ?> e : targets.entrySet()) {
			sb.append("<" + e.getKey().toString()+" , " + e.getValue().toString() + ">\n");
		}
		sb.append("Sources:\n");
		for (Map.Entry<?, ?> e : sources.entrySet()) {
			sb.append("<" + e.getKey().toString() +" , "+ e.getValue().toString() + ">\n");
		}
		return sb.toString();
	}
}
