package com.wawey.processing.controller

import com.wawey.processing.model.Ship

/**
 *
 * @author Tomas Perez Molina
 */
interface GameplayController: AnimationController{
    fun addShip(s: Ship)
}