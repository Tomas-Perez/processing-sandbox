package com.wawey.processing.model.score

import com.wawey.processing.model.entity.bullet.Bullet
import com.wawey.processing.model.entity.ship.ShipObserver

/**
 *
 * @author Tomas Perez Molina
 */
class StarShipPlayer(override val number: Int, override val name: String, override var points: Int = 0, private val initialHp: Int = 100, override var lives: Int = 3): Player {
    override var hp: Int = initialHp

    override var alive: Boolean = true
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

interface Player: ShipObserver {
    val number: Int
    val name: String
    var points: Int
    var lives: Int
    var hp: Int
    var alive: Boolean
}