package com.wawey.processing.model.entity

import edu.austral.starship.base.collision.Collisionable

/**
 *
 * @author Tomas Perez Molina
 */
interface GameEntity: Collisionable<GameEntity> {
    var destroyed: Boolean

    fun <R> accept(entityVisitor: EntityVisitor<R>): R
    fun update()
    fun hit(damage: Int)
}