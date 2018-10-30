package com.wawey.processing.model.entity.asteroid

import com.wawey.processing.model.entity.Collider
import com.wawey.processing.model.entity.bullet.Bullet
import com.wawey.processing.model.entity.powerup.PowerUp
import com.wawey.processing.model.entity.ship.Ship

/**
 *
 * @author Tomas Perez Molina
 */
class AsteroidCollider(override val entity: Asteroid): Collider {

    override fun visit(ship: Ship) {

    }

    override fun visit(asteroid: Asteroid) = Unit

    override fun visit(bullet: Bullet) = bullet.destroy()

    override fun visit(powerUp: PowerUp) {
        println("asteroid hit powerup")
    }
}