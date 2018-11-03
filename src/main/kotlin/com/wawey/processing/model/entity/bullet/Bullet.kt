package com.wawey.processing.model.entity.bullet

import com.wawey.processing.model.entity.EntityState
import com.wawey.processing.model.entity.EntityVisitor
import com.wawey.processing.model.entity.GameEntity
import com.wawey.processing.model.entity.Observable
import com.wawey.processing.model.vector2D.Vector2Adapter
import com.wawey.processing.model.vector2D.Vector2D

/**
 *
 * @author Tomas Perez Molina
 */
interface Bullet: GameEntity, Observable<BulletObserver> {
    override val state: BulletState
    val size: Int

    override fun <R> accept(entityVisitor: EntityVisitor<R>): R = entityVisitor.visit(this)

    fun hit(entity: GameEntity)

    fun copy(size: Int? = null,
             position: Vector2D? = null,
             heading: Float? = null,
             speed: Float? = null): Bullet

    fun destroy() = with(state) {
        destroyed = true
    }
}

data class BulletState(
        override var position: Vector2D = Vector2Adapter.vector(0, 0),
        override var heading: Float = (Math.PI / 2).toFloat(),
        override var destroyed: Boolean = false,
        var speed: Float = 0f
): EntityState