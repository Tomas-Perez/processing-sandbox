package com.wawey.processing.controller

import com.wawey.processing.Debounce
import com.wawey.processing.controller.event.KeyEventHandler
import com.wawey.processing.controller.event.KeyEventObserver
import com.wawey.processing.model.Bounds
import com.wawey.processing.model.Spawner
import com.wawey.processing.model.entity.ship.Ship

/**
 *
 * @author Tomas Perez Molina
 */
class ConfigurableShipSpawnController(
        private val bounds: Bounds,
        private val shipSpawner: Spawner<Ship>,
        private val configuration: ShipSpawnControllerConfiguration,
        private val shipConfigurations: List<ShipControllerConfiguration>): ShipSpawnController {

    private var shipsBuffer: List<Ship> = emptyList()
    private var shipControllers: List<ShipController> = emptyList()
    private var handler: KeyEventHandler? = null
    private val debounce = Debounce(200)

    override fun getNew(): List<Ship> {
        return shipsBuffer.also { shipsBuffer = emptyList() }
    }

    override fun notifySpawnShip() = debounce {
        val handler = this.handler
        if (handler != null && shipControllers.size < shipConfigurations.size) {
            val ship = shipSpawner.spawn(bounds.centerX(), bounds.centerY())
            val controller = ConfigurableShipController(ship, shipConfigurations[shipControllers.size])
            controller.register(handler)
            shipControllers = shipControllers.plus(controller)
            shipsBuffer = shipsBuffer.plus(ship)
        }
    }

    override fun register(handler: KeyEventHandler) {
        this.handler = handler
        handler.addObserver(configuration.spawnKey, object : KeyEventObserver {
            override fun notifyKeyPressed() = notifySpawnShip()
        })
    }

    override fun deregister(handler: KeyEventHandler) {
        if (this.handler == handler) {
            this.handler = null
            handler.removeObserver(configuration.spawnKey)
            shipControllers.forEach { it.deregister(handler) }
        }
    }
}