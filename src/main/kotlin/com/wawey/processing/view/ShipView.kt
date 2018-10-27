package com.wawey.processing.view

import java.awt.Shape
import java.awt.geom.AffineTransform

/**
 *
 * @author Tomas Perez Molina
 */
class ShipView(private val shape: Shape): Drawable {


    override fun draw(plane: Plane) {
        shape.getPathIterator(null, 20.toDouble())
    }

    override fun isActive(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}