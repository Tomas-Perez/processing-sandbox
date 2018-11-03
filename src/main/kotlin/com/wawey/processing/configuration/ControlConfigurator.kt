package com.wawey.processing.configuration

import com.wawey.processing.controller.BaseControllerConfiguration
import com.wawey.processing.controller.SelectConfiguration
import com.wawey.processing.controller.ShipControllerConfiguration
import com.wawey.processing.controller.ShipSpawnControllerConfiguration
import com.wawey.processing.util.tryOrDefault
import org.ini4j.IniPreferences
import java.io.File
import org.ini4j.Ini
import java.lang.Exception
import java.lang.RuntimeException
import java.util.*
import java.util.prefs.Preferences


/**
 *
 * @author Tomas Perez Molina
 */
interface ControlConfigurator {
    val controlConfiguration: ControlConfiguration
}

object StringToKeyCode {

    private val nonCharMap: Map<String, Int> = mutableMapOf(
            Pair("TAB", JavaKeyEvent.VK_TAB),
            Pair("SPACE", JavaKeyEvent.VK_SPACE),
            Pair("ESC", JavaKeyEvent.VK_ESCAPE),
            Pair("ENTER", JavaKeyEvent.VK_ENTER),
            Pair("ALT", JavaKeyEvent.VK_ALT),
            Pair("CTRL", JavaKeyEvent.VK_CONTROL),
            Pair("SHIFT", JavaKeyEvent.VK_SHIFT),
            Pair("UP", JavaKeyEvent.VK_UP),
            Pair("LEFT", JavaKeyEvent.VK_LEFT),
            Pair("RIGHT", JavaKeyEvent.VK_RIGHT),
            Pair("DOWN", JavaKeyEvent.VK_DOWN)
    )

    fun get(s: String): Int? {
        val upper = s.toUpperCase()
        return if (upper.length > 1) {
            nonCharMap[s]
        } else {
            upper[0].toInt()
        }
    }

    fun getOrThrow(s: String): Int = get(s) ?: throw ConfigException("$s is not a valid character")
}

class ConfigException(override val message: String): RuntimeException(message)

class INIControlConfigurator(fileName: String, private val default: ControlConfiguration): ControlConfigurator {
    override val controlConfiguration: ControlConfiguration

    init {
        val ini = Ini(File(fileName))
        val prefs = IniPreferences(ini)

        controlConfiguration = ControlConfiguration(
                shipSpawn = getSpawnConfig(prefs),
                shipControls = getShipControls(prefs),
                baseControl = getBaseControls(prefs),
                selectControl = getSelectControls(prefs)
        )
    }

    private fun getSpawnConfig(prefs: Preferences): ShipSpawnControllerConfiguration =
            tryOrDefault(default.shipSpawn) {
                ShipSpawnControllerConfiguration(
                        spawnKey = getFieldKeyCode("spawnShip", prefs.node("MENU"))
                )
            }

    private fun getShipControls(prefs: Preferences): List<ShipControllerConfiguration> =
            tryOrDefault(default.shipControls) {
                var counter = 1
                var currentConfNode = prefs.node("PLAYER_$counter")
                val resultList = mutableListOf<ShipControllerConfiguration>()
                while (currentConfNode.keys().isNotEmpty()) {
                    val playerConf = ShipControllerConfiguration(
                            forwardKey = getFieldKeyCode("up", currentConfNode),
                            backwardKey = getFieldKeyCode("down", currentConfNode),
                            leftKey = getFieldKeyCode("left", currentConfNode),
                            rightKey = getFieldKeyCode("right", currentConfNode),
                            shootKey = getFieldKeyCode("shoot", currentConfNode)
                    )
                    resultList.add(playerConf)
                    counter++
                    currentConfNode = prefs.node("PLAYER_$counter")
                }
                resultList
            }

    private fun getBaseControls(prefs: Preferences): BaseControllerConfiguration =
            tryOrDefault(default.baseControl) {
                BaseControllerConfiguration(
                        backKey = getFieldKeyCode("back", prefs.node("MENU"))
                )
            }

    private fun getSelectControls(prefs: Preferences): SelectConfiguration =
            tryOrDefault(default.selectControl) {
                SelectConfiguration(
                        selectKey = getFieldKeyCode("select", prefs.node("MENU"))
                )
            }

    private fun getFieldKeyCode(field: String, prefs: Preferences): Int {
        val s = prefs.get(field, null) ?: throw ConfigException("$field is null")
        return StringToKeyCode.getOrThrow(s)
    }
}