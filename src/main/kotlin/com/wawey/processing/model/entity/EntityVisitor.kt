package com.wawey.processing.model.entity

import com.wawey.processing.model.entity.asteroid.Asteroid
import com.wawey.processing.model.entity.bullet.Bullet
import com.wawey.processing.model.entity.powerup.PowerUp
import com.wawey.processing.model.entity.ship.Ship

/**
 *
 * @author Tomas Perez Molina
 */
interface EntityVisitor<R> {
    fun visit(ship: Ship): R
    fun visit(asteroid: Asteroid): R
    fun visit(bullet: Bullet): R
    fun visit(powerUp: PowerUp): R
}