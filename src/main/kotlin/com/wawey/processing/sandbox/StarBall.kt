package com.wawey.processing.sandbox

import processing.core.PApplet

/**
 *
 * @author Tomas Perez Molina
 */
class StarBall(private val sketch: PApplet, private val position: Position) {

    private val size: Float = sketch.random(10f, 100f)

    fun step(seconds: Float) {
        position.update(seconds, 5000)
    }

    fun render() {
        sketch.stroke(255)
        sketch.fill(255)
        sketch.ellipse(position.currentX(), position.currentY(), size, size)
    }
}