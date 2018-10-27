package com.wawey.processing.model

/**
 *
 * @author Tomas Perez Molina
 */
interface World {
    val boundsX: Int
    val boundsY: Int

    fun addEntity(entity: GameEntity)
    fun update()
}