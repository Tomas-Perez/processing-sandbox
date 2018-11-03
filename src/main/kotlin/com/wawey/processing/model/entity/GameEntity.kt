package com.wawey.processing.model.entity

import com.wawey.processing.model.vector2D.Vector2D
import java.awt.Shape

/**
 *
 * @author Tomas Perez Molina
 */
interface GameEntity {
    val collider: Collider
    val shape: Shape
    val state: EntityState

    fun <R> accept(entityVisitor: EntityVisitor<R>): R
    fun update()
    fun hit(damage: Int): Boolean
}

interface EntityState {
    var position: Vector2D
    var heading: Float
    var destroyed: Boolean
}