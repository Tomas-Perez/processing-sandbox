package com.wawey.processing.model

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
}
