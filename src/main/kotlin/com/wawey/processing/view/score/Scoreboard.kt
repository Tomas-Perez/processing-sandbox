package com.wawey.processing.view.score

import com.wawey.processing.model.score.Player
import com.wawey.processing.model.vector2D.Vector2D
import com.wawey.processing.view.DrawColors
import com.wawey.processing.view.Drawable
import com.wawey.processing.view.Plane
import java.awt.Color

/**
 *
 * @author Tomas Perez Molina
 */
class Scoreboard(private val player: Player, private val position: Vector2D): Drawable {
    override fun draw(plane: Plane) {
        plane.setDrawColors(DrawColors(Color.WHITE))
        plane.text("${player.name}: ${player.points}", 20, position)
    }

    override fun isActive(): Boolean = true
}