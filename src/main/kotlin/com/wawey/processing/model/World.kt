package com.wawey.processing.model

import com.wawey.processing.model.entity.GameEntity

/**
 *
 * @author Tomas Perez Molina
 */
interface World {
    val bounds: Bounds

    fun addEntity(entity: GameEntity)
    fun update()
}

data class Bounds(val x: Int, val y: Int)