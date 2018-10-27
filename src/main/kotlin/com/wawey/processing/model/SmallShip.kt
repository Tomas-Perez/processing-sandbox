package com.wawey.processing.model

import com.wawey.processing.model.vector2D.Vector2Adapter
import java.awt.Polygon
import java.awt.Shape

/**
 *
 * @author Tomas Perez Molina
 */
class SmallShip(private val boundsX: Int, private val boundsY: Int): Ship {

    private val _state = ShipState(position = Vector2Adapter.vector(boundsX / 2, boundsY / 2))

    override val state: ShipState
        get() = _state

    private val shape: Shape = Polygon(
            intArrayOf(-30, 0, 30, 0, -30),
            intArrayOf(32, 12, 32, -37, 32),
            5
    )

    override fun update() {
        state.position = state.position.add(Vector2Adapter.fromModule(state.speed, state.heading))
        state.speed *= 0.85f
        if (state.shooting) state.shooting = false

        var adjustedX = state.position.x
        var adjustedY = state.position.y

        if (adjustedX <= 0) {
            adjustedX = 0f
        } else if (adjustedX >= boundsX) {
            adjustedX = boundsX.toFloat()
        }

        if (adjustedY <= 0) {
            adjustedY = 0f
        } else if (adjustedY >= boundsY) {
            adjustedY = boundsY.toFloat()
        }

        state.position = Vector2Adapter.vector(adjustedX, adjustedY)
    }

    override fun isDestroyed(): Boolean = state.destroyed

    override fun hit(damage: Int) {
        state.hp -= damage
    }

    override fun getShape(): Shape = shape

    override fun collisionedWith(collisionable: GameEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun applyAcceleration(a: Float) {
        state.speed += a
    }

    override fun applyRotation(r: Float) {
        state.heading += r
    }

    override fun startShooting() {
        state.shooting = true
    }

    override fun stopShooting() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}