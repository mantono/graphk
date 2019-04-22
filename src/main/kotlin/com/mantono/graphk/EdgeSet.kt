package com.mantono.graphk

import com.mantono.graphk.api.Edge

class LayeredEdgeSet<T, V>(initialSize: Int): EdgeSet<T, V> where T: Any, V: Comparable<V> {

	override fun connectedWith(node: T): Set<Edge<T, V>> {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	companion object {
		private val PRIMES = intArrayOf(
			23,
			53,
			97,
			193,
			389,
			769,
			1543,
			3079,
			6151,
			12289,
			24593,
			49157,
			98317,
			196613,
			393241,
			786433,
			1572869,
			3145739,
			6291469,
			12582917,
			25165843,
			50331653,
			100663319,
			201326611,
			402653189,
			805306457,
			1610612741
		)
	}
}

interface EdgeSet<T, V>: MutableSet<Edge<T, V>> where T: Any, V: Comparable<V> {
	fun connectedWith(node: T): Set<Edge<T, V>>
}