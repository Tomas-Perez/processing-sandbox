package com.wawey.processing.view

import com.wawey.processing.model.vector2D.Vector2D
import processing.core.PGraphics

/**
 *
 * @author Tomas Perez Molina
 */
class PGraphicsPlane(private val graphics: PGraphics): Plane {

    override fun ellipse(radiusA: Int, radiusB: Int, position: Vector2D, angle: Float) {
        withRototraslation(position, angle) {
            graphics.ellipse(
                    0f,
                    0f,
                    radiusA.toFloat(),
                    radiusB.toFloat()
            )
        }
    }

    override fun polygon(points: List<Vector2D>, position: Vector2D, angle: Float) {
        graphics.shapeMode(PGraphics.CENTER)
        withRototraslation(position, angle) {
            graphics.beginShape()
            points.forEach {
                graphics.vertex(it.x, it.y)
            }
            graphics.endShape(PGraphics.CLOSE)
        }
    }

    override fun rectangle(width: Int, height: Int, position: Vector2D, angle: Float) {
        withRototraslation(position, angle) {
            graphics.rect(
                    0f,
                    0f,
                    width.toFloat(),
                    height.toFloat()
            )
        }
    }

    override fun setDrawColors(drawColors: DrawColors) {
        graphics.fill(drawColors.fill.rgb)
        graphics.strokeWeight(drawColors.borderWidth.toFloat())
        graphics.stroke(drawColors.border.rgb)
    }

    override fun getWidth(): Int = graphics.width

    override fun getHeight(): Int = graphics.height

    private fun withRototraslation(position: Vector2D, angle: Float, function: () -> Unit) {
        graphics.pushMatrix()
        graphics.translate(position.x, position.y)
        graphics.rotate(angle - ANGLE_OFFSET)
        function()
        graphics.popMatrix()
    }

    companion object {
        private const val ANGLE_OFFSET = Math.PI.toFloat() / 2
    }
}