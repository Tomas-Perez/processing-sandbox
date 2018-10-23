package com.wawey.processing.sandbox

import processing.core.PApplet
import processing.core.PGraphics


/**
 *
 * @author Tomas Perez Molina
 */
class KBall(private var x: Float, private var y: Float) {

    private val size: Float = (1..10).shuffled().first().toFloat()
    private var xSpeed: Float = (1..10).shuffled().first().toFloat()
    private var ySpeed: Float = (1..10).shuffled().first().toFloat()

    fun step(graphics: PGraphics) {
        x += xSpeed
        if(x !in 0..graphics.width) {
            xSpeed *= -1
        }

        y += ySpeed
        if(y !in 0..graphics.height) {
            ySpeed *= -1
        }
    }

    fun render(graphics: PGraphics) {
        graphics.stroke(255)
        graphics.fill(255)
        graphics.ellipse(x, y, size, size)
    }

}