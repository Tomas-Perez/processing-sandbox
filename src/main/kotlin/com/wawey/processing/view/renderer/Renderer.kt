package com.wawey.processing.view.renderer

import com.wawey.processing.view.Drawable
import com.wawey.processing.view.Plane

/**
 *
 * @author Tomas Perez Molina
 */
interface Renderer {
    fun add(drawable: Drawable)
    fun add(drawable: Drawable, z: Int)
    fun render(plane: Plane)
}