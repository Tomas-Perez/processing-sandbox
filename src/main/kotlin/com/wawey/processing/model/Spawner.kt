package com.wawey.processing.model

import com.wawey.processing.model.entity.Observable
import com.wawey.processing.model.entity.asteroid.Asteroid
import com.wawey.processing.model.entity.asteroid.CommonAsteroid
import com.wawey.processing.model.entity.gun.DoubleShot
import com.wawey.processing.model.entity.gun.Laser
import com.wawey.processing.model.entity.gun.Spread
import com.wawey.processing.model.entity.ship.Ship
import com.wawey.processing.model.entity.ship.SmallShip
import com.wawey.processing.model.vector2D.Vector2Adapter
import com.wawey.processing.random

/**
 *
 * @author Tomas Perez Molina
 */
interface Spawner<T>: Observable<SpawnObserver<T>> {
    fun spawn(x: Int, y: Int): T
}

class ShipSpawner(private val bounds: Bounds): Spawner<Ship> {
    private val observers: MutableList<SpawnObserver<Ship>> = mutableListOf()

    override fun spawn(x: Int, y: Int): Ship =
            SmallShip(Vector2Adapter.vector(x,y), bounds, Laser(bounds)).also { s -> observers.forEach { it.notifySpawn(s) } }

    override fun addObserver(o: SpawnObserver<Ship>) {
        observers.add(o)
    }

    override fun removeObserver(o: SpawnObserver<Ship>) {
        observers.remove(o)
    }
}

class AsteroidSpawner(private val bounds: Bounds): Spawner<Asteroid> {
    private val observers: MutableList<SpawnObserver<Asteroid>> = mutableListOf()

    override fun spawn(x: Int, y: Int): Asteroid {
        val initialPosition = Vector2Adapter.vector(x, y)
        val target = Vector2Adapter.vector(getRandomTarget(bounds.x), getRandomTarget(bounds.y))
        return CommonAsteroid(
                size = (20..50).random(),
                position = initialPosition,
                speed = -5f,
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

interface SpawnObserver<T> {
    fun notifySpawn(t: T)
}