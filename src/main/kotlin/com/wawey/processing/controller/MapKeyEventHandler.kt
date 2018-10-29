package com.wawey.processing.controller


/**
 *
 * @author Tomas Perez Molina
 */
class MapKeyEventHandler: KeyEventHandler {
    private val observers: MutableMap<Int, KeyEventObserver> = mutableMapOf()

    override fun addObserver(keyCode: Int, observer:KeyEventObserver) {
        observers[keyCode] = observer
    }

    override fun keyPressed(keyCode: Int) {
        observers[keyCode]?.notifyKeyPressed()
    }

    override fun removeObserver(keyCode: Int) {
        observers.remove(keyCode)
    }
}