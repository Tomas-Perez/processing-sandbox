package com.wawey.processing

import com.wawey.processing.model.vector2D.Vector2Adapter
import com.wawey.processing.model.vector2D.Vector2D
import java.awt.Shape
import java.awt.geom.PathIterator
import kotlin.random.Random

/**
 *
 * @author Tomas Perez Molina
 */

fun IntRange.random() = Random.nextInt(start, endInclusive)

fun IntRange.mapTo(outputRange: IntRange): (Int) -> Int {
    val slope: Float = (outputRange.endInclusive.toFloat() - outputRange.start.toFloat()) / (endInclusive.toFloat() - start.toFloat())
    return { input -> (outputRange.start + slope * (input - start)).toInt() }
}

fun Shape.getPoints(): List<Vector2D> {
    fun vectorFromArray(array: DoubleArray): Vector2D =
            Vector2Adapter.vector(array[0].toFloat(), array[1].toFloat())

    val pointList = mutableListOf<Vector2D>()
    val coords = DoubleArray(2)
    var numSubPaths = 0
    val pi = getPathIterator(null, 0.0)
    while (!pi.isDone) {
        when (pi.currentSegment(coords)) {
            PathIterator.SEG_MOVETO -> {
                pointList.add(vectorFromArray(coords))
                if (numSubPaths >= 1) throw IllegalArgumentException("Path contains multiple subpaths")
                else ++numSubPaths
            }
            PathIterator.SEG_LINETO -> pointList.add(vectorFromArray(coords))
            PathIterator.SEG_CLOSE -> return pointList
        }
        pi.next()
    }
    throw IllegalArgumentException("Unclosed path")
}

