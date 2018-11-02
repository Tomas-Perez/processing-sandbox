package com.wawey.processing.controller

import com.wawey.processing.Debounce
import com.wawey.processing.controller.event.KeyEventHandler
import com.wawey.processing.controller.event.KeyEventObserver
import com.wawey.processing.view.Plane
import java.util.*


/**
 *
 * @author Tomas Perez Molina
 */
class BaseController(private val handler: KeyEventHandler, initialScreen: ScreenController, configuration: BaseControllerConfiguration): GeneralController, ControllerCreationObserver, AnimationController {
    private val controllers: Stack<ScreenController> = Stack()
    private val debounce = Debounce(200)

    init {
        notifyNewController(initialScreen)
        handler.addObserver(configuration.backKey, object : KeyEventObserver {
            override fun notifyKeyPressed() = debounce {
                notifyBack()
            }
        })
    }

    override fun notifyBack() {
        if(controllers.size > 1) {
            val oldController = controllers.pop()
            oldController.deregister(handler)
            oldController.removeObserver(this)
            val newController = controllers.peek()
            newController.addObserver(this)
            newController.register(handler)
        }
    }

    override fun notifyNewController(s: ScreenController) {
        if(controllers.size >= 1) {
            val current = controllers.peek()
            current.removeObserver(this)
            current.deregister(handler)
        }
        s.addObserver(this)
        s.register(handler)
        controllers.push(s)
    }

    override fun render(plane: Plane) = controllers.peek().render(plane)

    override fun update() = controllers.peek().update()
}


interface GeneralController {
    fun notifyBack()
}

interface ControllerCreationObserver {
    fun notifyNewController(s: ScreenController)
}