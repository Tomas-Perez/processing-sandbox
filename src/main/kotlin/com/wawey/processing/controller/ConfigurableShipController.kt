package com.wawey.processing.controller

import com.wawey.processing.model.entity.ControllableShip

/**
 *
 * @author Tomas Perez Molina
 */
class ConfigurableShipController(private val ship: ControllableShip, private val configuration: ShipControllerConfiguration): ShipController {
    override fun notifyBackward() = ship.applyAcceleration(2f)

    override fun notifyForward() = ship.applyAcceleration(-2f)

    override fun notifyLeft() = ship.applyRotation(-0.2f)

    override fun notifyRight() = ship.applyRotation(0.2f)

    override fun notifyShoot() = ship.startShooting()

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