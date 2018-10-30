package com.wawey.processing.model.entity

import edu.austral.starship.base.collision.Collisionable
import java.awt.Shape
import java.awt.geom.AffineTransform

/**
 *
 * @author Tomas Perez Molina
 */
interface Collider: EntityVisitor<Unit>, Collisionable<Collider> {
    val entity: GameEntity

    override fun collisionedWith(collisionable: Collider) {
        collisionable.entity.accept(this)
    }

    override fun getShape(): Shape = with (entity) {
        val tx = AffineTransform()
        tx.translate(state.position.x.toDouble(),state.position.y.toDouble())
        tx.rotate(state.heading.toDouble())
        return tx.createTransformedShape(shape)
    }
}