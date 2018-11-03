package com.wawey.processing.model.spawner

import com.wawey.processing.mapTo
import com.wawey.processing.model.Bounds
import com.wawey.processing.model.entity.asteroid.Asteroid
import com.wawey.processing.model.entity.asteroid.CommonAsteroid
import com.wawey.processing.model.vector2D.Vector2Adapter
import com.wawey.processing.random

/**
 *
 * @author Tomas Perez Molina
 */
class AsteroidSpawner(private val bounds: Bounds): Spawner<Asteroid> {
    private val observers: MutableList<SpawnObserver<Asteroid>> = mutableListOf()
    private val sizeRange = 20..100
    private val speedRange = -5..-1
    private val speedFromSize: (Int) -> Float = { it -> sizeRange.mapTo(speedRange)(it).toFloat() }

    override fun spawn(x: Int, y: Int): Asteroid {
        val initialPosition = Vector2Adapter.vector(x, y)
        val target = Vector2Adapter.vector(getRandomTarget(bounds.x), getRandomTarget(bounds.y))
        val size = sizeRange.random()
        val speed = speedFromSize(size)
        return CommonAsteroid(
                size = size,
                position = initialPosition,
                speed = speed,
                heading = initialPosition.substract(target).angle(),
                bounds = bounds
        ).also { a -> observers.forEach { it.notifySpawn(a) } }
    }

    override fun addObserver(o: SpawnObserver<Asteroid>) {
        observers.add(o)
    }

    override fun removeObserver(o: SpawnObserver<Asteroid>) {
        observers.remove(o)
    }

    private fun getRandomTarget(i: Int): Int = ((i / 10f).toInt() .. (i * 9f/10f).toInt()).shuffled().first()
}
