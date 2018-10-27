package com.wawey.processing.view

/**
 *
 * @author Tomas Perez Molina
 */
interface Drawable {
    fun draw(plane: Plane)
    fun isActive(): Boolean
}