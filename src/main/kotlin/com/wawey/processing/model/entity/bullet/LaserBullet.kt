package com.wawey.processing.model.entity.bullet

import com.wawey.processing.model.Bounds
import com.wawey.processing.model.entity.GameEntity
import com.wawey.processing.model.vector2D.Vector2Adapter
import com.wawey.processing.model.vector2D.Vector2D
import java.awt.Rectangle

/**
 *
 * @author Tomas Perez Molina
 */
class LaserBullet(override val size: Int, position: Vector2D, heading: Float, speed: Float, private val bounds: Bounds): Bullet {

    override val shape = Rectangle(0, 0, size, size)
    override val collider = BulletCollider(this)
    override val state: BulletState = BulletState(position = position, heading = heading, speed = speed)

    private val observers = mutableListOf<BulletObserver>()

    override fun update() = with(state) {
        position = position.add(Vector2Adapter.fromModule(speed, heading))
        checkBounds()
    }

    private fun checkBounds() = with(state) {
        if (bounds.out(position.x.toInt(), position.y.toInt(), offset = 50)) {
            destroyed = true
        }
    }

    override fun hit(damage: Int) = false

    override fun hit(entity: GameEntity) {
        val hit = entity.hit(size * 5)
        if (hit) {
            if (entity.state.destroyed) observers.forEach { it.notifyDestroy(entity) }
            else observers.forEach { it.notifyHit(entity) }
        }
    }

    override fun addObserver(o: BulletObserver) {
        observers.add(o)
    }

    override fun removeObserver(o: BulletObserver) {
        observers.remove(o)
    }

    override fun copy(size: Int?, position: Vector2D?, heading: Float?, speed: Float?): Bullet =
        LaserBullet(
            size = size?: this.size,
            position = position?: this.state.position,
            heading = heading?: this.state.heading,
            speed = speed?: this.state.speed,
            bounds = bounds)
}