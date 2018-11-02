package com.wawey.processing

import com.wawey.processing.controller.BaseControllerConfiguration
import com.wawey.processing.controller.SelectConfiguration
import com.wawey.processing.controller.ShipControllerConfiguration
import com.wawey.processing.controller.ShipSpawnControllerConfiguration

/**
 *
 * @author Tomas Perez Molina
 */
data class ControlConfiguration(
        val shipSpawn: ShipSpawnControllerConfiguration,
        val shipControls: List<ShipControllerConfiguration>,
        val baseControl: BaseControllerConfiguration,
        val selectControl: SelectConfiguration
)


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

private val baseControl = BaseControllerConfiguration(
        backKey = JavaKeyEvent.VK_TAB
)

private val selectControl = SelectConfiguration(
        selectKey = JavaKeyEvent.VK_ENTER
)

val defaultControlConfig = ControlConfiguration(
        shipSpawn = spawnControllerConfig,
        shipControls = listOf(player1, player2),
        baseControl = baseControl,
        selectControl = selectControl
)