package com.wawey.processing.controller.ship

import com.wawey.processing.controller.ShipControllerConfiguration
import com.wawey.processing.controller.event.KeyEventHandler
import com.wawey.processing.controller.event.KeyEventObserver
import com.wawey.processing.model.entity.ControllableShip

/**
 *
 * @author Tomas Perez Molina
 */
class ConfigurableShipController(override var ship: ControllableShip, private val configuration: ShipControllerConfiguration): ShipController {
    private val acceleration = 1.8f
    private val rotation = 0.15f

    override fun notifyBackward() = ship.applyAcceleration(acceleration)

    override fun notifyForward() = ship.applyAcceleration(-acceleration)

    override fun notifyRight() = ship.applyRotation(rotation)

    override fun notifyLeft() = ship.applyRotation(-rotation)

    override fun notifyShoot() = ship.shoot()

    override fun register(handler: KeyEventHandler) = with(handler) {
        addObserver(configuration.forwardKey,   object : KeyEventObserver {
            override fun notifyKeyPressed() = notifyForward()
        })
        addObserver(configuration.backwardKey,  object : KeyEventObserver {
            override fun notifyKeyPressed() = notifyBackward()
        })
        addObserver(configuration.leftKey,      object : KeyEventObserver {
            override fun notifyKeyPressed() = notifyLeft()
        })
        addObserver(configuration.rightKey,     object : KeyEventObserver {
            override fun notifyKeyPressed() = notifyRight()
        })
        addObserver(configuration.shootKey,     object : KeyEventObserver {
            override fun notifyKeyPressed() = notifyShoot()
        })
    }

    override fun deregister(handler: KeyEventHandler) = with(handler) {
        removeObserver(configuration.forwardKey)
        removeObserver(configuration.backwardKey)
        removeObserver(configuration.leftKey)
        removeObserver(configuration.rightKey)
        removeObserver(configuration.shootKey)
    }
}