package com.wawey.processing.util

/**
 *
 * @author Tomas Perez Molina
 */
class Cooldown(private val timeout: Int) {
    private var lastCall = 0L

    operator fun <T> invoke(default: T, function: () -> T): T {
        val current = System.currentTimeMillis()
        return if (current - lastCall > timeout) {
            lastCall = current
            function()
        } else default
    }

    operator fun invoke(function: () -> Unit) = invoke(Unit) {
        function()
    }
}