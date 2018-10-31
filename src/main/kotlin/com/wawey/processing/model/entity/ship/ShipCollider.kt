package com.wawey.processing.model.entity.ship

import com.wawey.processing.model.entity.Collider
import com.wawey.processing.model.entity.GameEntity
import com.wawey.processing.model.entity.asteroid.Asteroid
import com.wawey.processing.model.entity.bullet.Bullet
import com.wawey.processing.model.entity.powerup.PowerUp
import java.awt.Shape
import java.awt.geom.AffineTransform

/**
 *
 * @author Tomas Perez Molina
 */
class ShipCollider(override val entity: Ship): Collider {

    override fun visit(ship: Ship) = Unit

    override fun visit(asteroid: Asteroid) = Unit

    override fun visit(bullet: Bullet) = bullet.destroy()

    override fun visit(powerUp: PowerUp) = powerUp.destroy()
}