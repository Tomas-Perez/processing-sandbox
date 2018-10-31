package com.wawey.processing.model.entity.ship

import com.wawey.processing.model.entity.Observable
import com.wawey.processing.model.SpawnObserver
import com.wawey.processing.model.entity.bullet.Bullet
import com.wawey.processing.model.score.Player

/**
 *
 * @author Tomas Perez Molina
 */
interface ShipObserver {
    fun notifyShoot(bullets: List<Bullet>)
    fun notifyHit(damage: Int)
}

class ShootingObserver: ShipObserver, Observable<SpawnObserver<Bullet>> {
    private val observers: MutableList<SpawnObserver<Bullet>> = mutableListOf()

    override fun notifyShoot(bullets: List<Bullet>) {
        observers.forEach { o -> bullets.forEach { o.notifySpawn(it) }  }
    }

    override fun notifyHit(damage: Int) = Unit

    override fun addObserver(o: SpawnObserver<Bullet>) {
        observers.add(o)
    }

    override fun removeObserver(o: SpawnObserver<Bullet>) {
        observers.remove(o)
    }
}

class PlayerShipObserver(private val player: Player): ShipObserver {

    override fun notifyShoot(bullets: List<Bullet>) = Unit

    override fun notifyHit(damage: Int) {
        player.hp -= damage
    }

}