package com.wawey.processing.model.entity.ship

import com.wawey.processing.model.spawner.SpawnObserver
import com.wawey.processing.model.entity.Observable
import com.wawey.processing.model.entity.bullet.Bullet

/**
 *
 * @author Tomas Perez Molina
 */
interface ShipObserver {
    fun notifyShoot(bullets: List<Bullet>)
    fun notifyHit(damage: Int)
    fun notifyDestroy()
}

class ShootingObserver: ShipObserver, Observable<SpawnObserver<Bullet>> {
    private val observers: MutableList<SpawnObserver<Bullet>> = mutableListOf()

    override fun notifyShoot(bullets: List<Bullet>) {
        observers.forEach { o -> bullets.forEach { o.notifySpawn(it) }  }
    }

    override fun notifyDestroy() = Unit

    override fun notifyHit(damage: Int) = Unit

    override fun addObserver(o: SpawnObserver<Bullet>) {
        observers.add(o)
    }

    override fun removeObserver(o: SpawnObserver<Bullet>) {
        observers.remove(o)
    }
}