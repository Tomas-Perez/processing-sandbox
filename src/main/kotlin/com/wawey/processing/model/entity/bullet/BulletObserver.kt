package com.wawey.processing.model.entity.bullet

import com.wawey.processing.model.entity.EntityVisitor
import com.wawey.processing.model.entity.GameEntity

/**
 *
 * @author Tomas Perez Molina
 */
interface BulletObserver {
    fun notifyHit(entity: GameEntity)
    fun notifyDestroy(entity: GameEntity)
}

class PointScout(private val pointVisitor: EntityVisitor<Int>): BulletObserver {

    override fun notifyHit(entity: GameEntity) {
        println(entity.accept(pointVisitor))
    }

    override fun notifyDestroy(entity: GameEntity) {
        println(entity.accept(pointVisitor))
    }
}