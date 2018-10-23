package com.wawey.processing.sandbox

import edu.austral.starship.base.framework.GameFramework
import edu.austral.starship.base.framework.ImageLoader
import edu.austral.starship.base.framework.WindowSettings
import processing.core.PGraphics
import processing.event.KeyEvent

/**
 *
 * @author Tomas Perez Molina
 */

class Test: GameFramework{
    private val balls: MutableList<KBall> = ArrayList()

    override fun setup(windowsSettings: WindowSettings, imageLoader: ImageLoader) {
        val i = 800f
        windowsSettings.setSize(i.toInt(), i.toInt())
        balls.add(KBall(i / 2, i / 2))
        balls.add(KBall(i / 2, i / 2))
        balls.add(KBall(i / 2, i / 2))
    }

    override fun draw(graphics: PGraphics, timeSinceLastDraw: Float, keySet: MutableSet<Int>) {
        if(balls.size < 20) {
            balls.add(KBall((graphics.width / 2).toFloat(), (graphics.height / 2).toFloat()))
        }
        graphics.background(64)
        balls.forEach { b ->
            b.step(graphics)
            b.render(graphics)
        }
    }

    override fun keyPressed(event: KeyEvent?) {

    }

    override fun keyReleased(event: KeyEvent?) {

    }

}