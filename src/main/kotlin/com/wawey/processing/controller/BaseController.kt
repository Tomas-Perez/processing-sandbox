package com.wawey.processing.controller

import com.wawey.processing.util.Cooldown
import com.wawey.processing.controller.event.KeyEventHandler
import com.wawey.processing.controller.event.KeyEventObserver
import com.wawey.processing.view.Plane
import java.util.*


/**
 *
 * @author Tomas Perez Molina
 */
class BaseController(private val handler: KeyEventHandler, initialScreen: ScreenController, configuration: BaseControllerConfiguration): ControllerRouter, AnimationController {
    private val controllers: Stack<ScreenController> = Stack()
    private val debounce = Cooldown(200)

    init {
        newController(initialScreen)
        handler.addObserver(configuration.backKey, object : KeyEventObserver {
            override fun notifyKeyPressed() = debounce {
                back()
            }
        })
    }

    override fun back() {
        if(controllers.size > 1) {
            val oldController = controllers.pop()
            oldController.deregister(handler)
            oldController.removeRouter()
            val newController = controllers.peek()
            newController.addRouter(this)
            newController.register(handler)
        }
    }

    override fun newController(s: ScreenController) {
        if(controllers.size >= 1) {
            val current = controllers.peek()
            current.removeRouter()
            current.deregister(handler)
        }
        s.addRouter(this)
        s.register(handler)
        controllers.push(s)
    }

    override fun goToStart() {
        while (controllers.size > 1) back()
    }

    override fun render(plane: Plane) = controllers.peek().render(plane)

    override fun update() = controllers.peek().update()
}

interface ControllerRouter {
    fun newController(s: ScreenController)
    fun goToStart()
    fun back()
}
