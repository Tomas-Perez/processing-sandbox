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
        addObserver(configuration.forwardKey) { notifyForward() }
        addObserver(configuration.backwardKey) { notifyBackward() }
        addObserver(configuration.leftKey) { notifyLeft() }
        addObserver(configuration.rightKey) { notifyRight() }
        addObserver(configuration.shootKey) { notifyShoot() }
    }

    override fun deregister(handler: KeyEventHandler) = with(handler) {
        removeObserver(configuration.forwardKey)
        removeObserver(configuration.backwardKey)
        removeObserver(configuration.leftKey)
        removeObserver(configuration.rightKey)
        removeObserver(configuration.shootKey)
    }
}