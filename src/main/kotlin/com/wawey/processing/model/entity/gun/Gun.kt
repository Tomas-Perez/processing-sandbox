package com.wawey.processing.model.entity.gun

import com.wawey.processing.model.Bounds
import com.wawey.processing.model.entity.bullet.Bullet
import com.wawey.processing.model.entity.bullet.LaserBullet
import com.wawey.processing.model.vector2D.Vector2D

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
    private val timeout = 500
    private var lastShot = System.currentTimeMillis()

    override fun shoot(origin: Vector2D, heading: Float, boost: Float): List<Bullet> {
        val current = System.currentTimeMillis()
        return if (current - lastShot > timeout) {
            lastShot = current
            listOf(LaserBullet(origin, heading, speed + boost, bounds))
        } else emptyList()
    }

}