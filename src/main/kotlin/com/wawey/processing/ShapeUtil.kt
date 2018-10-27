package com.wawey.processing

import com.wawey.processing.model.vector2D.Vector2Adapter
import com.wawey.processing.model.vector2D.Vector2D
import java.awt.Shape
import java.awt.geom.PathIterator


/**
 *
 * @author Tomas Perez Molina
 */
object ShapeUtil {

    fun getPoints(path: Shape, flatness: Int): List<Vector2D> {
        val pointList = mutableListOf<Vector2D>()
        val coords = DoubleArray(2)
        var numSubPaths = 0
        val pi = path.getPathIterator(null, flatness.toDouble())
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

    private fun vectorFromArray(array: DoubleArray): Vector2D =
            Vector2Adapter.vector(array[0].toFloat(), array[1].toFloat())
}