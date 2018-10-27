package com.wawey.processing.model

/**
 *
 * @author Tomas Perez Molina
 */
interface PowerUp: GameEntity {
    override fun <R> accept(entityVisitor: EntityVisitor<R>): R = entityVisitor.visit(this)
}