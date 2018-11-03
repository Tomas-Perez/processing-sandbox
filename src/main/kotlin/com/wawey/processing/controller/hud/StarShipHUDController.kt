package com.wawey.processing.controller.hud

import com.wawey.processing.model.Bounds
import com.wawey.processing.model.score.Player
import com.wawey.processing.model.vector2D.Vector2Adapter
import com.wawey.processing.view.DrawColors
import com.wawey.processing.view.Drawable
import com.wawey.processing.view.Plane
import com.wawey.processing.view.score.PlayerView
import java.awt.Color

/**
 *
 * @author Tomas Perez Molina
 */
class StarShipHUDController(private val bounds: Bounds, private val screenBounds: Bounds): HUDController {
    private val players = mutableListOf<Drawable>()
    private val hudHeight = screenBounds.y - bounds.y
    private val hudWidth = screenBounds.x

    override fun addPlayer(player: Player) {
        val position = Vector2Adapter.vector(100 + (players.size * 200), bounds.y + 50)
        players.add(PlayerView(player, position, fontSize = 20))
    }

    override fun render(plane: Plane) {
        plane.setDrawColors(DrawColors(fill = Color.BLACK, borderWidth = 0))
        plane.rectangle(hudWidth, hudHeight, Vector2Adapter.vector(screenBounds.centerX(), bounds.y + hudHeight / 2), Math.PI.toFloat() / 2)
        players.forEach { it.draw(plane) }
    }

    override fun update() = Unit
}