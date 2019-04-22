package com.mantono.graphk.api

/**
 * An edge connect two different nodes, or on some occasions, it goes back to
 * to the same node (circular edge). Some edges are directed (only goes in one direction) while
 * other are undirected (goes in both directions).
 * For example;
 * 1. Directed edge: `A --> B`
 * 2. Undirected edge `A <-> B`
 * 3. Circular edge `A --> A`
 *
 * An edge have a weight (or cost), which is usually represented with a number.
 * Some edges may have a negative weight while others does not, if the [Graph]
 * implementation allows negative weights.
 *
 * The names [first] and [second] does not neccessarily imply any order or
 * direction of the nodes that the edge connects, it is up to each [Graph]
 * implementation to decide how these values should be treated.
 */
interface Edge<T: Any, V: Comparable<V>> {
	/**
	 * The weight of this edge
	 */
	val weight: V
	/**
	 * The first node that this edge connects to
	 */
	val first: T
	/**
	 * The second node that this edge connects to
	 */
	val second: T
}