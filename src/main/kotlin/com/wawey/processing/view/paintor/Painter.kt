package com.wawey.processing.view.paintor

import com.wawey.processing.model.SpawnObserver
import com.wawey.processing.model.entity.asteroid.Asteroid
import com.wawey.processing.model.entity.bullet.Bullet
import com.wawey.processing.model.entity.ship.Ship
import com.wawey.processing.view.Drawable
import com.wawey.processing.view.model.AsteroidView
import com.wawey.processing.view.model.BulletView
import com.wawey.processing.view.model.ShipView
import java.awt.Color

/**
 *
 * @author Tomas Perez Molina
 */
interface Painter<T> {
    fun draw(t: T, color: Color = Color.WHITE): Drawable
}

class ShipPainter: Painter<Ship> {
    override fun draw(t: Ship, color: Color): Drawable = ShipView(t.shape, t.state, color)
}

class BulletPainter: Painter<Bullet> {
    override fun draw(t: Bullet, color: Color): Drawable = BulletView(t.shape, t.state, color)
}

class AsteroidPainter: Painter<Asteroid> {
    override fun draw(t: Asteroid, color: Color): Drawable = AsteroidView(t.shape, t.state, color)
}

interface SpawnPainter<T> : SpawnObserver<T>{
    fun getNew(): List<Drawable>
}

class BaseSpawnPainter<T>(private val painter: Painter<T>): SpawnPainter<T> {
    private var buffer: List<Drawable> = emptyList()

    override fun getNew(): List<Drawable> = buffer.apply { buffer = emptyList() }

    override fun notifySpawn(t: T) {
        buffer = buffer.plus(painter.draw(t))
    }
}