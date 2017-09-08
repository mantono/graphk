package com.mantono.graphk

import java.util.concurrent.Semaphore

data class Edge<T>(val source: T, val destination: T, val distance: Double, val id: String = "", val lock: Semaphore = Semaphore(1)): Comparable<Edge<T>>
{
	override fun equals(obj: Any?): Boolean
	{
		if (obj == null || obj.javaClass != this.javaClass)
			return false
		val other = obj as Edge<T>
		val sameWeight = this.id == other.id
		val sameSource = this.source == other.source
		val sameDestination = this.destination == other.destination
		return sameSource && sameDestination && sameWeight
	}

	override fun hashCode(): Int
	{
		val prime = 17
		var code: Double = prime * distance
		code = code * prime + id.hashCode()
		code = code * prime + (source?.hashCode() ?: 0)
		code = code * prime + (destination?.hashCode() ?: 0)
		return code.toInt()
	}

	override fun toString(): String = "$source -- $distance --> $destination"
	override fun compareTo(other: Edge<T>): Int = java.lang.Double.compare(this.distance, other.distance)

	fun acquire()
	{
		lock.acquire()
	}

	fun release()
	{
		lock.release()
	}

	fun reverse(): Edge<T> = Edge(destination, source, distance, id)
}