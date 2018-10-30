package com.wawey.processing.model

import com.wawey.processing.model.entity.GameEntity

/**
 *
 * @author Tomas Perez Molina
 */
interface World {
    fun addEntity(entity: GameEntity)
    fun update()
}