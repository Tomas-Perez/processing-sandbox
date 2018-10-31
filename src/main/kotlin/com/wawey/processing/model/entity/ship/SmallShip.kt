package com.wawey.processing.model.entity.ship

import com.wawey.processing.Debounce
import com.wawey.processing.model.Bounds
import com.wawey.processing.model.entity.gun.Gun
import com.wawey.processing.model.vector2D.Vector2Adapter
import com.wawey.processing.model.vector2D.Vector2D
import java.awt.Polygon

/**
 *
 * @author Tomas Perez Molina
 */
class SmallShip(position: Vector2D, private val bounds: Bounds, var gun: Gun): Ship {

    private val observers: MutableList<ShipObserver> = mutableListOf()

    override val state = ShipState(position = position)
    override val collider = ShipCollider(this)
    private var acceleration = 0f
    private var rotation = 0f
    private val debounce = Debounce(500)

    override val shape = Polygon(
            intArrayOf(-30, 0, 30, 0, -30),
            intArrayOf(32, 12, 32, -37, 32),
            5
    )

    override fun update() = with(state) {
        if (hp <= 0) {
            destroyed = true
            return
        }
        speed += acceleration
        acceleration = 0f
        heading += rotation
        rotation = 0f
        position = position.add(Vector2Adapter.fromModule(speed, heading))
        speed *= 0.85f
        if (shooting) {
            val offset = Vector2Adapter.fromModule(-50f, heading)
            val bullets = gun.shoot(position.add(offset), heading, speed)
            observers.forEach { it.notifyShoot(bullets) }
            shooting = false
        }

        checkBounds()
    }

    private fun checkBounds() = with(state) {
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

    override fun applyAcceleration(a: Float) {
        acceleration = a
    }

    override fun applyRotation(r: Float) {
        rotation = r
    }

    override fun shoot() = debounce {
        state.shooting = true
    }

    override fun addObserver(o: ShipObserver) {
        observers.add(o)
    }

    override fun removeObserver(o: ShipObserver) {
        observers.remove(o)
    }
}