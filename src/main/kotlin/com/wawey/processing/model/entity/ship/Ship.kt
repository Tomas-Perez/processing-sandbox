package com.wawey.processing.model.entity.ship

import com.wawey.processing.model.entity.*
import com.wawey.processing.model.entity.Observable
import com.wawey.processing.model.entity.gun.Gun
import com.wawey.processing.model.vector2D.Vector2Adapter
import com.wawey.processing.model.vector2D.Vector2D
import java.util.*

/**
 *
 * @author Tomas Perez Molina
 */
interface Ship: GameEntity, ControllableShip, Observable<ShipObserver> {
    val id: UUID
    override val state: ShipState
    var gun: Gun

    override fun <R> accept(entityVisitor: EntityVisitor<R>): R = entityVisitor.visit(this)
}

data class ShipState(
        override var position: Vector2D = Vector2Adapter.vector(0, 0),
        override var heading: Float = (Math.PI / 2).toFloat(),
        override var destroyed: Boolean = false,
        var speed: Float = 0f,
        var hp: Int = 100,
        var shooting: Boolean = false,
        var invincible: Boolean = false
): EntityState