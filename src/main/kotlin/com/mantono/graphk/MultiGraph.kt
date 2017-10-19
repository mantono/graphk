package com.mantono.graphk

class MultiGraph<T, V>(nodes: Collection<T> = emptySet()): MutableGraph<T, V> where T: Any, V: Edge<T, V>
{
	override val multiGraph: Boolean = false
	override fun containsAll(elements: Collection<T>): Boolean
	{
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun iterator(): MutableIterator<T>
	{
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun removeAll(elements: Collection<T>): Boolean
	{
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override val directed: Boolean
		get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
	override val allEdges: Map<T, Set<Edge<T, V>>>
		get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
	override val allNodes: Set<T>
		get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
	override val allowsNegativeWeights: Boolean
		get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

	override fun nodeWithLeastEdges(): T
	{
		TODO()
	}

	override fun numberOfEdges(): Int
	{
		TODO()
	}

	override fun edgeSize(data: T): Int
	{
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun isConnected(node: T): Boolean
	{
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun isConnected(start: T, end: T): Boolean
	{
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun addAll(elements: Collection<T>): Boolean
	{
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun retainAll(elements: Collection<T>): Boolean
	{
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

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

	override val size: Int
		get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

	override fun add(data: T): Boolean
	{
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun remove(data: T): Boolean
	{
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun clear()
	{
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun contains(data: T): Boolean
	{
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun connect(start: T, end: T, weight: V): Boolean
	{
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun disconnect(start: T, end: T): Boolean
	{
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun disconnect(start: T, end: T, weight: V): Boolean
	{
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun remove(edge: Edge<T, V>)
	{
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun isEmpty(): Boolean
	{
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
}