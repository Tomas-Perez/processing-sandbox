package com.wawey.processing.controller

/**
 *
 * @author Tomas Perez Molina
 */
interface KeyEventHandler {
    fun addObserver(keyCode: Int, observer: () -> Unit)
    fun keyPressed(keyCode: Int)
    fun removeObserver(keyCode: Int)
}