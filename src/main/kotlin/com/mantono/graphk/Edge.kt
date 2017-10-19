package com.mantono.graphk

sealed class Edge<T, V>: Comparable<Edge<T, V>> where V: Any, V: Comparable<V>, T: Any
{
	abstract val nodes: Set<T>
	abstract val weight: V
	override fun compareTo(other: Edge<T, V>): Int = weight.compareTo(other.weight)
	override fun hashCode(): Int
	{
		val prime = 17
		var code: Int = prime * weight.hashCode()
		code *= prime * nodes.first().hashCode()
		code *= prime * nodes.last().hashCode()
		return code
	}
}

data class DirectedEdge<T : Any, V: Comparable<V>>(val start: T, val end: T, override val weight: V): Edge<T, V>()
{
	override val nodes: Set<T> = setOf(start, end)
	constructor(nodes: Pair<T, T>, weight: V): this(nodes.first, nodes.second, weight)

	override fun equals(obj: Any?): Boolean
	{
		if (obj == null || obj.javaClass != this.javaClass)
			return false
		val other = obj as DirectedEdge<T, V>
		val sameWeight = this.weight == other.weight
		val sameSource = this.start == other.start
		val sameDestination = this.end == other.end
		return sameSource && sameDestination && sameWeight
	}

	override fun toString(): String = "$start -- $weight --> $end"
}

data class UndirectedEdge<T : Any, V: Comparable<V>>(override val nodes: Set<T>, override val weight: V): Edge<T, V>()
{
	constructor(node1: T, node2: T, weight: V): this(setOf(node1, node2), weight)
	constructor(nodes: Collection<T>, weight: V): this(nodes.toSet(), weight)
	constructor(nodes: Pair<T, T>, weight: V): this(nodes.first, nodes.second, weight)

	init
	{
		if(nodes.size != 2)
			throw IllegalArgumentException("An edge can only be between 2 nodes, but got ${nodes.size} nodes")
	}

	override fun equals(obj: Any?): Boolean
	{
		if (obj == null || obj.javaClass != this.javaClass)
			return false
		val other = obj as UndirectedEdge<T, V>
		val sameWeight = this.weight == other.weight
		val sameNodes = this.nodes == other.nodes
		return sameNodes && sameWeight
	}

	override fun toString(): String = "${nodes.first()} <-- $weight --> ${nodes.last()}"
}