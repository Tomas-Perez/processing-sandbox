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

class StartScreen(private val bounds: Bounds,
                  private val selectConfiguration: SelectConfiguration,
                  private val genGamePausePair: () -> GamePausePair,
                  private val selectKeyName: String): ScreenController {
    private var observers: List<ControllerCreationObserver> = emptyList()

    override fun render(plane: Plane) {
        plane.setDrawColors(DrawColors())
        plane.text(
                "STARSHIPS",
                50,
                Vector2Adapter.vector(bounds.centerX(), bounds.centerY()),
                centered = true
        )
        plane.text(
                "Press $selectKeyName to start the game",
                30,
                Vector2Adapter.vector(bounds.centerX(), bounds.centerY() + 75),
                centered = true
        )
    }

    override fun update() = Unit

    override fun register(handler: KeyEventHandler) {
        handler.addObserver(selectConfiguration.selectKey, object : KeyEventObserver {
            override fun notifyKeyPressed() {
                val pair = genGamePausePair()
                observers.forEach {
                    it.notifyNewController(pair.pauseScreen)
                    it.notifyNewController(pair.gameScreen)
                }
            }
        })
    }

    override fun deregister(handler: KeyEventHandler) {
        handler.removeObserver(selectConfiguration.selectKey)
    }

    override fun addObserver(o: ControllerCreationObserver) {
        observers = observers.plus(o)
    }

    override fun removeObserver(o: ControllerCreationObserver) {
        observers = observers.minus(o)
    }
}

data class GamePausePair(val pauseScreen: ScreenController, val gameScreen: ScreenController)