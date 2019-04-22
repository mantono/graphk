package com.mantono.graphk.api

interface MutableGraph<T, V> : Graph<T, V> where T: Any, V: Comparable<V> {

	/**
	 * Add a node without connecting it to any edges. Returns true
	 * if the node was not already present in the graph.
	 */
	fun add(node: T): Boolean

	/**
	 * Remove a node and all edges connecting to or from it.
	 */
	fun remove(node: T): Boolean

	/**
	 * Create an edge between [node0] and [node1] with weight [weight].
	 * Returns true if the edge was successfully created. If an edge already
	 * exists, creating a new edge may or may not be successful depending on
	 * whether the implementation is a multi-graph or not (which allows several
	 * edges between two nodes).
	 */
	fun connect(node0: T, node1: T, weight: V): Boolean

	/**
	 * Remove all edges between [node0] and [node1]. Returns true
	 * if any edge was removed.
	 */
	fun disconnect(node0: T, node1: T): Boolean

	/**
	 * Remove any edge between [node0] and [node1] with the given weight
	 * [weight]. Any edge with a different weight between [node0] and [node1]
	 * will be ignored.
	 * Returns true if any edge were removed.
	 */
	fun disconnect(node0: T, node1: T, weight: V): Boolean
}