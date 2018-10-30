package com.wawey.processing.controller

/**
 *
 * @author Tomas Perez Molina
 */

data class ShipControllerConfiguration(
        val backwardKey: Int,
        val forwardKey: Int,
        val leftKey: Int,
        val rightKey: Int,
        val shootKey: Int
)

data class ShipSpawnControllerConfiguration(
        val spawnKey: Int
)