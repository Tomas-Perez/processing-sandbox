package com.wawey.processing.sandbox

import com.wawey.processing.model.ControllableShip
import com.wawey.processing.model.GameEntity
import com.wawey.processing.model.vector2D.Vector2Adapter
import com.wawey.processing.model.vector2D.Vector2D
import com.wawey.processing.view.Plane

/**
 *
 * @author Tomas Perez Molina
 */
class MockShip(x: Int, y: Int): ControllableShip {
    private var speed: Float = 0f
    private var heading: Float = Math.PI.toFloat() / 2
    private var pos: Vector2D = Vector2Adapter.vector(x, y)

    override fun applyAcceleration(a: Float) {
        speed += a
    }

    override fun applyRotation(r: Float) {
        heading += r
    }

    override fun startShooting() {
        println("Start shooting")
    }

    override fun stopShooting() {
        println("Stop shooting")
    }

    fun update() {
        pos = pos.add(Vector2Adapter.fromModule(speed, heading))
        speed *= 0.85f
    }

    fun draw(plane: Plane) {
        plane.polygon(listOf(
                Vector2Adapter.vector(-30, 50),
                Vector2Adapter.vector(0, 30),
                Vector2Adapter.vector(30, 50),
                Vector2Adapter.vector(0, -20),
                Vector2Adapter.vector(-30, 50)
        ), pos, heading)
    }
}