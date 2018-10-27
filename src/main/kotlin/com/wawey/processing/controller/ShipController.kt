package com.wawey.processing.controller

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