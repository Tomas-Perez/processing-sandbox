package com.wawey.processing.controller

import com.wawey.processing.model.Ship
import com.wawey.processing.model.StarShipWorld
import com.wawey.processing.model.World
import com.wawey.processing.view.Plane
import com.wawey.processing.view.model.ShipView
import com.wawey.processing.view.renderer.LayeredRenderer
import com.wawey.processing.view.renderer.Renderer

/**
 *
 * @author Tomas Perez Molina
 */
class StarShipGameplayController(private val renderer: Renderer, private val world: World): GameplayController {

    override fun addShip(s: Ship) {
        renderer.add(ShipView(s.shape, s.state))
        world.addEntity(s)
    }

    override fun render(plane: Plane) = renderer.render(plane)

    override fun update() = world.update()
}