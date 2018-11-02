package com.wawey.processing

import com.wawey.processing.controller.*
import com.wawey.processing.controller.event.KeyEventHandler
import com.wawey.processing.controller.event.MapKeyEventHandler
import com.wawey.processing.controller.event.ProcessingKeyEventAdapter
import com.wawey.processing.controller.gameplay.StarShipGameplayController
import com.wawey.processing.controller.hud.StarShipHUDController
import com.wawey.processing.controller.ship.ConfigurableShipSpawnController
import com.wawey.processing.model.*
import com.wawey.processing.view.PGraphicsPlane
import com.wawey.processing.view.paintor.AsteroidPainter
import com.wawey.processing.view.paintor.BaseSpawnPainter
import com.wawey.processing.view.paintor.BulletPainter
import com.wawey.processing.view.paintor.ShipPainter
import com.wawey.processing.view.renderer.LayeredRenderer
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
    private val bounds = defaultGameplayConfig.bounds
    private var lag = 0f
    private val screenExtra = 400
    private val baseController: AnimationController

    init {
        val genGamePausePair = {
            val gameController = GameController(
                shipColors = defaultPaintConfig.shipColors,
                gameplayController = StarShipGameplayController(
                        renderer = LayeredRenderer(),
                        world = StarShipWorld(CollisionEngine()),
                        asteroidSpawner = AsteroidSpawner(bounds),
                        shipPainter = ShipPainter(),
                        asteroidSpawnPainter = BaseSpawnPainter(AsteroidPainter()),
                        bulletSpawnPainter = BaseSpawnPainter(BulletPainter()),
                        bounds = bounds),
                hudController = StarShipHUDController(bounds),
                shipSpawnController = ConfigurableShipSpawnController(
                        spawnLocations = defaultGameplayConfig.shipSpawnLocations,
                        shipSpawner = ShipSpawner(bounds),
                        configuration = defaultControlConfig.shipSpawn,
                        shipConfigurations = defaultControlConfig.shipControls
                )
            ).apply { register(handler) }

            val pauseScreen = PauseScreen(
                    bounds = bounds,
                    gameScreen = gameController,
                    selectConfiguration = defaultControlConfig.selectControl,
                    selectKeyName = defaultHUDConfig.selectKeyName,
                    backKeyName = defaultHUDConfig.backKeyName
            )

            GamePausePair(pauseScreen, gameController)
        }

        baseController = BaseController(
                handler = handler,
                configuration = defaultControlConfig.baseControl,
                initialScreen = StartScreen(
                        bounds = bounds,
                        selectConfiguration = defaultControlConfig.selectControl,
                        genGamePausePair = genGamePausePair,
                        selectKeyName = defaultHUDConfig.selectKeyName
                )
        )
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
            baseController.update()
            lag -= MS_PER_UPDATE
        }
        baseController.render(plane)
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