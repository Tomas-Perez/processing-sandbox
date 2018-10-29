package com.wawey.processing.model.entity

import edu.austral.starship.base.collision.Collisionable
import java.awt.Shape

/**
 *
 * @author Tomas Perez Molina
 */
interface Collider: EntityVisitor<Unit>, Collisionable<Collider> {
    val entity: GameEntity

    override fun collisionedWith(collisionable: Collider) {
        collisionable.entity.accept(this)
    }
}