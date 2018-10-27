package com.wawey.processing.controller

import com.wawey.processing.model.ControllableShip

/**
 *
 * @author Tomas Perez Molina
 */
class ConfigurableShipController(private val ship: ControllableShip, private val configuration: ShipControllerConfiguration): ShipController {
    override fun notifyBackward() {
        ship.applyAcceleration(2f)
    }

    override fun notifyForward() {
        ship.applyAcceleration(-2f)
    }

    override fun notifyLeft() {
        ship.applyRotation(-0.2f)
    }

    override fun notifyRight() {
        ship.applyRotation(0.2f)
    }

    override fun notifyShoot() {
        ship.startShooting()
    }

    override fun register(handler: KeyEventHandler) {
        handler.addObserver(configuration.forwardKey)   {   notifyForward()   }
        handler.addObserver(configuration.backwardKey)  {   notifyBackward()  }
        handler.addObserver(configuration.leftKey)      {   notifyLeft()      }
        handler.addObserver(configuration.rightKey)     {   notifyRight()     }
        handler.addObserver(configuration.shootKey)     {   notifyShoot()     }
    }

    override fun deregister(handler: KeyEventHandler) {
        handler.removeObserver(configuration.forwardKey)
        handler.removeObserver(configuration.backwardKey)
        handler.removeObserver(configuration.leftKey)
        handler.removeObserver(configuration.rightKey)
        handler.removeObserver(configuration.shootKey)
    }
}