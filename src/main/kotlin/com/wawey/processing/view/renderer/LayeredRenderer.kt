package com.wawey.processing.view.renderer

import com.wawey.processing.view.Drawable
import com.wawey.processing.view.Plane

/**
 *
 * @author Tomas Perez Molina
 */
class LayeredRenderer: Renderer {
    private val layers: MutableMap<Int, Layer> = mutableMapOf()

    override fun add(drawable: Drawable) {
        layers.getOrPut(0) {
            Layer()
        }.add(drawable)
    }

    override fun add(drawable: Drawable, z: Int) {
        layers.getOrPut(z) {
            Layer()
        }.add(drawable)
    }

    override fun render(plane: Plane) {
        layers.values.forEach { it.draw(plane) }
    }
}