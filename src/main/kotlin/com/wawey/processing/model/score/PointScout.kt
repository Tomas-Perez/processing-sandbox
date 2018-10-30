package com.wawey.processing.model.score

import com.wawey.processing.model.entity.EntityVisitor
import com.wawey.processing.model.entity.GameEntity
import com.wawey.processing.model.entity.bullet.BulletObserver

/**
 *
 * @author Tomas Perez Molina
 */
class PointScout(private val player: Player, private val pointVisitor: EntityVisitor<Int>): BulletObserver {

    override fun notifyHit(entity: GameEntity) {
        player.points += entity.accept(pointVisitor)
    }

    override fun notifyDestroy(entity: GameEntity) {
        player.points += entity.accept(pointVisitor)
    }
}