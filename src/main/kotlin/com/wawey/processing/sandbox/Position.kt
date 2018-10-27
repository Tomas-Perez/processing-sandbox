package com.wawey.processing.sandbox

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sqrt

/**
 *
 * @author Tomas Perez Molina
 */
class Position private constructor(
        private val x: MutableList<Float>,
        private val y: MutableList<Float>,
        private val interval: MutableList<Float>)
{
    fun currentX(): Float {
        return this.x[0]
    }

    fun currentY(): Float {
        return this.y[0]
    }

    fun shiftPosition(dx: Float, dy: Float) {
        this.x[0] += dx
        this.y[0] += dy
    }

    fun movePosition(x: Float, y: Float, seconds: Float) {
        this.x.add(0, x)
        this.x.removeAt(this.x.size - 1)
        print(this.x)

        this.y.add(0, y)
        this.y.removeAt(this.y.size - 1)

        this.interval.add(0, seconds)
        this.interval.removeAt(this.interval.size - 1)
    }

    fun update(seconds: Float, range: Int) {
        val DRAG = 0.000001f

        val vx = (x[0] - x[1]) / interval[0]
        val vy = (y[0] - y[1]) / interval[0]

        val speed = sqrt(vx * vx + vy * vy)

        val drag = speed * speed * DRAG

        val total = max(abs(vx) + abs(vy), DRAG)
        val dragX = drag * (vx/total)
        val dragY = drag * (vy/total)

        val dx = (vx - dragX) * seconds
        val dy = (vy - dragY) * seconds

        this.movePosition(x[0] + dx, y[0] + dy, seconds)


        val distance = sqrt(x[0] * x[0] + y[0] * y[0])

        if (distance > range) {
            x[0] *= range / distance
            y[0] *= range / distance
        }
    }

    companion object {
        fun createAt(x: Float, y: Float) = Position(mutableListOf(x, 0f, 0f), mutableListOf(y, 0f, 0f), mutableListOf(1f, 1f, 1f))

        fun create(x: Float, y: Float, vx: Float = 0f, vy: Float = 0f): Position {
            return Position(mutableListOf(x, x - vx, x - vx), mutableListOf(y, y - vy, y - vy), mutableListOf(1f, 1f, 1f))
        }
    }
}

