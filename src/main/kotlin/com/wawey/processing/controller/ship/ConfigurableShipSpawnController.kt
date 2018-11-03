package com.wawey.processing.controller.ship

import com.wawey.processing.Cooldown
import com.wawey.processing.controller.ShipControllerConfiguration
import com.wawey.processing.controller.ShipSpawnControllerConfiguration
import com.wawey.processing.controller.event.KeyEventHandler
import com.wawey.processing.controller.event.KeyEventObserver
import com.wawey.processing.model.entity.ship.Ship
import com.wawey.processing.model.spawner.ShipSpawner
import com.wawey.processing.model.vector2D.Vector2D

/**
 *
 * @author Tomas Perez Molina
 */
class ConfigurableShipSpawnController(
        private val spawnLocations: List<Vector2D>,
        private val shipSpawner: ShipSpawner,
        private val configuration: ShipSpawnControllerConfiguration,
        private val shipConfigurations: List<ShipControllerConfiguration>): ShipSpawnController {

    private var shipsBuffer: List<Ship> = emptyList()
    private var shipControllers: List<ShipController> = emptyList()
    private var handler: KeyEventHandler? = null
    private val debounce = Cooldown(200)
    private var lastLocation = spawnLocations.shuffled().first()

    override fun getNew(): List<Ship> {
        return shipsBuffer.also { shipsBuffer = emptyList() }
    }

    override fun notifySpawnShip() = debounce {
        val handler = this.handler
        if (handler != null && shipControllers.size < shipConfigurations.size) {
            val position = spawnLocations.shuffled().first { it != lastLocation }
            lastLocation = position
            val ship = shipSpawner.spawn(position.x.toInt(), position.y.toInt())
            val controller = ConfigurableShipController(ship, shipConfigurations[shipControllers.size])
            controller.register(handler)
            shipControllers = shipControllers.plus(controller)
            shipsBuffer = shipsBuffer.plus(ship)
        }
    }

    override fun respawnShip(s: Ship): Ship? {
        val handler = this.handler
        if (handler != null) {
            val oldController = shipControllers.firstOrNull { it.ship === s }
            if (oldController != null) {
                val position = spawnLocations.shuffled().first { it != lastLocation }
                lastLocation = position
                val ship = shipSpawner.respawn(s, position.x.toInt(), position.y.toInt())
                oldController.ship = ship
                return ship
            }
        }
        return null
    }

    override fun register(handler: KeyEventHandler) {
        this.handler = handler
        handler.addObserver(configuration.spawnKey, object : KeyEventObserver {
            override fun notifyKeyPressed() = notifySpawnShip()
        })
        shipControllers.forEach { it.register(handler) }
    }

    override fun deregister(handler: KeyEventHandler) {
        if (this.handler == handler) {
            this.handler = null
            handler.removeObserver(configuration.spawnKey)
            shipControllers.forEach { it.deregister(handler) }
        }
    }
}