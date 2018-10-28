package com.wawey.processing.model.entity.asteroid

import com.wawey.processing.model.entity.EntityVisitor
import com.wawey.processing.model.entity.GameEntity

/**
 *
 * @author Tomas Perez Molina
 */
interface Asteroid: GameEntity {

    override fun <R> accept(entityVisitor: EntityVisitor<R>): R = entityVisitor.visit(this)

}