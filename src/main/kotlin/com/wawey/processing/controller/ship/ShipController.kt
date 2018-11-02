package com.wawey.processing.controller.ship

import com.wawey.processing.controller.event.KeyEventController
import com.wawey.processing.model.entity.ControllableShip

/**
 *
 * @author Tomas Perez Molina
 */
interface ShipController: KeyEventController {
    var ship: ControllableShip

    fun notifyBackward()
    fun notifyForward()
    fun notifyLeft()
    fun notifyRight()
    fun notifyShoot()
}