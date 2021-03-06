package com.wawey.processing.view.model;

import com.wawey.processing.util.getPoints
import com.wawey.processing.model.entity.bullet.BulletState
import com.wawey.processing.view.DrawColors
import com.wawey.processing.view.Drawable
import com.wawey.processing.view.Plane
import java.awt.Color
import java.awt.Shape

/**
 * @author Tomas Perez Molina
 */
class BulletView(shape: Shape, private val state: BulletState, private val color: Color): Drawable {
    private val points = shape.getPoints()

    override fun draw(plane: Plane) {
        plane.setDrawColors(DrawColors(fill = color, borderWidth = 0))
        plane.polygon(points, state.position, state.heading)
    }

    override fun isActive(): Boolean = !state.destroyed
}
