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

    override fun visit(ship: Ship) {
        /*println("Ship hit ship")*/
    }

    override fun visit(asteroid: Asteroid) {
        /*println("Ship hit asteroid")*/
    }

    override fun visit(bullet: Bullet) {
        /*println("Ship hit bullet")*/
    }

    override fun visit(powerUp: PowerUp) {
        /*println("Ship hit powerup")*/
    }
}