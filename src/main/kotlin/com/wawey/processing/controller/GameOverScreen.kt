package com.wawey.processing.controller

import com.wawey.processing.controller.event.KeyEventHandler
import com.wawey.processing.model.Bounds
import com.wawey.processing.model.score.Player
import com.wawey.processing.model.vector2D.Vector2Adapter
import com.wawey.processing.view.DrawColors
import com.wawey.processing.view.Plane

/**
 *
 * @author Tomas Perez Molina
 */
class GameOverScreen(private val bounds: Bounds,
                     private val backKeyName: String,
                     private val highScorePlayer: Player): ScreenController {

    override fun render(plane: Plane) {
        plane.setDrawColors(DrawColors())
        plane.text(
                "${highScorePlayer.name} WINS",
                50,
                Vector2Adapter.vector(bounds.centerX(), bounds.centerY()),
                centered = true
        )
        plane.text(
                "Score: ${highScorePlayer.points}",
                30,
                Vector2Adapter.vector(bounds.centerX(), bounds.centerY() + 75),
                centered = true
        )
        plane.text(
                "Press $backKeyName to go to Main Menu",
                20,
                Vector2Adapter.vector(bounds.centerX(), bounds.centerY() + 125),
                centered = true
        )
    }

    override fun update() = Unit

    override fun register(handler: KeyEventHandler) = Unit

    override fun deregister(handler: KeyEventHandler) = Unit

    override fun addRouter(r: ControllerRouter) = Unit

    override fun removeRouter() = Unit
}