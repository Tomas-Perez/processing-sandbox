package com.wawey.processing.model

/**
 *
 * @author Tomas Perez Molina
 */
interface Ship: GameEntity, ControllableShip {
    val state: ShipState

    override fun <R> accept(entityVisitor: EntityVisitor<R>): R = entityVisitor.visit(this)
}