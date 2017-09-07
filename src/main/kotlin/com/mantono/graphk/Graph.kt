package com.mantono.graphk

interface Graph<T> : Collection<T>, Set<T>
{
	override val size: Int
	override operator fun contains(data: T): Boolean

    val numberOfEdges: Int
    val nodeWithLeastEdges: T
    val directed: Boolean
	val multiGraph: Boolean
	val allEdges: Map<T, Set<Edge<T>>>
    val allNodes: Set<T>
    val allowsNegativeWeights: Boolean

	fun edgeSize(data: T): Int
	fun isConnected(node: T): Boolean
	fun isConnected(start: T, end: T): Boolean
	fun getWeight(start: T, end: T): Double
	fun edgesFor(node: T): Set<Edge<T>>
	fun edgeBetween(node1: T, node2: T): Edge<T>?
}

interface MutableGraph<T> : Graph<T>, MutableCollection<T>, MutableSet<T>
{
	override val size: Int
	override fun add(data: T): Boolean
	override fun remove(data: T): Boolean
    override fun clear()
	override operator fun contains(data: T): Boolean

	fun connect(start: T, end: T, weight: Double): Boolean
	fun disconnect(start: T, end: T): Boolean
	fun changeWeight(start: T, end: T, weight: Double): Boolean
}