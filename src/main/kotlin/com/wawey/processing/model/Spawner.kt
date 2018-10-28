package com.wawey.processing.model

import com.wawey.processing.model.entity.Observable
import com.wawey.processing.model.entity.gun.Laser
import com.wawey.processing.model.entity.ship.Ship
import com.wawey.processing.model.entity.ship.SmallShip
import com.wawey.processing.model.vector2D.Vector2Adapter

/**
 *
 * @author Tomas Perez Molina
 */
interface Spawner<T>: Observable<SpawnObserver<T>> {
    fun spawn(x: Int, y: Int): T
}

class ShipSpawner(private val bounds: Bounds): Spawner<Ship> {
    private val observers: MutableList<SpawnObserver<Ship>> = mutableListOf()

    override fun spawn(x: Int, y: Int): Ship = SmallShip(Vector2Adapter.vector(x,y), bounds, Laser(bounds))

    override fun addObserver(o: SpawnObserver<Ship>) {
        observers.add(o)
    }

    override fun removeObserver(o: SpawnObserver<Ship>) {
        observers.remove(o)
    }
}

interface SpawnObserver<T> {
    fun notifySpawn(t: T)
}