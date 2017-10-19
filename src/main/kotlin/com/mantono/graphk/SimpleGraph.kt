package com.mantono.graphk

import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class DirectedGraph<T, V>(nodes: Collection<T> = emptySet()): AbstractSimpleGraph<T, V>(nodes) where T: Any, V: DirectedEdge<T, V>
{
	override val directed: Boolean = true

	override fun getWeights(start: T, end: T): List<V>
	{
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun edgesFor(node: T): Set<Edge<T, V>>
	{
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun edgesBetween(node1: T, node2: T): List<Edge<T, V>>
	{
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun isConnected(node: T): Boolean = TODO()
}

class UndirectedGraph<T, V>(nodes: Collection<T> = emptySet()): AbstractSimpleGraph<T, V>(nodes) where T: Any, V: UndirectedEdge<T, V>
{
	override val directed: Boolean = false

	override fun getWeights(start: T, end: T): List<V>
	{
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun edgesFor(node: T): Set<Edge<T, V>>
	{
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun edgesBetween(node1: T, node2: T): List<Edge<T, V>>
	{
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun isConnected(node: T): Boolean = edgeSize(node) > 0
}

abstract class AbstractSimpleGraph<T, V>(nodes: Collection<T>): AbstractGraph<T,V>(nodes), MutableGraph<T, V> where T: Any, V: Edge<T, V>
{
	override val multiGraph: Boolean = false

	override fun edgeSize(node: T): Int = edges[node]?.size ?: 0

	override fun isConnected(start: T, end: T): Boolean
	{
		val edgeSet: Set<Edge<T, V>> = edges[start] ?: return false
		val found: Int =  edgeSet.asSequence()
				.filter { end in it.nodes }
				.count()
		return found > 0
	}

	// These methods are same for multigraphs, single graphs and directed and undirected graphs
	override fun add(node: T): Boolean = nodes.add(node)
	override fun isEmpty(): Boolean = nodes.isEmpty()
	override fun contains(node: T): Boolean = node in nodes
	override fun containsAll(elements: Collection<T>): Boolean = nodes.containsAll(elements)
	override fun iterator(): MutableIterator<T> = nodes.iterator()
	override fun addAll(elements: Collection<T>): Boolean = nodes.addAll(elements)
	override fun removeAll(elements: Collection<T>): Boolean = nodes.removeAll(elements)
	override fun clear()
	{
		nodes.clear()
		edges.clear()
	}
}

/*
@Deprecated("Legacy code")
class Old()
{
	override fun containsAll(c: Collection<T>): Boolean = nodes.containsAll(c)
	override fun isEmpty(): Boolean = nodes.isEmpty()
	override fun contains(e: T): Boolean = nodes.contains(e)
	override fun iterator(): MutableIterator<T> = nodes.iterator()
	override fun addAll(c: Collection<T>): Boolean = nodes.addAll(c)
	override fun removeAll(c: Collection<T>): Boolean = nodes.removeAll(c)

	override val numberOfEdges: Int = edges.size
	override val nodeWithLeastEdges: T
		get() = TODO("not implemented") //T // o change initializer of created properties use File | Settings | File Templates.

	override val allNodes: Set<T> = nodes.toSet()
	override fun edgeSize(e: T): Int = edges[e]?.size ?: 0
	override fun isConnected(e: T): Boolean = edgeSize(e) != 0
	override fun isConnected(start: T, end: T): Boolean = edgesBetween(start, end).isNotEmpty()
	override fun getWeights(start: T, end: T): List<V> = edgesBetween(start, end)?.weight ?: Double.NaN
	override fun edgesBetween(node1: T, node2: T): List<Edge<T, V>> = edges[node1]?.firstOrNull { it.destination == node2 }
	override fun add(data: T): Boolean = nodes.add(data)
	override fun clear()
	{
		edges.clear()
		nodes.clear()
	}

	override fun connect(start: T, end: T, weight: V): Boolean
	{
		if (weight < 0.0 && !allowsNegativeWeights)
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

	private fun addEdge(edge: Edge<T, V>): Boolean
	{
		if(edges[edge.destination] == null)
			edges[edge.destination] = HashSet(3)
		return edges[edge.destination]!!.add(edge)
	}

	override fun remove(data: T): Boolean
	{
		if(nodes.remove(data))
		{
			disconnect(data)
			return true
		}
		return false
	}

	override fun disconnect(start: T, end: T, weight: V): Boolean
	{
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun remove(edge: Edge<T, V>) = edges.remove()

	private fun removeAllEdgesLeadingToNode(data: T)
	{
		for(node in nodes)
		{
			val edgesFromNode: MutableSet<Edge<T>>  = edges[node] ?: HashSet(0)
			val iter: MutableIterator<Edge<T>> = edgesFromNode.iterator()
			while(iter.hasNext())
			{
				val edge = iter.next()
				if (edge.destination == node)
					iter.remove()
			}
		}
	}

	private fun removeAllEdgesLeadingToNode(data: T, )
	{
		for(node in nodes)
		{
			val edgesFromNode: MutableSet<Edge<T>>  = edges[node] ?: HashSet(0)
			val iter: MutableIterator<Edge<T>> = edgesFromNode.iterator()
			while(iter.hasNext())
			{
				val edge = iter.next()
				if (edge.destination == node)
					iter.remove()
			}
		}
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
		return false
	}

	private fun disconnect(node: T)
	{
		if(directed)
		{
			edges.asSequence()
					.map { it.value }
					.flatMap { it.asSequence() }
					.filter { it.source == node || it.destination == node }
					.forEach { disconnect(it.source, it.destination) }
		}
		else
		{
			edgesFor(node).forEach { disconnect(it.destination, it.source) }
		}
	}

	private fun removeEdgeForNode(node: T, edge: Edge<T>): Boolean = edges[node]?.remove(edge) == true

	override fun retainAll(elements: Collection<T>): Boolean
	{
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun edgesFor(node: T): Set<Edge<T, V>> = edges[node] ?: emptySet()
}*/