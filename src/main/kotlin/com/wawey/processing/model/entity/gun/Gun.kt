package com.wawey.processing.model.entity.gun

import com.wawey.processing.Debounce
import com.wawey.processing.model.Bounds
import com.wawey.processing.model.entity.bullet.Bullet
import com.wawey.processing.model.entity.bullet.LaserBullet
import com.wawey.processing.model.vector2D.Vector2Adapter
import com.wawey.processing.model.vector2D.Vector2D
import kotlin.math.min

/**
 *
 * @author Tomas Perez Molina
 */
interface Gun {
    fun shoot(origin: Vector2D, heading: Float, boost: Float): List<Bullet>
    fun removeAttachment(): Gun = this
}

class Laser(private val bounds: Bounds): Gun {
    private val speed = -10f

    override fun shoot(origin: Vector2D, heading: Float, boost: Float): List<Bullet> =
            listOf(LaserBullet(origin, heading, min(speed + boost, speed), bounds))
}

class Spread(private val gun: Gun): Gun {
    override fun shoot(origin: Vector2D, heading: Float, boost: Float): List<Bullet> {
        val angleOffset = (Math.PI / 8).toFloat()

        val shots = gun.shoot(origin, heading, boost)
        return listOf(
                shots.map { it.copy(heading = it.state.heading + angleOffset) },
                shots,
                shots.map { it.copy(heading = it.state.heading - angleOffset) }
        ).flatten()
    }

    override fun removeAttachment(): Gun = gun
}

class DoubleShot(private val gun: Gun): Gun {

    override fun shoot(origin: Vector2D, heading: Float, boost: Float): List<Bullet> {
        val positionOffset = Vector2Adapter.fromModule(-15f, heading + Math.PI.toFloat() / 2)
        val shots = gun.shoot(origin, heading, boost)
        return listOf(
                shots.map { it.copy(position = it.state.position.add(positionOffset)) },
                shots.map { it.copy(position = it.state.position.substract(positionOffset)) }
        ).flatten()
    }

    override fun removeAttachment(): Gun = gun
}