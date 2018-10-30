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

