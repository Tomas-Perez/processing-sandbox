package com.wawey.processing.model.entity.asteroid

import com.wawey.processing.model.entity.EntityState
import com.wawey.processing.model.entity.EntityVisitor
import com.wawey.processing.model.entity.GameEntity
import com.wawey.processing.model.vector2D.Vector2Adapter
import com.wawey.processing.model.vector2D.Vector2D

/**
 *
 * @author Tomas Perez Molina
 */
interface Asteroid: GameEntity {
    override val state: AsteroidState

    override fun <R> accept(entityVisitor: EntityVisitor<R>): R = entityVisitor.visit(this)
    fun hit(entity: GameEntity)
}

data class AsteroidState(
        override var position: Vector2D = Vector2Adapter.vector(0, 0),
        override var heading: Float = (Math.PI / 2).toFloat(),
        override var destroyed: Boolean = false,
        var speed: Float = 0f,
        var hp: Int = 1
): EntityState