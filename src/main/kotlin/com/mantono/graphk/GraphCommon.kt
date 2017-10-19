package com.mantono.graphk

fun numberOfEdges(): Int
{
	val divideBy: Int = if(directed) 1 else 2
	val total: Int = edges.values.asSequence()
			.flatMap { it.asSequence() }
			.count()
	return total / divideBy
}