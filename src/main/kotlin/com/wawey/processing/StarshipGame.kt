package com.wawey.processing

import com.wawey.processing.controller.*
import com.wawey.processing.controller.event.KeyEventHandler
import com.wawey.processing.controller.event.MapKeyEventHandler
import com.wawey.processing.controller.event.ProcessingKeyEventAdapter
import com.wawey.processing.controller.gameplay.GameplayController
import com.wawey.processing.controller.gameplay.StarShipGameplayController
import com.wawey.processing.controller.hud.StarShipHUDController
import com.wawey.processing.model.*
import com.wawey.processing.model.entity.bullet.Bullet
import com.wawey.processing.model.entity.ship.BaseShipObserver
import com.wawey.processing.model.score.Player
import com.wawey.processing.model.score.PointScout
import com.wawey.processing.model.score.PointVisitor
import com.wawey.processing.model.vector2D.Vector2Adapter
import com.wawey.processing.view.PGraphicsPlane
import com.wawey.processing.view.paintor.AsteroidPainter
import com.wawey.processing.view.paintor.BaseSpawnPainter
import com.wawey.processing.view.paintor.BulletPainter
import com.wawey.processing.view.paintor.ShipPainter
import com.wawey.processing.view.renderer.LayeredRenderer
import com.wawey.processing.view.score.Scoreboard
import edu.austral.starship.base.Main
import edu.austral.starship.base.collision.CollisionEngine
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
    private val bounds = Bounds(2560 / 2, 1440 / 2)
    private val gameController: ScreenController
    private var lag = 0f
    private val screenExtra = 200

    init {
        val spawnControllerConfig = ShipSpawnControllerConfiguration(JavaKeyEvent.VK_0)
        val config1 = ShipControllerConfiguration(
                forwardKey = JavaKeyEvent.VK_W,
                backwardKey = JavaKeyEvent.VK_S,
                leftKey = JavaKeyEvent.VK_A,
                rightKey = JavaKeyEvent.VK_D,
                shootKey = JavaKeyEvent.VK_SPACE
        )

        val config2 = ShipControllerConfiguration(
                forwardKey = JavaKeyEvent.VK_UP,
                backwardKey = JavaKeyEvent.VK_DOWN,
                leftKey = JavaKeyEvent.VK_LEFT,
                rightKey = JavaKeyEvent.VK_RIGHT,
                shootKey = JavaKeyEvent.VK_SHIFT
        )

        gameController = GameController(
                gameplayController = StarShipGameplayController(
                        LayeredRenderer(),
                        StarShipWorld(CollisionEngine()),
                        AsteroidSpawner(bounds),
                        ShipPainter(),
                        BaseSpawnPainter(AsteroidPainter()),
                        BaseSpawnPainter(BulletPainter()),
                        bounds),
                hudController = StarShipHUDController(bounds),
                shipSpawnController = ConfigurableShipSpawnController(
                        bounds = bounds,
                        shipSpawner = ShipSpawner(bounds),
                        configuration = spawnControllerConfig,
                        shipConfigurations = listOf(config1, config2)
                )
        )

        gameController.register(handler)
    }


    override fun setup(windowsSettings: WindowSettings, imageLoader: ImageLoader) {
        windowsSettings
                .setSize(bounds.x + screenExtra, bounds.y + screenExtra)
                .setFrameRate(120)
    }

    override fun draw(graphics: PGraphics, timeSinceLastDraw: Float, keySet: MutableSet<Int>) {
        graphics.rectMode(PGraphics.CORNER)
        graphics.translate(screenExtra / 2f, screenExtra / 2f)
        graphics.noFill()
        graphics.stroke(255)
        graphics.strokeWeight(3f)
        graphics.rect(0f, 0f, bounds.x.toFloat(), bounds.y.toFloat())
        val plane = PGraphicsPlane(graphics)
        adapter.notifyHandler()
        lag += timeToMS(timeSinceLastDraw)
        while (lag > MS_PER_UPDATE) {
            gameController.update()
            lag -= MS_PER_UPDATE
        }
        gameController.render(plane)
    }

    private fun timeToMS(time: Float) = (100f / time) * (1f/60f) * 1000f

    override fun keyPressed(event: KeyEvent) {
        adapter.keyPressed(event)
    }

    override fun keyReleased(event: KeyEvent) {
        adapter.keyReleased(event)
    }

    companion object {
        const val MS_PER_UPDATE = 16
    }
}

fun main(args: Array<String>) {
    Main.main(args)
}