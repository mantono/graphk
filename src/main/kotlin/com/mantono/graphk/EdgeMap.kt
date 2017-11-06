package com.mantono.graphk

typealias EdgeMap<T, V> = MutableMap<T, MutableSet<Edge<T, V>>>

operator fun <T, V> EdgeMap<T, V>.get(key: T): MutableSet<Edge<T, V>> where T: Any, V: Comparable<V>
{
	return this[key] ?: HashSet<Edge<T, V>>().also { this[key] = it }
}

fun <T, V> edgeMapOf(edges: Map<T, Set<Edge<T, V>>>): EdgeMap<T, V> where T: Any, V: Comparable<V>
{
	return edges
		.map { it.key to it.value.toMutableSet() }
		.toMap()
		.toMutableMap()
}