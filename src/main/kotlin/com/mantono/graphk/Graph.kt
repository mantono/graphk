package com.mantono.graphk

import com.mantono.RandomAccess

interface Graph<T> : Collection<T>, Set<T>, RandomAccess<T>
{
	override val size: Int
	override operator fun contains(data: T): Boolean
	fun edgeSize(data: T): Int
	fun allowsNegativeWeights(): Boolean
	val isDirected: Boolean
	fun isConnected(node: T): Boolean
	fun isConnected(start: T, end: T): Boolean
	fun getWeight(start: T, end: T): Double
	val allNodes: Set<T>
	fun edgesFor(node: T): List<Edge<T>>
	fun edgeBetween(node1: T, node2: T): Edge<T>
	val numberOfEdges: Int
	val nodeWithLeastEdges: T
	val allEdges: Map<T, List<Edge<T>>>
}

interface MutableGraph<T> : MutableCollection<T>, MutableSet<T>, RandomAccess<T>
{
	override val size: Int
	override fun add(data: T): Boolean
	override fun remove(data: T): Boolean
	override operator fun contains(data: T): Boolean
	fun edgeSize(data: T): Int
	fun allowsNegativeWeights(): Boolean
	val isDirected: Boolean
	fun isConnected(node: T): Boolean
	fun isConnected(start: T, end: T): Boolean
	fun connect(start: T, end: T, weight: Double): Boolean
	fun disconnect(start: T, end: T): Boolean
	fun changeWeight(start: T, end: T, weight: Double): Boolean
	fun getWeight(start: T, end: T): Double
	val allNodes: Set<T>
	fun edgesFor(node: T): List<Edge<T>>
	fun edgeBetween(node1: T, node2: T): Edge<T>
	val numberOfEdges: Int
	override fun clear()
	val nodeWithLeastEdges: T
	val allEdges: Map<T, List<Edge<T>>>
}