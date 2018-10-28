package com.wawey.processing.view.renderer

import com.wawey.processing.view.Drawable
import com.wawey.processing.view.Plane

/**
 *
 * @author Tomas Perez Molina
 */
class Layer: Drawable {
    private var elements: List<Drawable> = listOf()

    fun add(drawable: Drawable) {
        elements = elements.plus(drawable)
    }
    override fun draw(plane: Plane) {
        elements = elements
                .asSequence()
                .filter { it.isActive() }
                .onEach { it.draw(plane) }
                .toList()
    }
    override fun isActive(): Boolean = true

    override fun toString(): String {
        return "Layer(elements=$elements)"
    }
}