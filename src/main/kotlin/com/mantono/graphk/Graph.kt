package com.mantono.graphk

interface Graph<T, V: Comparable<V>> : Collection<T>, Set<T> where T: Any
{
	override val size: Int
	override operator fun contains(data: T): Boolean

    val directed: Boolean
	val multiGraph: Boolean
	val allEdges: Map<T, Set<Edge<T, V>>>
    val allNodes: Set<T>

	fun edgeSize(node: T): Int
	fun isConnected(node: T): Boolean
	fun isConnected(start: T, end: T): Boolean
	fun getWeights(start: T, end: T): List<V>
	fun edgesFor(node: T): Set<Edge<T, V>>
	fun edgesBetween(node1: T, node2: T): List<Edge<T, V>>
}

interface MutableGraph<T, E: Comparable<E>> : Graph<T, E>, MutableCollection<T>, MutableSet<T> where T: Any
{
	override fun add(node: T): Boolean
	override fun remove(node: T): Boolean
    override fun clear()

	fun connect(start: T, end: T, weight: E): Boolean
	fun disconnect(start: T, end: T): Boolean
	fun disconnect(start: T, end: T, weight: E): Boolean
	fun disconnect(edge: Edge<T, E>): Boolean
}