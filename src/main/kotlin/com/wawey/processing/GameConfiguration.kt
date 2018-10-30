package com.wawey.processing

import com.wawey.processing.controller.ShipControllerConfiguration
import com.wawey.processing.controller.ShipSpawnControllerConfiguration

/**
 *
 * @author Tomas Perez Molina
 */
data class GameConfiguration(val spawnControlConfig: ShipSpawnControllerConfiguration,
                             val shipConfigs: List<ShipControllerConfiguration>)


private val spawnControllerConfig = ShipSpawnControllerConfiguration(JavaKeyEvent.VK_0)
private val player1 = ShipControllerConfiguration(
        forwardKey = JavaKeyEvent.VK_W,
        backwardKey = JavaKeyEvent.VK_S,
        leftKey = JavaKeyEvent.VK_A,
        rightKey = JavaKeyEvent.VK_D,
        shootKey = JavaKeyEvent.VK_SPACE
)

private val player2 = ShipControllerConfiguration(
        forwardKey = JavaKeyEvent.VK_UP,
        backwardKey = JavaKeyEvent.VK_DOWN,
        leftKey = JavaKeyEvent.VK_LEFT,
        rightKey = JavaKeyEvent.VK_RIGHT,
        shootKey = JavaKeyEvent.VK_SHIFT
)


val default = GameConfiguration(
        spawnControlConfig = spawnControllerConfig,
        shipConfigs = listOf(player1, player2)
)