package com.wawey.processing.controller


/**
 *
 * @author Tomas Perez Molina
 */
class MapKeyEventHandler: KeyEventHandler {
    private val observers: MutableMap<Int, () -> Unit> = mutableMapOf()

    override fun addObserver(keyCode: Int, observer: () -> Unit) {
        observers[keyCode] = observer
    }

    override fun keyPressed(keyCode: Int) {
        observers[keyCode]?.invoke()
    }

    override fun removeObserver(keyCode: Int) {
        observers.remove(keyCode)
    }
}