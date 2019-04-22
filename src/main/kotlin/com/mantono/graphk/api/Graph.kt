package com.mantono.graphk.api

interface Graph<T, V>: Collection<T> where T: Any, V: Comparable<V> {

	/**
	 * Return all edges in the graph
	 */
	fun edges(): Set<Edge<T, V>> = this.asSequence()
		.map { get(it) }
		.flatten()
		.toSet()

	/**
	 * Return true if an edge exists between [node0] and [node1]
	 */
	fun isConnected(node0: T, node1: T): Boolean

	/**
	 * Return the edges for node [node]. If the node is not present in
	 * the graph or it is does not have any edges, an empty list will be
	 * returned.
	 */
	operator fun get(node: T): List<Edge<T, V>>
}