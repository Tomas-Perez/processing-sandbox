package com.wawey.processing.controller

import com.wawey.processing.model.SpawnObserver
import com.wawey.processing.model.entity.ship.Ship
import com.wawey.processing.model.World
import com.wawey.processing.model.entity.bullet.Bullet
import com.wawey.processing.model.entity.ship.BaseShipObserver
import com.wawey.processing.view.Plane
import com.wawey.processing.view.paintor.Painter
import com.wawey.processing.view.paintor.SpawnPainter
import com.wawey.processing.view.renderer.Renderer

/**
 *
 * @author Tomas Perez Molina
 */
class StarShipGameplayController(
    private val renderer: Renderer,
    private val world: World,
    private val shipPainter: Painter<Ship>,
    private val bulletSpawnPainter: SpawnPainter<Bullet>): GameplayController {

    private var bulletBuffer: List<Bullet> = emptyList()

    override fun addShip(s: Ship) {
        s.addObserver(BaseShipObserver().apply {
            addObserver(bulletSpawnPainter)
            addObserver(object: SpawnObserver<Bullet> {
                override fun notifySpawn(t: Bullet) {
                    bulletBuffer = bulletBuffer.plus(t)
                }
            })
        })
        renderer.add(shipPainter.draw(s))
        world.addEntity(s)
    }

    override fun render(plane: Plane) = renderer.render(plane)

    override fun update() {
        world.update()
        bulletBuffer.forEach { world.addEntity(it) }
        bulletBuffer = emptyList()
        bulletSpawnPainter.getNew().forEach { renderer.add(it) }
    }
}
