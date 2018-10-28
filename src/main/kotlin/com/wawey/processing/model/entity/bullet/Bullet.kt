package com.wawey.processing.model.entity.bullet

import com.wawey.processing.model.Bounds
import com.wawey.processing.model.entity.EntityVisitor
import com.wawey.processing.model.entity.GameEntity
import com.wawey.processing.model.vector2D.Vector2Adapter
import com.wawey.processing.model.vector2D.Vector2D

/**
 *
 * @author Tomas Perez Molina
 */
interface Bullet: GameEntity {
    val state: BulletState

    override fun <R> accept(entityVisitor: EntityVisitor<R>): R = entityVisitor.visit(this)

    fun copy(position: Vector2D? = null,
             heading: Float? = null,
             speed: Float? = null): Bullet
}

data class BulletState(
        var position: Vector2D = Vector2Adapter.vector(0, 0),
        var speed: Float = 0f,
        var heading: Float = (Math.PI / 2).toFloat(),
        var destroyed: Boolean = false
)