package com.wawey.processing

import com.wawey.processing.model.vector2D.Vector2Adapter
import com.wawey.processing.model.vector2D.Vector2D
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.awt.Polygon
import java.awt.Rectangle
import java.awt.Shape


/**
 * @author Tomas Perez Molina
 */
class ShapeUtilTest {

    @Nested
    inner class GetPoints {
        @Test
        fun `gets rectangle points`() {
            val rectangle = Rectangle(0, 0, 50, 50)
            val expected = listOf(
                    Vector2Adapter.vector(0, 0),
                    Vector2Adapter.vector(50, 0),
                    Vector2Adapter.vector(50, 50),
                    Vector2Adapter.vector(0, 50),
                    Vector2Adapter.vector(0, 0)
            )

            checkShape(rectangle, expected)
        }

        @Test
        fun `gets rectangular polygon points`() {
            val rectangle = Polygon(intArrayOf(0, 50, 50, 0, 0), intArrayOf(0, 0, 50, 50, 0), 5)
            val expected = listOf(
                    Vector2Adapter.vector(0, 0),
                    Vector2Adapter.vector(50, 0),
                    Vector2Adapter.vector(50, 50),
                    Vector2Adapter.vector(0, 50),
                    Vector2Adapter.vector(0, 0)
            )

            checkShape(rectangle, expected)
        }

        @Test
        fun `get triangle points`() {
            val triangle = Polygon(intArrayOf(0, 50, 0, 0), intArrayOf(0, 0, 50, 0), 4)
            val expected = listOf(
                    Vector2Adapter.vector(0, 0),
                    Vector2Adapter.vector(50, 0),
                    Vector2Adapter.vector(0, 50),
                    Vector2Adapter.vector(0, 0)
            )

            checkShape(triangle, expected)
        }

        @Test
        fun `get points away from origin`() {
            val triangle = Polygon(intArrayOf(25, 50, 25, 25), intArrayOf(25, 25, 50, 25), 4)
            val expected = listOf(
                    Vector2Adapter.vector(25, 25),
                    Vector2Adapter.vector(50, 25),
                    Vector2Adapter.vector(25, 50),
                    Vector2Adapter.vector(25, 25)
            )

            checkShape(triangle, expected)
        }

        private fun checkShape(s: Shape, l: List<Vector2D>) {
            val points = ShapeUtil.getPoints(s, 2)
            assertThat(points).usingElementComparator{ v1, v2 -> Vector2D.compareByXY(v1, v2) }.containsAll(l)
        }
    }
}