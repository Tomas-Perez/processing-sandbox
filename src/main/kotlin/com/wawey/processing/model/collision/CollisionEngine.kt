package com.wawey.processing.model.collision

import edu.austral.starship.base.collision.Collisionable

/**
 *
 * @author Tomas Perez Molina
 */
interface CollisionEngine<T: Collisionable<T>> {
    fun checkCollisions(collisionables: List<T>)
}

class CollisionEngineAdapter<T: Collisionable<T>>: CollisionEngine<T> {
    private val engine = edu.austral.starship.base.collision.CollisionEngine<T>()

    override fun checkCollisions(collisionables: List<T>) = engine.checkCollisions(collisionables)
}