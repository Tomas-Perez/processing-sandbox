package com.wawey.processing.view.renderer

import com.wawey.processing.view.Drawable
import com.wawey.processing.view.Plane

/**
 *
 * @author Tomas Perez Molina
 */
class BasicRenderer: Renderer {
    private var elements: List<Drawable> = listOf()

    override fun add(drawable: Drawable) {
        elements = elements.plus(drawable)
    }
    override fun render(plane: Plane) {
        elements = elements
                .asSequence()
                .filter { it.isActive() }
                .onEach { it.draw(plane) }
                .toList()
    }
}