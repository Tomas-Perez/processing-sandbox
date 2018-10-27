package com.wawey.processing.controller

import processing.event.KeyEvent

/**
 *
 * @author Tomas Perez Molina
 */


class ProcessingKeyEventAdapter(private val handler: KeyEventHandler) {
    private val pressed = mutableSetOf<Int>()

    fun keyPressed(event: KeyEvent) {
        pressed.add(event.keyCode)
    }

    fun keyReleased(event: KeyEvent) {
        pressed.remove(event.keyCode)
    }

    fun notifyHandler() {
        pressed.forEach { handler.keyPressed(it) }
    }
}