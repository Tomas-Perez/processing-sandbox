package com.wawey.processing.view.model;

import com.wawey.processing.ShapeUtil
import com.wawey.processing.model.entity.bullet.BulletState
import com.wawey.processing.view.Drawable
import com.wawey.processing.view.Plane
import java.awt.Shape

/**
 * @author Tomas Perez Molina
 */
class BulletView(shape: Shape, private val state: BulletState): Drawable {
    private val points = ShapeUtil.getPoints(shape, 0)

    override fun draw(plane: Plane) {
        plane.polygon(points, state.position, state.heading)
    }

    override fun isActive(): Boolean = !state.destroyed
}
