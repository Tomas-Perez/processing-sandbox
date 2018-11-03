package com.wawey.processing.model.entity.powerup

/**
 *
 * @author Tomas Perez Molina
 */
interface PowerUpVisitor<R> {
    fun visit(doubleShot: DoubleShotPowerUp): R
    fun visit(spread: SpreadPowerUp): R
    fun visit(doubleDamage: DoubleDamagePowerUp): R
}