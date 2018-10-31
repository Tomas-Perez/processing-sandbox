package com.wawey.processing.view.score

import com.wawey.processing.model.score.Player
import com.wawey.processing.model.vector2D.Vector2Adapter
import com.wawey.processing.model.vector2D.Vector2D
import com.wawey.processing.view.DrawColors
import com.wawey.processing.view.Drawable
import com.wawey.processing.view.Plane
import java.awt.Color

/**
 *
 * @author Tomas Perez Molina
 */
class PlayerView(private val player: Player, private val position: Vector2D, private val fontSize: Int): Drawable {

    override fun draw(plane: Plane) {
        plane.setDrawColors(DrawColors(Color.WHITE))
        plane.text(player.name, fontSize, position)
        plane.text("Score: ${player.points}", fontSize, position.add(Vector2Adapter.vector(0, 30)))
        plane.text("HP: ${player.hp}", fontSize, position.add(Vector2Adapter.vector(0, 60)))
        plane.text("Lives: ${player.lives}", fontSize, position.add(Vector2Adapter.vector(0, 90)))
    }

    override fun isActive(): Boolean = true
}