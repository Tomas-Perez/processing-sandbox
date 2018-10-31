package com.wawey.processing.controller.gameplay

import com.wawey.processing.controller.AnimationController
import com.wawey.processing.model.entity.ship.Ship
import java.awt.Color

/**
 *
 * @author Tomas Perez Molina
 */
interface GameplayController: AnimationController {
    fun addShip(s: Ship, c: Color)
}