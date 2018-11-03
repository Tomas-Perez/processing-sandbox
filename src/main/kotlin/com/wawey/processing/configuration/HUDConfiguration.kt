package com.wawey.processing.configuration

/**
 *
 * @author Tomas Perez Molina
 */

data class HUDConfiguration(
        val backKeyName: String,
        val selectKeyName: String
)

val defaultHUDConfig = HUDConfiguration(
        backKeyName = "BACK",
        selectKeyName = "SELECT"
)