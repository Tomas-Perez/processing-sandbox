package com.wawey.processing

import edu.austral.starship.base.vector.Vector2
import java.awt.Shape
import sun.reflect.annotation.AnnotationParser.toArray
import java.awt.geom.PathIterator
import java.util.Arrays
import java.util.ArrayList



/**
 *
 * @author Tomas Perez Molina
 */
object ShapeUtil {

    fun getPoints(path: Shape, flatness: Int): Array<DoubleArray> {
        val pointList = ArrayList<DoubleArray>()
        val coords = DoubleArray(6)
        var numSubPaths = 0
        val pi = path.getPathIterator(null, flatness.toDouble())
        while (!pi.isDone) {
            when (pi.currentSegment(coords)) {
                PathIterator.SEG_MOVETO -> {
                    pointList.add(Arrays.copyOf(coords, 2))
                    ++numSubPaths
                }
                PathIterator.SEG_LINETO -> pointList.add(Arrays.copyOf(coords, 2))
                PathIterator.SEG_CLOSE -> {
                    if (numSubPaths > 1) {
                        throw IllegalArgumentException("Path contains multiple subpaths")
                    }
                    return pointList.toTypedArray()
                }
                else -> throw IllegalArgumentException("Path contains curves")
            }
            pi.next()
        }
        throw IllegalArgumentException("Unclosed path")
    }
}