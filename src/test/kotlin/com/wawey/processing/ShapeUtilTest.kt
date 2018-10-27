package com.wawey.processing

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.awt.Rectangle


/**
 * @author Tomas Perez Molina
 */
class ShapeUtilTest {

    @Nested
    inner class GetPoints {
        @Test
        fun `gets rectangle points`() {
            val rectangle = Rectangle(0, 0, 50, 50)
            val points = ShapeUtil.getPoints(rectangle, 0)
            points.distinct().forEach {
                println(it)
            }
        }
    }

}