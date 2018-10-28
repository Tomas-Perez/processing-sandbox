package com.wawey.processing.model.entity.ship

import com.wawey.processing.model.entity.Observable
import com.wawey.processing.model.SpawnObserver
import com.wawey.processing.model.entity.bullet.Bullet

/**
 *
 * @author Tomas Perez Molina
 */
interface ShipObserver: Observable<SpawnObserver<Bullet>> {
    fun notifyShoot(bullets: List<Bullet>)
}

class BaseShipObserver: ShipObserver {
    private val observers: MutableList<SpawnObserver<Bullet>> = mutableListOf()

    override fun notifyShoot(bullets: List<Bullet>) {
       observers.forEach { o -> bullets.forEach { o.notifySpawn(it) }  }
    }

    override fun addObserver(o: SpawnObserver<Bullet>) {
        observers.add(o)
    }

    override fun removeObserver(o: SpawnObserver<Bullet>) {
        observers.remove(o)
    }
}