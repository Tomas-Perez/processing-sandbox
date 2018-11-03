package com.wawey.processing.model.spawner

import com.wawey.processing.model.Bounds
import com.wawey.processing.model.entity.gun.Laser
import com.wawey.processing.model.entity.ship.Ship
import com.wawey.processing.model.entity.ship.SmallShip
import com.wawey.processing.model.vector2D.Vector2Adapter
import java.util.*

/**
 *
 * @author Tomas Perez Molina
 */
class SmallShipSpawner(private val bounds: Bounds): ShipSpawner {
    private val observers: MutableList<SpawnObserver<Ship>> = mutableListOf()

    override fun spawn(x: Int, y: Int): Ship =
            SmallShip(UUID.randomUUID(), Vector2Adapter.vector(x,y), bounds, Laser(bounds)).also { s -> observers.forEach { it.notifySpawn(s) } }

    override fun respawn(t: Ship, x: Int, y: Int): Ship =
            SmallShip(t.id, Vector2Adapter.vector(x,y), bounds, Laser(bounds)).also { s -> observers.forEach { it.notifySpawn(s) } }

    override fun addObserver(o: SpawnObserver<Ship>) {
        observers.add(o)
    }

    override fun removeObserver(o: SpawnObserver<Ship>) {
        observers.remove(o)
    }
}

interface ShipSpawner: Spawner<Ship> {
    fun respawn(t: Ship, x: Int, y: Int): Ship
}