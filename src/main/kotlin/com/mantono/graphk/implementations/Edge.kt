package com.mantono.graphk.implementations

data class Edge<T: Any, V: Comparable<V>>(
	override val first: T,
	override val second: T,
	override val weight: V
): com.mantono.graphk.api.Edge<T, V> {
	override fun toString(): String = "$first <-- $weight --> $second"
}