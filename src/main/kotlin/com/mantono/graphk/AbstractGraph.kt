package com.mantono.graphk

/**
 * An abstract graph implementation that has all the methods that are equal for both directed and undirected graphs
 * as well as for multi graphs and regular graphs.
 */
abstract class AbstractGraph<T, V>(nodes: Collection<T> = HashSet(),
                                   edges: Map<T, Set<Edge<T, V>>> = HashMap(nodes.size, 0.8f)): MutableGraph<T, V> where T: Any, V: Edge<T, V>
{

	protected val nodes: MutableSet<T> = HashSet(nodes)
	protected val edges: EdgeMap<T, V> = edgeMapOf(edges)
	override val size: Int = nodes.size
	override val allEdges: Map<T, Set<Edge<T, V>>> = edges.toMap()
	override val allNodes: Set<T> = nodes.toSet()

	override fun add(node: T): Boolean = nodes.add(node)
	override fun isEmpty(): Boolean = nodes.isEmpty()
	override fun contains(node: T): Boolean = node in nodes
	override fun containsAll(elements: Collection<T>): Boolean = nodes.containsAll(elements)
	override fun iterator(): MutableIterator<T> = nodes.iterator()
	override fun addAll(elements: Collection<T>): Boolean = nodes.addAll(elements)

	override fun removeAll(elements: Collection<T>): Boolean
	{
		return elements.asSequence()
			.map { remove(it) }
			.count { it } > 0
	}

	override fun clear()
	{
		nodes.clear()
		edges.clear()
	}
}