package com.wawey.processing.model.score

import com.wawey.processing.model.entity.EntityVisitor
import com.wawey.processing.model.entity.asteroid.Asteroid
import com.wawey.processing.model.entity.bullet.Bullet
import com.wawey.processing.model.entity.powerup.PowerUp
import com.wawey.processing.model.entity.ship.Ship

/**
 *
 * @author Tomas Perez Molina
 */
class HitVisitor: EntityVisitor<Int> {
    override fun visit(ship: Ship): Int = 20

    override fun visit(asteroid: Asteroid): Int = 10

    override fun visit(bullet: Bullet): Int = 0

    override fun visit(powerUp: PowerUp): Int = 0
}

class DestroyVisitor: EntityVisitor<Int> {
    override fun visit(ship: Ship): Int = 200

    override fun visit(asteroid: Asteroid): Int = 50

    override fun visit(bullet: Bullet): Int = 0

    override fun visit(powerUp: PowerUp): Int = 0
}