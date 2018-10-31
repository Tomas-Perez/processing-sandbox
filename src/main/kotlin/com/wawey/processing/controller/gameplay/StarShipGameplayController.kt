package com.wawey.processing.controller.gameplay

import com.wawey.processing.Debounce
import com.wawey.processing.model.Bounds
import com.wawey.processing.model.SpawnObserver
import com.wawey.processing.model.Spawner
import com.wawey.processing.model.World
import com.wawey.processing.model.entity.asteroid.Asteroid
import com.wawey.processing.model.entity.bullet.Bullet
import com.wawey.processing.model.entity.ship.ShootingObserver
import com.wawey.processing.model.entity.ship.Ship
import com.wawey.processing.model.vector2D.Vector2Adapter
import com.wawey.processing.random
import com.wawey.processing.view.Plane
import com.wawey.processing.view.paintor.Painter
import com.wawey.processing.view.paintor.SpawnPainter
import com.wawey.processing.view.renderer.Renderer
import java.awt.Color
import kotlin.math.max

/**
 *
 * @author Tomas Perez Molina
 */
class StarShipGameplayController(
        private val renderer: Renderer,
        private val world: World,
        private val asteroidSpawner: Spawner<Asteroid>,
        private val shipPainter: Painter<Ship>,
        private val asteroidSpawnPainter: SpawnPainter<Asteroid>,
        private val bulletSpawnPainter: SpawnPainter<Bullet>,
        private val bounds: Bounds): GameplayController {

    private var bulletBuffer: List<Bullet> = emptyList()
    private val debounce = Debounce(1000)

    private val bulletBufferObserver = object: SpawnObserver<Bullet> {
        override fun notifySpawn(t: Bullet) {
            bulletBuffer = bulletBuffer.plus(t)
        }
    }

    init {
        asteroidSpawner.addObserver(asteroidSpawnPainter)
    }

    override fun addShip(s: Ship, c: Color) {
        s.addObserver(ShootingObserver().apply {
            addObserver(bulletSpawnPainter)
            addObserver(bulletBufferObserver)
        })
        renderer.add(shipPainter.draw(s, c))
        world.addEntity(s)
    }

    override fun render(plane: Plane) = renderer.render(plane)

    override fun update() {
        world.update()
        val asteroidAmount = (1..4).random()
        spawnAsteroids(asteroidAmount).forEach { world.addEntity(it) }
        bulletBuffer.forEach { world.addEntity(it) }
        bulletBuffer = emptyList()
        bulletSpawnPainter.getNew().forEach { renderer.add(it) }
        asteroidSpawnPainter.getNew().forEach { renderer.add(it) }
    }

    private fun spawnAsteroids(amount: Int): List<Asteroid> {
        val centerVector = Vector2Adapter.vector(bounds.centerX(), bounds.centerY())
        return debounce(emptyList()) {
            (0..amount).map {
                val module = max(bounds.y, bounds.x) / 2f + 200
                val angle = Math.toRadians((0..360).random().toDouble()).toFloat()
                val vector = centerVector.add(Vector2Adapter.fromModule(module, angle))
                asteroidSpawner.spawn(vector.x.toInt(), vector.y.toInt())
            }
        }
    }
}
