package com.wawey.processing.model.entity.powerup

import com.wawey.processing.model.entity.Collider
import com.wawey.processing.model.entity.asteroid.Asteroid
import com.wawey.processing.model.entity.bullet.Bullet
import com.wawey.processing.model.entity.powerup.PowerUp
import com.wawey.processing.model.entity.ship.Ship
import java.awt.Shape
import java.awt.geom.AffineTransform

/**
 *
 * @author Tomas Perez Molina
 */
class PowerUpCollider(override val entity: PowerUp): Collider {

    override fun visit(ship: Ship) {
        entity.enhance(ship)
    }

    override fun visit(asteroid: Asteroid) = Unit

    override fun visit(bullet: Bullet) = Unit

    override fun visit(powerUp: PowerUp) = Unit
}