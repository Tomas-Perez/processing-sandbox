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
    private val _state = BulletState(position = position, heading = heading, speed = speed)

    private val shape: Shape = Rectangle(0, 0, 3, 3)

    override val state: BulletState = _state
    override var destroyed: Boolean = state.destroyed
        get() = state.destroyed

    override fun update() = with(state) {
        position = position.add(Vector2Adapter.fromModule(speed, heading))
        checkBounds()
    }

    private fun checkBounds() = with(state) {
        if ((position.x !in 0..bounds.x && abs(position.x - bounds.x) > 20) ||
            (position.y !in 0..bounds.y && abs(position.y - bounds.y) > 20)) {
            destroyed = true
        }
    }

    override fun hit(damage: Int) = Unit

    override fun getShape(): Shape = shape

    override fun collisionedWith(collisionable: GameEntity?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}