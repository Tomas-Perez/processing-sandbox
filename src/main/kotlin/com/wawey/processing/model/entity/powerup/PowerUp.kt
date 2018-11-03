package com.wawey.processing.model.entity.powerup

import com.wawey.processing.model.entity.EntityState
import com.wawey.processing.model.entity.EntityVisitor
import com.wawey.processing.model.entity.GameEntity
import com.wawey.processing.model.entity.ship.Ship
import com.wawey.processing.model.vector2D.Vector2Adapter
import com.wawey.processing.model.vector2D.Vector2D

/**
 *
 * @author Tomas Perez Molina
 */
interface PowerUp: GameEntity {
    override val state: PowerUpState

    override fun <R> accept(entityVisitor: EntityVisitor<R>): R = entityVisitor.visit(this)
    fun <R> accept(powerUpVisitor: PowerUpVisitor<R>): R

    fun destroy() = with(state) {
        destroyed = true
    }

    override fun update() {
        if (state.timeLeft <= 0) state.destroyed = true
        else state.timeLeft--
    }

    override fun hit(damage: Int): Boolean = false

    fun enhance(s: Ship)
}

data class PowerUpState(
        override var position: Vector2D = Vector2Adapter.vector(0, 0),
        override var heading: Float = (Math.PI / 2).toFloat(),
        override var destroyed: Boolean = false,
        var timeLeft: Int = 1000
): EntityState