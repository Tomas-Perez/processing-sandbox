package com.wawey.processing.view.model

import com.wawey.processing.ShapeUtil
import com.wawey.processing.model.ShipState
import com.wawey.processing.model.vector2D.Vector2Adapter
import com.wawey.processing.view.DrawColors
import com.wawey.processing.view.Drawable
import com.wawey.processing.view.Plane
import java.awt.Color
import java.awt.Shape

/**
 *
 * @author Tomas Perez Molina
 */
class ShipView(shape: Shape, private val state: ShipState): Drawable {
    private val points = ShapeUtil.getPoints(shape, 0)

    override fun draw(plane: Plane) {
        plane.setDrawColors(DrawColors(border = Color.RED, borderWidth = 5))
        plane.polygon(points, state.position, state.heading)
    }

    override fun isActive(): Boolean = state.destroyed
}