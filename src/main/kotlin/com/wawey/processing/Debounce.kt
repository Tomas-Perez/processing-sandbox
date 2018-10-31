package com.wawey.processing

/**
 *
 * @author Tomas Perez Molina
 */
class Debounce(private val timeout: Int) {
    private var lastCall = System.currentTimeMillis()

    operator fun <T> invoke(default: T, function: () -> T): T {
        val current = System.currentTimeMillis()
        return if (current - lastCall > timeout) {
            lastCall = current
            function()
        } else default
    }

    operator fun invoke(function: () -> Unit) {
        val current = System.currentTimeMillis()
        if (current - lastCall > timeout) {
            lastCall = current
            function()
        }
    }
}