package io.github.mamachanko.geometry

import kotlin.comparisons.compareBy

val Set<Vertex>.convexHull: Set<Vertex>
    get() = generateSequence(findNextOnConvexHull(leftMost)) {
        if (it == leftMost) null else findNextOnConvexHull(it)
    }.toSet()

private val Set<Vertex>.leftMost: Vertex
    get() = sortedWith(compareBy { it.x }).first()

private fun Set<Vertex>.findNextOnConvexHull(from: Vertex): Vertex {
    return this.minus(from).find { potentialNext ->
        this.minus(potentialNext).filter { anyOther ->
            orientationOf(from, potentialNext, anyOther) != Orientation.COUNTERCLOCKWISE
        }.size == this.size - 1
    }!!
}

enum class Orientation {
    CLOCKWISE, COUNTERCLOCKWISE, COLINEAR
}

fun orientationOf(a: Vertex, b: Vertex, c: Vertex): Orientation {
    val orientation = (b.y - a.y) * (c.x - b.x) - (b.x - a.x) * (c.y - b.y)
    return when {
        orientation < 0 -> Orientation.CLOCKWISE
        orientation > 0 -> Orientation.COUNTERCLOCKWISE
        else -> Orientation.COLINEAR
    }
}