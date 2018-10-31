package com.wawey.processing.model.score

import com.wawey.processing.model.entity.bullet.Bullet
import com.wawey.processing.model.entity.ship.ShipObserver

/**
 *
 * @author Tomas Perez Molina
 */
class Player(val name: String, var points: Int = 0, var hp: Int = 100, var lives: Int = 3): ShipObserver {

    override fun notifyShoot(bullets: List<Bullet>) = Unit

    override fun notifyHit(damage: Int) {
        hp -= damage
    }


}