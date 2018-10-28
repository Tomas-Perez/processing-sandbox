package com.wawey.processing.model.entity.ship

import com.wawey.processing.model.Bounds
import com.wawey.processing.model.entity.GameEntity
import com.wawey.processing.model.entity.gun.Gun
import com.wawey.processing.model.vector2D.Vector2Adapter
import com.wawey.processing.model.vector2D.Vector2D
import java.awt.Polygon
import java.awt.Shape

/**
 *
 * @author Tomas Perez Molina
 */
class SmallShip(position: Vector2D, private val bounds: Bounds, var gun: Gun): Ship {

    private val _state = ShipState(position = position)
    private val observers: MutableList<ShipObserver> = mutableListOf()

    override val state: ShipState = _state
    override var destroyed: Boolean = state.destroyed

    private val shape: Shape = Polygon(
            intArrayOf(-30, 0, 30, 0, -30),
            intArrayOf(32, 12, 32, -37, 32),
            5
    )

    override fun update() = with(state) {
        position = position.add(Vector2Adapter.fromModule(speed, heading))
        speed *= 0.85f
        if (shooting) {
            val offset = Vector2Adapter.fromModule(-50f, heading)
            observers.forEach { it.notifyShoot(gun.shoot(position.add(offset), heading, speed)) }
            shooting = false
        }

        boundCheck()
    }

    private fun boundCheck() = with(state){
        var adjustedX = position.x
        var adjustedY = position.y

        if (adjustedX <= 0) {
            adjustedX = 0f
        } else if (adjustedX >= bounds.x) {
            adjustedX = bounds.x.toFloat()
        }

        if (adjustedY <= 0) {
            adjustedY = 0f
        } else if (adjustedY >= bounds.y) {
            adjustedY = bounds.y.toFloat()
        }

        position = Vector2Adapter.vector(adjustedX, adjustedY)
    }

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

    override fun addObserver(o: ShipObserver) {
        observers.add(o)
    }

    override fun removeObserver(o: ShipObserver) {
        observers.remove(o)
    }
}