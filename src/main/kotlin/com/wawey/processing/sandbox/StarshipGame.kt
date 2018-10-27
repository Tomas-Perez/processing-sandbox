package com.wawey.processing.sandbox

import com.wawey.processing.controller.*
import com.wawey.processing.model.ControllableShip
import com.wawey.processing.view.PGraphicsPlane
import com.wawey.processing.view.Plane
import edu.austral.starship.base.Main
import edu.austral.starship.base.framework.GameFramework
import edu.austral.starship.base.framework.ImageLoader
import edu.austral.starship.base.framework.WindowSettings
import processing.core.PApplet
import processing.core.PGraphics
import processing.event.KeyEvent

/**
 *
 * @author Tomas Perez Molina
 */
typealias JavaKeyEvent = java.awt.event.KeyEvent

class StarshipGame: GameFramework{
    private val balls: MutableList<KBall> = ArrayList()
    private val handler: KeyEventHandler = MapKeyEventHandler()
    private val adapter: ProcessingKeyEventAdapter = ProcessingKeyEventAdapter(handler)
    private val screenSize: Int = 800
    private val ship: MockShip = MockShip(screenSize / 2, screenSize / 2)


    override fun setup(windowsSettings: WindowSettings, imageLoader: ImageLoader) {
        windowsSettings.setSize(screenSize, screenSize)
        val config = ShipControllerConfiguration(
                forwardKey = JavaKeyEvent.VK_UP,
                backwardKey = JavaKeyEvent.VK_DOWN,
                leftKey = JavaKeyEvent.VK_LEFT,
                rightKey = JavaKeyEvent.VK_RIGHT,
                shootKey = JavaKeyEvent.VK_SPACE
        )
        val shipController = ConfigurableShipController(ship, config)
        shipController.register(handler)
    }

    override fun draw(graphics: PGraphics, timeSinceLastDraw: Float, keySet: MutableSet<Int>) {
        val plane = PGraphicsPlane(graphics)
        adapter.notifyHandler()
        ship.update()
        ship.draw(plane)
    }

    override fun keyPressed(event: KeyEvent) {
        adapter.keyPressed(event)
    }

    override fun keyReleased(event: KeyEvent) {
        adapter.keyReleased(event)
    }

    private fun ballsSetup(screenSize: Int) {
        balls.add(KBall(screenSize / 2, screenSize / 2, screenSize, screenSize))
        balls.add(KBall(screenSize / 2, screenSize / 2, screenSize, screenSize))
        balls.add(KBall(screenSize / 2, screenSize / 2, screenSize, screenSize))
    }

    private fun ballsDraw(plane: Plane) {
        if(balls.size < 20) {
            balls.add(KBall(
                    (plane.getWidth() / 2),
                    (plane.getHeight() / 2),
                    plane.getWidth(),
                    plane.getHeight()
            ))
        }

        balls.forEach { it.step() }
        balls.forEach { it.render(plane) }
    }



}

fun main(args: Array<String>) {
    Main.main(args)
}