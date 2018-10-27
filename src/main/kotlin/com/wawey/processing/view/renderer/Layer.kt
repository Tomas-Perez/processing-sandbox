package com.wawey.processing.view.renderer

import com.wawey.processing.view.Drawable
import com.wawey.processing.view.Plane

/**
 *
 * @author Tomas Perez Molina
 */
class Layer: Drawable {
    private val elements: MutableList<Drawable> = mutableListOf()

    fun add(drawable: Drawable)  = elements.add(drawable)
    override fun draw(plane: Plane) = elements.forEach { it.draw(plane) }
    override fun isActive(): Boolean = true

    override fun toString(): String {
        return "Layer(elements=$elements)"
    }
}