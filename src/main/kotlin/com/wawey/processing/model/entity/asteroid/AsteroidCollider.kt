package com.wawey.processing.model.entity.asteroid

import com.wawey.processing.model.entity.Collider
import com.wawey.processing.model.entity.bullet.Bullet
import com.wawey.processing.model.entity.powerup.PowerUp
import com.wawey.processing.model.entity.ship.Ship
import java.awt.Shape
import java.awt.geom.AffineTransform

/**
 *
 * @author Tomas Perez Molina
 */
class AsteroidCollider(override val entity: Asteroid): Collider {

    override fun visit(ship: Ship) {
        println("asteroid hit ship")
    }

    override fun visit(asteroid: Asteroid) = Unit

    override fun visit(bullet: Bullet) {
        println("asteroid hit bullet")
    }

    override fun visit(powerUp: PowerUp) {
        println("asteroid hit powerup")
    }

    override fun getShape(): Shape {
        val tx = AffineTransform()
        tx.translate(entity.state.position.x.toDouble(), entity.state.position.y.toDouble())
        tx.rotate(entity.state.heading.toDouble())
        return tx.createTransformedShape(entity.shape)
    }
}