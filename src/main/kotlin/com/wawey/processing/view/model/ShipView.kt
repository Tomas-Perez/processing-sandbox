package com.wawey.processing.view.model

import com.wawey.processing.util.getPoints
import com.wawey.processing.model.entity.ship.ShipState
import com.wawey.processing.view.DrawColors
import com.wawey.processing.view.Drawable
import com.wawey.processing.view.Plane
import java.awt.Color
import java.awt.Shape

/**
 *
 * @author Tomas Perez Molina
 */
class ShipView(shape: Shape, private val state: ShipState, private val color: Color): Drawable {
    private val points = shape.getPoints()
    private val blinkTime = 20
    private var currentBlinkTime = 0

    override fun draw(plane: Plane) {
        var draw = true
        if (state.invincible) {
            currentBlinkTime++
            if (currentBlinkTime >= blinkTime) {
                draw = false
            }
            if (currentBlinkTime >= blinkTime * 2) {
                currentBlinkTime = 0
            }
        }
        if (draw) {
            plane.setDrawColors(DrawColors(border = color, borderWidth = 5))
            plane.polygon(points, state.position, state.heading)
        }
    }

    override fun isActive(): Boolean = !state.destroyed
}