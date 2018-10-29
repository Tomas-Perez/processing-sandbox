package com.wawey.processing.model.entity.bullet

import com.wawey.processing.model.Bounds
import com.wawey.processing.model.entity.GameEntity
import com.wawey.processing.model.vector2D.Vector2Adapter
import com.wawey.processing.model.vector2D.Vector2D
import java.awt.Rectangle
import java.awt.Shape
import kotlin.math.abs

/**
 *
 * @author Tomas Perez Molina
 */
class LaserBullet(position: Vector2D, heading: Float, speed: Float, private val bounds: Bounds): Bullet {

    override val shape = Rectangle(0, 0, 3, 3)
    override val collider = BulletCollider(this)

    override val state: BulletState = BulletState(position = position, heading = heading, speed = speed)

    override var destroyed: Boolean = state.destroyed
        get() = state.destroyed

    override fun update() = with(state) {
        position = position.add(Vector2Adapter.fromModule(speed, heading))
        checkBounds()
    }

    private fun checkBounds() = with(state) {
        if (bounds.out(position.x.toInt(), position.y.toInt(), offset = 50)) {
            destroyed = true
        }
    }

    override fun hit(damage: Int) = Unit


    override fun copy(position: Vector2D?, heading: Float?, speed: Float?): Bullet =
        LaserBullet(
            position = position?: this.state.position,
            heading = heading?: this.state.heading,
            speed = speed?: this.state.speed,
            bounds = bounds)
}