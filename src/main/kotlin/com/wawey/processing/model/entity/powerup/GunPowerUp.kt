package com.wawey.processing.model.entity.powerup

import com.wawey.processing.model.entity.Collider
import com.wawey.processing.model.entity.EntityState
import com.wawey.processing.model.entity.gun.DoubleDamage
import com.wawey.processing.model.entity.gun.DoubleShot
import com.wawey.processing.model.entity.gun.Gun
import com.wawey.processing.model.entity.gun.Spread
import com.wawey.processing.model.entity.ship.Ship
import com.wawey.processing.model.vector2D.Vector2D
import java.awt.Rectangle
import java.awt.Shape

/**
 *
 * @author Tomas Perez Molina
 */
abstract class GunPowerUp(private val attachFunc: (Gun) -> Gun, position: Vector2D): PowerUp {
    private val size = 40

    override val shape: Shape = Rectangle(-size / 2, -size / 2, size, size)
    override val state: PowerUpState = PowerUpState(position = position)
    override fun enhance(s: Ship) {
        s.gun = attachFunc(s.gun)
    }
}

class DoubleShotPowerUp(position: Vector2D): GunPowerUp({DoubleShot(it)}, position) {
    override val collider: Collider = PowerUpCollider(this)
    override fun <R> accept(powerUpVisitor: PowerUpVisitor<R>) = powerUpVisitor.visit(this)
}

class SpreadPowerUp(position: Vector2D): GunPowerUp({Spread(it)}, position) {
    override val collider: Collider = PowerUpCollider(this)
    override fun <R> accept(powerUpVisitor: PowerUpVisitor<R>) = powerUpVisitor.visit(this)
}

class DoubleDamagePowerUp(position: Vector2D): GunPowerUp({DoubleDamage(it)}, position) {
    override val collider: Collider = PowerUpCollider(this)
    override fun <R> accept(powerUpVisitor: PowerUpVisitor<R>) = powerUpVisitor.visit(this)
}