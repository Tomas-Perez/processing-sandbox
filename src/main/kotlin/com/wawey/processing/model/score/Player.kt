package com.wawey.processing.model.score

import com.wawey.processing.model.entity.bullet.Bullet
import com.wawey.processing.model.entity.ship.ShipObserver

/**
 *
 * @author Tomas Perez Molina
 */
class Player(val number: Int, val name: String, var points: Int = 0, private val initialHp: Int = 100, var lives: Int = 3): ShipObserver {
    var hp: Int = initialHp

    var alive: Boolean = true
        get() = lives > 0

    override fun notifyShoot(bullets: List<Bullet>) = Unit

    override fun notifyHit(damage: Int) {
        hp -= damage
    }

    override fun notifyDestroy() {
        --lives
        if(lives >= 0) {
            hp = initialHp
        }
    }
}