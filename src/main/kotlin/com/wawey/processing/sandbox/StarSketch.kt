package com.wawey.processing.sandbox

import processing.core.PApplet

/**
 *
 * @author Tomas Perez Molina
 */

class StarSketch: PApplet() {
    private val balls: MutableList<StarBall> = ArrayList()

    override fun settings() {
        size(800, 800)
        balls.add(StarBall(this, Position.createAt(width/2f, height/2f)))
    }

    override fun draw() {
        background(64)
        balls.forEach { b ->
            b.step(frameRate / 1000)
            b.render()
        }
        fill(0f, 0f, 0f)
        text(frameRate.toString(), 50f, 50f, 100f)
        textSize(30f)
    }

    override fun mouseDragged() {
        balls.add(StarBall(this, Position.createAt(mouseX.toFloat(), mouseY.toFloat())))
    }
}

object Main2 {
    @JvmStatic
    fun main(args: Array<String>) {
        val processingArgs: Array<String> = arrayOf("MySketch")
        val sketch = StarSketch()
        PApplet.runSketch(processingArgs, sketch)
    }
}