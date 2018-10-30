package com.wawey.processing.controller.event

/**
 *
 * @author Tomas Perez Molina
 */
interface KeyEventController {
    fun register(handler: KeyEventHandler)
    fun deregister(handler: KeyEventHandler)
}