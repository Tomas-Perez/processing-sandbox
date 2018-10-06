package com.wawey.processing.sandbox

import processing.core.PApplet

/**
 *
 * @author Tomas Perez Molina
 */
class KSketch: PApplet() {
    private val balls: MutableList<KBall> = ArrayList()

    override fun settings() {
        size(800, 800)
        balls.add(KBall(this, width/2f, height/2f))
    }

    override fun draw() {
        background(64)
        balls.forEach { b ->
            b.step()
            b.render()
        }
        fill(0f, 0f, 0f)
        text(frameRate.toString(), 50f, 50f, 100f)
        textSize(30f)
    }

    override fun mouseDragged() {
        balls.add(KBall(this, mouseX.toFloat(), mouseY.toFloat()))
    }
}

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val processingArgs: Array<String> = arrayOf("MySketch")
        val sketch = KSketch()
        PApplet.runSketch(processingArgs, sketch)
    }
}