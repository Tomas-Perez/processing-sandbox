package com.wawey.processing.controller

import com.wawey.processing.model.*
import com.wawey.processing.model.entity.asteroid.Asteroid
import com.wawey.processing.model.entity.ship.Ship
import com.wawey.processing.model.entity.bullet.Bullet
import com.wawey.processing.model.entity.ship.BaseShipObserver
import com.wawey.processing.view.Plane
import com.wawey.processing.view.paintor.Painter
import com.wawey.processing.view.paintor.SpawnPainter
import com.wawey.processing.view.renderer.Renderer

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
    private val timeout = 1000
    private var lastSpawn = System.currentTimeMillis()

    init {
        asteroidSpawner.addObserver(asteroidSpawnPainter)
    }

    override fun addShip(s: Ship) {
        s.addObserver(BaseShipObserver().apply {
            addObserver(bulletSpawnPainter)
            addObserver(object: SpawnObserver<Bullet> {
                override fun notifySpawn(t: Bullet) {
                    bulletBuffer = bulletBuffer.plus(t)
                }
            })
        })
        renderer.add(shipPainter.draw(s))
        world.addEntity(s)
    }

    override fun render(plane: Plane) = renderer.render(plane)

    override fun update() {
        world.update()
        val asteroidAmount = (1..1).shuffled().first()
        spawnAsteroids(asteroidAmount).forEach { world.addEntity(it) }
        bulletBuffer.forEach { world.addEntity(it) }
        bulletBuffer = emptyList()
        bulletSpawnPainter.getNew().forEach { renderer.add(it) }
        asteroidSpawnPainter.getNew().forEach { renderer.add(it) }
    }

    private fun spawnAsteroids(amount: Int): List<Asteroid> {
        val current = System.currentTimeMillis()
        return if (current - lastSpawn > timeout) {
            lastSpawn = current
            val xPositionRange = (-200..-100).asIterable().plus((bounds.x + 100..bounds.x + 200)).shuffled().take(amount)
            val yPositionRange = (-200..-100).asIterable().plus((bounds.y + 100..bounds.y + 200)).shuffled().take(amount)
            xPositionRange.zip(yPositionRange).map { asteroidSpawner.spawn(it.first, it.second) }
        } else emptyList()
    }
}
