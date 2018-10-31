package com.wawey.processing.model.entity.powerup

import com.wawey.processing.model.entity.EntityVisitor
import com.wawey.processing.model.entity.GameEntity

/**
 *
 * @author Tomas Perez Molina
 */
interface PowerUp: GameEntity {
    override fun <R> accept(entityVisitor: EntityVisitor<R>): R = entityVisitor.visit(this)
    fun destroy()
}