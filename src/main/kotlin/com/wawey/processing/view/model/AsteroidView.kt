package com.wawey.processing.view.model

import com.wawey.processing.util.getPoints
import com.wawey.processing.model.entity.asteroid.AsteroidState
import com.wawey.processing.view.DrawColors
import com.wawey.processing.view.Drawable
import com.wawey.processing.view.Plane
import java.awt.Color
import java.awt.Shape

/**
 *
 * @author Tomas Perez Molina
 */
class AsteroidView(shape: Shape, private val state: AsteroidState, private val color: Color): Drawable {
    private val points = shape.getPoints()

    override fun draw(plane: Plane) {
        plane.setDrawColors(DrawColors(fill = Color.BLACK, border = color, borderWidth = 2))
        plane.polygon(points, state.position, state.heading)
    }

    override fun isActive(): Boolean = !state.destroyed
}