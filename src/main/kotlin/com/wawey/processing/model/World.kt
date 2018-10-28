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

data class Bounds(val x: Int, val y: Int) {
    fun centerX() = x / 2
    fun centerY() = y / 2

    fun out(x: Int, y: Int, offset: Int) =
            (x !in -offset..(this.x + offset)) || (y !in -offset..(this.y + offset))
}