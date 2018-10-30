package com.wawey.processing.controller

import com.wawey.processing.controller.event.KeyEventController
import com.wawey.processing.model.entity.ship.Ship

/**
 *
 * @author Tomas Perez Molina
 */
interface ShipSpawnController: KeyEventController {
    fun getNew(): List<Ship>
    fun notifySpawnShip()
}