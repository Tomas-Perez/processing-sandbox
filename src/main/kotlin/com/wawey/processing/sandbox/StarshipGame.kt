package com.wawey.processing.sandbox

import com.wawey.processing.view.PGraphicsPlane
import edu.austral.starship.base.framework.GameFramework
import edu.austral.starship.base.framework.ImageLoader
import edu.austral.starship.base.framework.WindowSettings
import processing.core.PGraphics
import processing.event.KeyEvent

/**
 *
 * @author Tomas Perez Molina
 */

class StarshipGame: GameFramework{
    private val balls: MutableList<KBall> = ArrayList()

    override fun setup(windowsSettings: WindowSettings, imageLoader: ImageLoader) {
        val i = 800
        windowsSettings.setSize(i, i)
        balls.add(KBall(i / 2, i / 2, i, i))
        balls.add(KBall(i / 2, i / 2, i, i))
        balls.add(KBall(i / 2, i / 2, i, i))
    }

    override fun draw(graphics: PGraphics, timeSinceLastDraw: Float, keySet: MutableSet<Int>) {
        val plane = PGraphicsPlane(graphics)

        if(balls.size < 20) {
            balls.add(KBall(
                    (plane.getWidth() / 2),
                    (plane.getHeight() / 2),
                    plane.getWidth(),
                    plane.getHeight()
            ))
        }

        graphics.background(64)

        balls.forEach { it.step() }
        balls.forEach { it.render(plane) }
    }

    override fun keyPressed(event: KeyEvent?) {

    }

    override fun keyReleased(event: KeyEvent?) {

    }

}