package com.wawey.processing.model.entity.ship

import com.wawey.processing.Debounce
import com.wawey.processing.model.Bounds
import com.wawey.processing.model.entity.gun.Gun
import com.wawey.processing.model.vector2D.Vector2Adapter
import com.wawey.processing.model.vector2D.Vector2D
import java.awt.Polygon
import java.util.*

/**
 *
 * @author Tomas Perez Molina
 */
class SmallShip(override val id: UUID, position: Vector2D, private val bounds: Bounds, var gun: Gun): Ship {

    private val observers: MutableList<ShipObserver> = mutableListOf()

    override val state = ShipState(position = position, invincible = true)
    override val collider = ShipCollider(this)
    private var acceleration = 0f
    private var rotation = 0f
    private val debounce = Debounce(500)
    private val invincibleTime = 60
    private var invincibilityCounter = 0

    override val shape = Polygon(
            intArrayOf(-30, 0, 30, 0, -30),
            intArrayOf(32, 12, 32, -37, 32),
            5
    )

    override fun update() = with(state) {
        if (destroyed) return
        if (invincible) {
            invincibilityCounter++
        }
        if (invincibilityCounter >= invincibleTime) {
            invincibilityCounter = 0
            invincible = false
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

        override fun hit(damage: Int): Boolean = with(state){
            if(!invincible) {
                hp -= damage
                observers.forEach { it.notifyHit(damage) }
                if (hp <= 0) {
                    destroyed = true
                    observers.forEach { it.notifyDestroy() }
                }
            invincible = true
            return@with true
        }
        return@with false
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SmallShip

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "SmallShip(id=$id)"
    }


}