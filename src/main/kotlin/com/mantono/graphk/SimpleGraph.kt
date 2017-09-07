package com.mantono.graphk

import com.sun.org.apache.xpath.internal.operations.Bool
import java.util.*

class SimpleGraph<T: Any>(nodes: Collection<T> = emptySet(),
                     override val directed: Boolean = false,
                     override val allowsNegativeWeights: Boolean = false,
                     override val multiGraph: Boolean = false): MutableGraph<T>
{
	private val nodes: MutableSet<T> = HashSet(nodes)
	private val edges: MutableMap<T, MutableSet<Edge<T>>> = HashMap(nodes.size, 0.8f)
	override val size: Int = nodes.size
	override val allEdges: Map<T, Set<Edge<T>>>
		get() = edges

	override fun containsAll(c: Collection<T>): Boolean = nodes.containsAll(c)
	override fun isEmpty(): Boolean = nodes.isEmpty()
	override fun contains(e: T): Boolean = nodes.contains(e)
	override fun iterator(): MutableIterator<T> = nodes.iterator()
	override fun addAll(c: Collection<T>): Boolean = nodes.addAll(c)
	override fun removeAll(c: Collection<T>): Boolean = nodes.removeAll(c)

	override val numberOfEdges: Int = edges.size
	override val nodeWithLeastEdges: T
		get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
	override val allNodes: Set<T>
		get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

	override fun edgeSize(e: T): Int = edges[e]?.size ?: 0
	override fun isConnected(e: T): Boolean = edgeSize(e) != 0
	override fun isConnected(start: T, end: T): Boolean = edgeBetween(start, end) != null
	override fun getWeight(start: T, end: T): Double = edgeBetween(start, end)?.weight ?: Double.NaN
	override fun edgeBetween(node1: T, node2: T): Edge<T>? = edges[node1]?.firstOrNull { it.destination == node2 }
	override fun add(data: T): Boolean = nodes.add(data)
	override fun clear()
	{
		edges.clear()
		nodes.clear()
	}

	override fun connect(start: T, end: T, weight: Double): Boolean
	{
		if (weight < 0.0 and !allowsNegativeWeights)
			throw IllegalArgumentException("Trying to add negative weight ($weight), but negative wegihts are not alllowed")
		return when(isConnected(start, end))
		{
			true -> when(multiGraph)
			{
				true -> addEdge(Edge(start, end, weight))
				false -> false
			}
			false -> addEdge(Edge(start, end, weight))
		}
	}

	private fun addEdge(edge: Edge<T>): Boolean
	{
		if(edges[edge.destination] == null)
			edges[edge.destination] = HashSet(3)
		return edges[edge.destination]!!.add(edge)
	}

	override fun remove(data: T): Boolean
	{
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun disconnect(start: T, end: T): Boolean
	{
		edgeBetween(start, end)?.let {
			if(removeEdgeForNode(start, it))
			{
				return when (directed)
				{
					true -> true
					false -> edges[end]?.remove(it.reverse()) == true
				}
			}
		}
	}

	private fun removeEdgeForNode(node: T, edge: Edge<T>): Boolean = edges[node]?.remove(edge) == true

	override fun changeWeight(start: T, end: T, weight: Double): Boolean
	{
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun retainAll(elements: Collection<T>): Boolean
	{
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun edgesFor(node: T): Set<Edge<T>> = edges[node] ?: emptySet()
}