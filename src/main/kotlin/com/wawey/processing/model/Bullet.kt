package com.wawey.processing.model

/**
 *
 * @author Tomas Perez Molina
 */
interface Bullet: GameEntity {
    override fun <R> accept(entityVisitor: EntityVisitor<R>): R = entityVisitor.visit(this)
}