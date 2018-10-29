package com.wawey.processing.model.entity

import java.awt.Shape

/**
 *
 * @author Tomas Perez Molina
 */
interface GameEntity {
    var destroyed: Boolean
    val collider: Collider
    val shape: Shape

    fun <R> accept(entityVisitor: EntityVisitor<R>): R
    fun update()
    fun hit(damage: Int)
}