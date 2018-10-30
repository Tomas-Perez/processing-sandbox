package com.wawey.processing.controller

import com.wawey.processing.controller.event.KeyEventController

/**
 *
 * @author Tomas Perez Molina
 */
interface ShipController: KeyEventController {
    fun notifyBackward()
    fun notifyForward()
    fun notifyLeft()
    fun notifyRight()
    fun notifyShoot()
}