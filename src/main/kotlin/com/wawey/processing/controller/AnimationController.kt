package com.wawey.processing.controller

import com.wawey.processing.view.Plane

/**
 *
 * @author Tomas Perez Molina
 */
interface AnimationController {
    fun render(plane: Plane)
    fun update()
}