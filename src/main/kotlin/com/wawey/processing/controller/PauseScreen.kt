package com.wawey.processing.controller

import com.wawey.processing.controller.event.KeyEventHandler
import com.wawey.processing.controller.event.KeyEventObserver
import com.wawey.processing.model.Bounds
import com.wawey.processing.model.vector2D.Vector2Adapter
import com.wawey.processing.view.DrawColors
import com.wawey.processing.view.Plane

/**
 *
 * @author Tomas Perez Molina
 */
class PauseScreen(private val bounds: Bounds,
                  private val gameScreen: ScreenController,
                  private val selectConfiguration: SelectConfiguration,
                  private val selectKeyName: String,
                  private val backKeyName: String): ScreenController {
    var router: ControllerRouter? = null

    override fun render(plane: Plane) {
        plane.setDrawColors(DrawColors())
        plane.text(
                "PAUSED",
                50,
                Vector2Adapter.vector(bounds.centerX(), bounds.centerY()),
                centered = true
        )
        plane.text(
                "Press $selectKeyName to continue",
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

    override fun register(handler: KeyEventHandler) {
        router?.also {
            handler.addObserver(selectConfiguration.selectKey, object : KeyEventObserver {
                override fun notifyKeyPressed() = it.newController(gameScreen)
            })
        }

    }

    override fun deregister(handler: KeyEventHandler) {
        handler.removeObserver(selectConfiguration.selectKey)
    }

    override fun addRouter(r: ControllerRouter) {
        router = r
    }

    override fun removeRouter() {
        router = null
    }
}