package com.wawey.processing.model.vector2D

/**
 *
 * @author Tomas Perez Molina
 */
interface Vector2D {

    val x: Float

    val y: Float

    fun add(other: Vector2D): Vector2D

    fun substract(other: Vector2D): Vector2D

    fun multiply(scalar: Float): Vector2D

    fun rotate(angle: Float): Vector2D

    fun module(): Float

    fun unitary(): Vector2D

    fun angle(): Float

    companion object {
        fun compareByX(v1: Vector2D, v2: Vector2D): Int = v1.x.compareTo(v2.x)
        fun compareByY(v1: Vector2D, v2: Vector2D): Int = v1.y.compareTo(v2.y)
        fun compareByXY(v1: Vector2D, v2: Vector2D): Int {
            val cX = compareByX(v1, v2)
            return when (cX) {
                0 -> compareByY(v1, v2)
                else -> cX
            }
        }
    }
}
