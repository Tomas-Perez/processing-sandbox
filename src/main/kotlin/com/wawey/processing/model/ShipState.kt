package com.wawey.processing.model

import com.wawey.processing.model.vector2D.Vector2Adapter
import com.wawey.processing.model.vector2D.Vector2D

/**
 *
 * @author Tomas Perez Molina
 */
data class ShipState(
    var position: Vector2D = Vector2Adapter.vector(0, 0),
    var speed: Float = 0f,
    var heading: Float = (Math.PI / 2).toFloat(),
    var shooting: Boolean = false,
    var hp: Int = 100
) {
    val destroyed: Boolean
        get() = hp <= 0
}