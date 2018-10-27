package com.wawey.processing.sandbox

import com.wawey.processing.controller.*
import com.wawey.processing.model.SmallShip
import com.wawey.processing.model.StarShipWorld
import com.wawey.processing.view.PGraphicsPlane
import com.wawey.processing.view.renderer.LayeredRenderer
import edu.austral.starship.base.Main
import edu.austral.starship.base.framework.GameFramework
import edu.austral.starship.base.framework.ImageLoader
import edu.austral.starship.base.framework.WindowSettings
import processing.core.PGraphics
import processing.event.KeyEvent

/**
 *
 * @author Tomas Perez Molina
 */
typealias JavaKeyEvent = java.awt.event.KeyEvent

class StarshipGame: GameFramework{
    private val handler: KeyEventHandler = MapKeyEventHandler()
    private val adapter: ProcessingKeyEventAdapter = ProcessingKeyEventAdapter(handler)
    private val screenSize: Int = 800
    private val gameplayController: GameplayController

    init {
        gameplayController = StarShipGameplayController(LayeredRenderer(), StarShipWorld(screenSize, screenSize))
        val ship = SmallShip(screenSize, screenSize)
        gameplayController.addShip(ship)
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


    override fun setup(windowsSettings: WindowSettings, imageLoader: ImageLoader) {
        windowsSettings.setSize(screenSize, screenSize)
    }

    override fun draw(graphics: PGraphics, timeSinceLastDraw: Float, keySet: MutableSet<Int>) {
        val plane = PGraphicsPlane(graphics)
        adapter.notifyHandler()
        gameplayController.update()
        gameplayController.render(plane)
    }

    override fun keyPressed(event: KeyEvent) {
        adapter.keyPressed(event)
    }

    override fun keyReleased(event: KeyEvent) {
        adapter.keyReleased(event)
    }
}

fun main(args: Array<String>) {
    Main.main(args)
}