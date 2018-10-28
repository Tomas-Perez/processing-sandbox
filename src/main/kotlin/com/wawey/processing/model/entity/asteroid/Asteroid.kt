package com.wawey.processing.model.entity.asteroid

import com.wawey.processing.model.entity.EntityVisitor
import com.wawey.processing.model.entity.GameEntity
import com.wawey.processing.model.vector2D.Vector2Adapter
import com.wawey.processing.model.vector2D.Vector2D

/**
 *
 * @author Tomas Perez Molina
 */
interface Asteroid: GameEntity {
    val state: AsteroidState

    override fun <R> accept(entityVisitor: EntityVisitor<R>): R = entityVisitor.visit(this)
}

data class AsteroidState(
        var position: Vector2D = Vector2Adapter.vector(0, 0),
        var speed: Float = 0f,
        var heading: Float = (Math.PI / 2).toFloat(),
        var hp: Int = 100,
        var destroyed: Boolean = false
)