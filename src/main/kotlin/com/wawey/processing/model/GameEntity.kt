package com.wawey.processing.model

import edu.austral.starship.base.collision.Collisionable

/**
 *
 * @author Tomas Perez Molina
 */
interface GameEntity: Collisionable<GameEntity> {
    fun <R> accept(entityVisitor: EntityVisitor<R>): R
    fun update()
    fun isDestroyed(): Boolean
    fun hit(damage: Int)
}