package com.wawey.processing.view.model

import com.wawey.processing.getPoints
import com.wawey.processing.model.entity.powerup.PowerUpState
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
class DoubleShotView(shape: Shape, private val state: PowerUpState, private val color: Color): Drawable {
    private val points = shape.getPoints()

    override fun draw(plane: Plane) {
        plane.setDrawColors(DrawColors(fill = Color.BLACK, border = color, borderWidth = 2))
        plane.polygon(points, state.position, state.heading)
        plane.setDrawColors(DrawColors(border = Color.WHITE, borderWidth = 0))
        plane.rectangle(3, 15, state.position.add(Vector2Adapter.vector(5, 0)), state.heading)
        plane.rectangle(3, 15, state.position.substract(Vector2Adapter.vector(5, 0)), state.heading)
    }

    override fun isActive(): Boolean = !state.destroyed
}

class SpreadView(shape: Shape, private val state: PowerUpState, private val color: Color): Drawable {
    private val points = shape.getPoints()

    override fun draw(plane: Plane) {
        plane.setDrawColors(DrawColors(fill = Color.BLACK, border = color, borderWidth = 2))
        plane.polygon(points, state.position, state.heading)
        plane.setDrawColors(DrawColors(border = Color.WHITE, borderWidth = 0))
        plane.rectangle(3, 15, state.position.add(Vector2Adapter.vector(6.5f, 0f)), state.heading + (Math.PI.toFloat() / 4))
        plane.rectangle(3, 15, state.position, state.heading)
        plane.rectangle(3, 15, state.position.substract(Vector2Adapter.vector(6.5f, 0f)), state.heading - (Math.PI.toFloat() / 4))
    }

    override fun isActive(): Boolean = !state.destroyed
}

class DoubleDamageView(shape: Shape, private val state: PowerUpState, private val color: Color): Drawable {
    private val points = shape.getPoints()

    override fun draw(plane: Plane) {
        plane.setDrawColors(DrawColors(fill = Color.BLACK, border = color, borderWidth = 2))
        plane.polygon(points, state.position, state.heading)
        plane.setDrawColors(DrawColors())
        plane.text("x2", 20, state.position, centered = true)
    }

    override fun isActive(): Boolean = !state.destroyed
}