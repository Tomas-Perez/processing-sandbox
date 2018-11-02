package com.wawey.processing

/**
 *
 * @author Tomas Perez Molina
 */

data class HUDConfiguration(
        val backKeyName: String,
        val selectKeyName: String
)

val defaultHUDConfig = HUDConfiguration(
        backKeyName = "TAB",
        selectKeyName = "ENTER"
)