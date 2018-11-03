package com.wawey.processing.model.entity.asteroid

import com.wawey.processing.model.Bounds
import com.wawey.processing.model.entity.GameEntity
import com.wawey.processing.model.vector2D.Vector2Adapter
import com.wawey.processing.model.vector2D.Vector2D
import com.wawey.processing.random
import java.awt.Polygon
import java.awt.Shape

/**
 *
 * @author Tomas Perez Molina
 */
class CommonAsteroid(val size: Int, position: Vector2D, heading: Float, speed: Float, private val bounds: Bounds): Asteroid {
    override val state: AsteroidState = AsteroidState(position = position, speed = speed, heading = heading, hp = size)
    override val shape: Shape = randomAsteroid(size, (size * 0.2).toInt())
    override val collider = AsteroidCollider(this)

    private var destroyableByOutOfBounds = false

    override fun update() = with(state) {
        if (destroyed) return

        position = position.add(Vector2Adapter.fromModule(speed, heading))
        checkBounds()
    }

    private fun checkBounds() = with(state) {
        val outOfBounds = bounds.out(position.x.toInt(), position.y.toInt(), offset = size + 20)

        if (!destroyableByOutOfBounds && !outOfBounds) {
            destroyableByOutOfBounds = true
        }

        if (destroyableByOutOfBounds && outOfBounds){
            destroyed = true
        }
    }

    override fun hit(damage: Int): Boolean {
        state.hp -= damage
        if (state.hp <= 0) state.destroyed = true
        return true
    }

    override fun hit(entity: GameEntity) {
        entity.hit(size)
    }

    companion object {
        private fun randomAsteroid(size: Int, maxOffset: Int): Shape {
            val vectors = (0..360 step 20).asSequence()
                    .map{ Math.toRadians(it.toDouble()) }
                    .map {
                        val randomOffset = (-maxOffset..maxOffset).random()
                        Vector2Adapter.fromModule(size.toFloat() + randomOffset, it.toFloat())
                    }
            val xpoints = vectors.map { it.x.toInt() }.toList().toIntArray()
            val ypoints = vectors.map { it.y.toInt() }.toList().toIntArray()
            return Polygon(intArrayOf(*xpoints), intArrayOf(*ypoints), vectors.count())
        }
    }
}