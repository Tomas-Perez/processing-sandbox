package com.wawey.processing.sandbox

import processing.core.PApplet


/**
 *
 * @author Tomas Perez Molina
 */
class KBall(private val sketch: PApplet, private var x: Float, private var y: Float) {

    private val size: Float = sketch.random(10f, 100f)
    private var xSpeed: Float = sketch.random(-10f, 10f)
    private var ySpeed: Float = sketch.random(-10f, 10f)

    fun step() {
        x += xSpeed
        if(x !in 0..sketch.width) {
            xSpeed *= -1
        }

        y += ySpeed
        if(y !in 0..sketch.height) {
            ySpeed *= -1
        }
    }

    fun render() {
        sketch.stroke(255)
        sketch.fill(255)
        sketch.ellipse(x, y, size, size)
    }

}