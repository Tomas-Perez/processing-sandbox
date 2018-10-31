package com.wawey.processing

import java.awt.Color

/**
 *
 * @author Tomas Perez Molina
 */
data class PaintConfiguration(
        val shipColors: List<Color>
)

private val shipColors = listOf(
        Color.RED,
        Color.GREEN,
        Color.BLUE,
        Color.ORANGE,
        Color.YELLOW,
        Color.CYAN
)

val defaultPaintConfig = PaintConfiguration(
        shipColors = shipColors
)