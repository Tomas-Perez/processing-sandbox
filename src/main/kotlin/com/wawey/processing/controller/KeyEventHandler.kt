package com.wawey.processing.controller

/**
 *
 * @author Tomas Perez Molina
 */
interface KeyEventHandler {
    fun addObserver(keyCode: Int, observer: KeyEventObserver)
    fun keyPressed(keyCode: Int)
    fun removeObserver(keyCode: Int)
}