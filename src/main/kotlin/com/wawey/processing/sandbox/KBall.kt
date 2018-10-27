package com.wawey.processing.sandbox

import com.wawey.processing.view.DrawColors
import com.wawey.processing.view.Plane
import edu.austral.starship.base.vector.Vector2
import processing.core.PApplet
import processing.core.PGraphics
import java.awt.Color


/**
 *
 * @author Tomas Perez Molina
 */
class KBall(private var x: Int,
            private var y: Int,
            private val boundsX: Int,
            private val boundsY: Int) {

    private val size: Int = (10..50).shuffled().first()
    private var xSpeed: Int = (10..50).shuffled().first()
    private var ySpeed: Int = (10..50).shuffled().first()

    fun step() {
        x += xSpeed
        if(x !in 0..boundsX) {
            xSpeed *= -1
        }

        y += ySpeed
        if(y !in 0..boundsY) {
            ySpeed *= -1
        }
    }

    fun render(graphics: Plane) {
        graphics.setDrawColors(DrawColors(Color.BLUE, Color.GRAY, 5))
        graphics.ellipse(size, size, Vector2.vector(x.toFloat(), y.toFloat()), 0f)
    }

}