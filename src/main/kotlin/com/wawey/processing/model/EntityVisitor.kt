package com.wawey.processing.model

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