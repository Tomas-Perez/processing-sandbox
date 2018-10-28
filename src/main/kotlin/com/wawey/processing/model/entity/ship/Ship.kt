package com.wawey.processing.model.entity.ship

import com.wawey.processing.model.entity.*
import com.wawey.processing.model.vector2D.Vector2Adapter
import com.wawey.processing.model.vector2D.Vector2D

/**
 *
 * @author Tomas Perez Molina
 */
interface Ship: GameEntity, ControllableShip, Observable<ShipObserver> {
    val state: ShipState

    override fun <R> accept(entityVisitor: EntityVisitor<R>): R = entityVisitor.visit(this)
}

data class ShipState(
        var position: Vector2D = Vector2Adapter.vector(0, 0),
        var speed: Float = 0f,
        var heading: Float = (Math.PI / 2).toFloat(),
        var shooting: Boolean = false,
        var hp: Int = 100
) {
    var destroyed: Boolean = false
        get() {
            return hp <= 0
        }
}