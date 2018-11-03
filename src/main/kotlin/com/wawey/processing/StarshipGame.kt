package com.wawey.processing

import com.wawey.processing.configuration.*
import com.wawey.processing.controller.*
import com.wawey.processing.controller.event.KeyEventHandler
import com.wawey.processing.controller.event.MapKeyEventHandler
import com.wawey.processing.controller.event.ProcessingKeyEventAdapter
import com.wawey.processing.controller.gameplay.StarShipGameplayController
import com.wawey.processing.controller.hud.StarShipHUDController
import com.wawey.processing.controller.ship.ConfigurableShipSpawnController
import com.wawey.processing.model.Bounds
import com.wawey.processing.model.StarShipWorld
import com.wawey.processing.model.spawner.*
import com.wawey.processing.view.PGraphicsPlane
import com.wawey.processing.view.paintor.*
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

class StarshipGame: GameFramework{
    private val handler: KeyEventHandler = MapKeyEventHandler()
    private val adapter: ProcessingKeyEventAdapter = ProcessingKeyEventAdapter(handler)
    private val bounds = defaultGameplayConfig.bounds
    private val screenBounds = Bounds(bounds.x, bounds.y + 200)
    private var lag = 0f
    private val baseController: AnimationController

    init {
        val controlConfig = INIControlConfigurator("conf.ini", defaultControlConfig).controlConfiguration
        val genGamePausePair = {
            val gameController = GameController(
                shipColors = defaultPaintConfig.shipColors,
                gameplayController = StarShipGameplayController(
                        renderer = LayeredRenderer(),
                        world = StarShipWorld(CollisionEngine()),
                        asteroidSpawner = AsteroidSpawner(bounds),
                        powerUpSpawner = RandomPowerUpSpawner(
                                listOf(
                                        DoubleShotSpawner(),
                                        SpreadSpawner(),
                                        DoubleDamageSpawner()
                                )
                        ),
                        shipPainter = ShipPainter(),
                        asteroidSpawnPainter = BaseSpawnPainter(AsteroidPainter()),
                        bulletSpawnPainter = BaseSpawnPainter(BulletPainter()),
                        powerUpSpawnPainter = BaseSpawnPainter(PowerUpPainter()),
                        bounds = bounds),
                hudController = StarShipHUDController(bounds, screenBounds),
                shipSpawnController = ConfigurableShipSpawnController(
                        spawnLocations = defaultGameplayConfig.shipSpawnLocations,
                        shipSpawner = SmallShipSpawner(bounds),
                        configuration = controlConfig.shipSpawn,
                        shipConfigurations = controlConfig.shipControls
                )
            ).apply { register(handler) }

            val pauseScreen = PauseScreen(
                    bounds = screenBounds,
                    gameScreen = gameController,
                    selectConfiguration = defaultControlConfig.selectControl,
                    selectKeyName = defaultHUDConfig.selectKeyName,
                    backKeyName = defaultHUDConfig.backKeyName
            )

            GamePausePair(pauseScreen, gameController)
        }

        baseController = BaseController(
                handler = handler,
                configuration = controlConfig.baseControl,
                initialScreen = StartScreen(
                        bounds = screenBounds,
                        selectConfiguration = controlConfig.selectControl,
                        genGamePausePair = genGamePausePair,
                        selectKeyName = defaultHUDConfig.selectKeyName
                )
        )
    }

    override fun setup(windowsSettings: WindowSettings, imageLoader: ImageLoader) {
        windowsSettings
                .setSize(screenBounds.x, screenBounds.y)
                .setFrameRate(120)
    }

    override fun draw(graphics: PGraphics, timeSinceLastDraw: Float, keySet: MutableSet<Int>) {
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