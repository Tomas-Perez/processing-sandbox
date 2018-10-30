package com.wawey.processing.model.entity.bullet

import com.wawey.processing.model.entity.Collider
import com.wawey.processing.model.entity.asteroid.Asteroid
import com.wawey.processing.model.entity.powerup.PowerUp
import com.wawey.processing.model.entity.ship.Ship
import java.awt.Shape
import java.awt.geom.AffineTransform

/**
 *
 * @author Tomas Perez Molina
 */
class BulletCollider(override val entity: Bullet): Collider {

    override fun visit(ship: Ship) {
        entity.hit(ship)
    }

    override fun visit(asteroid: Asteroid) {
        entity.hit(asteroid)
    }

    override fun visit(bullet: Bullet) = Unit

    override fun visit(powerUp: PowerUp) = Unit
}