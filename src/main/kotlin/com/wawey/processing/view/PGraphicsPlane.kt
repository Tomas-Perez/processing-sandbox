package com.wawey.processing.view

import edu.austral.starship.base.vector.Vector2
import processing.core.PGraphics
import java.awt.Color

/**
 *
 * @author Tomas Perez Molina
 */
class PGraphicsPlane(private val graphics: PGraphics): Plane {

    override fun ellipse(radiusA: Int, radiusB: Int, position: Vector2, angle: Float) {
        withRotation({
            graphics.ellipse(
                position.x,
                position.y,
                radiusA.toFloat(),
                radiusB.toFloat()
            )
        }, angle)
    }

    override fun rectangle(width: Int, height: Int, position: Vector2, angle: Float) {
        withRotation({
            graphics.rect(
                position.x,
                position.y,
                width.toFloat(),
                height.toFloat()
            )
        }, angle)
    }

    override fun setDrawColors(drawColors: DrawColors) {
        graphics.fill(drawColors.fill.rgb)
        graphics.strokeWeight(drawColors.borderWidth.toFloat())
        graphics.stroke(drawColors.border.rgb)
    }

    override fun getWidth(): Int = graphics.width

    override fun getHeight(): Int = graphics.height

    private fun withRotation(function: () -> Unit, angle: Float) {
        graphics.pushMatrix()
        graphics.rotate(angle)
        function()
        graphics.popMatrix()
    }
}