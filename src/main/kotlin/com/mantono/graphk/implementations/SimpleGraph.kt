package com.mantono.graphk.implementations

import com.mantono.graphk.api.MutableGraph
import java.lang.IllegalArgumentException

class UndirectedSimpleGraph<T: Any, V: Comparable<V>>(
	private val edges: MutableMap<T, MutableList<Edge<T, V>>> = HashMap(32)
): MutableGraph<T, V>, Collection<T> by edges.keys {

	override operator fun get(node: T): List<com.mantono.graphk.api.Edge<T, V>> =
		edges.getOrDefault(node, emptyList())

	override fun isConnected(node0: T, node1: T): Boolean =
		get(node0).any { it.second == node1 }

	override fun edges(): Set<com.mantono.graphk.api.Edge<T, V>> =
		edges.values.flatten().toSet()

	override fun add(node: T): Boolean =
		edges.putIfAbsent(node, ArrayList(1)) == null

	override fun connect(node0: T, node1: T, weight: V): Boolean {
		if(isConnected(node0, node1)) {
			return false
		}
		require(node0 != node1) {
			"This graph does not allow circular edges. Tried to create circular edge for $node0"
		}
		connectOneWay(node0, node1, weight)
		connectOneWay(node1, node0, weight)
		return true
	}

	private fun connectOneWay(node0: T, node1: T, weight: V) {
		val edge = Edge<T, V>(node0, node1, weight)
		val nodeEdges: MutableList<Edge<T, V>> = edges.getOrDefault(node0, ArrayList(1))
		check(nodeEdges.add(edge))
		edges.putIfAbsent(node0, nodeEdges)
	}

	override fun remove(node: T): Boolean {
		val nodeEdges: List<Edge<T, V>> = edges.remove(node) ?: return false
		nodeEdges.mapNotNull { edges[it.second] }
	}

	override fun disconnect(node0: T, node1: T): Boolean {
		if(node0 !in edges || node1 !in edges) {
			return false
		}
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	private fun disconnectBothWays(node0: T, node1: T) {
		val nodeEdges: MutableList<Edge<T, V>> = edges[node0] ?: return
		edges[node0]?.removeIf { it.first == node0 && it.second == node1 }
	}
}