package com.wawey.processing.controller.hud

import com.wawey.processing.model.Bounds
import com.wawey.processing.model.score.Player
import com.wawey.processing.model.vector2D.Vector2Adapter
import com.wawey.processing.view.Drawable
import com.wawey.processing.view.Plane
import com.wawey.processing.view.score.Scoreboard

/**
 *
 * @author Tomas Perez Molina
 */
class StarShipHUDController(private val bounds: Bounds): HUDController {
    private val players = mutableListOf<Drawable>()

    override fun addPlayer(player: Player) {
        val position = Vector2Adapter.vector(100 + (players.size * 200), bounds.y + 50)
        players.add(Scoreboard(player, position))
    }

    override fun render(plane: Plane) = players.forEach { it.draw(plane) }

    override fun update() = Unit
}