package com.wawey.processing

import com.wawey.processing.controller.*
import com.wawey.processing.model.Bounds
import com.wawey.processing.model.ShipSpawner
import com.wawey.processing.model.StarShipWorld
import com.wawey.processing.view.PGraphicsPlane
import com.wawey.processing.view.paintor.BaseSpawnPainter
import com.wawey.processing.view.paintor.BulletPainter
import com.wawey.processing.view.paintor.ShipPainter
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
    private val bounds = Bounds(1000, 1000)
    private val gameplayController: GameplayController

    init {
        gameplayController = StarShipGameplayController(
            LayeredRenderer(),
            StarShipWorld(bounds),
            ShipPainter(),
            BaseSpawnPainter(BulletPainter())
        )
        val spawner = ShipSpawner(bounds)
        val ship = spawner.spawn(bounds.x / 2, bounds.y / 2)
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
        windowsSettings.setSize(bounds.x, bounds.y)
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