package com.wawey.processing.controller

import com.wawey.processing.controller.event.KeyEventHandler
import com.wawey.processing.controller.gameplay.GameplayController
import com.wawey.processing.controller.hud.HUDController
import com.wawey.processing.model.SpawnObserver
import com.wawey.processing.model.entity.bullet.Bullet
import com.wawey.processing.model.entity.ship.PlayerShipObserver
import com.wawey.processing.model.entity.ship.ShootingObserver
import com.wawey.processing.model.score.Player
import com.wawey.processing.model.score.PointScout
import com.wawey.processing.model.score.PointVisitor
import com.wawey.processing.view.Plane

/**
 *
 * @author Tomas Perez Molina
 */
class GameController(private val gameplayController: GameplayController,
                     private val hudController: HUDController,
                     private val shipSpawnController: ShipSpawnController): ScreenController {

    private val players = mutableListOf<Player>()
    private val pointVisitor = PointVisitor()

    override fun render(plane: Plane) {
        gameplayController.render(plane)
        hudController.render(plane)
    }

    override fun update() {
        shipSpawnController.getNew().forEach {
            val newPlayer = Player("Player ${players.size + 1}")
            players.add(newPlayer)
            val baseObserver = ShootingObserver().apply {
                addObserver(object : SpawnObserver<Bullet> {
                    override fun notifySpawn(t: Bullet) {
                        t.addObserver(PointScout(newPlayer, pointVisitor))
                    }
                })
            }
            it.addObserver(baseObserver)
            it.addObserver(PlayerShipObserver(newPlayer))
            hudController.addPlayer(newPlayer)
            gameplayController.addShip(it)
        }
        gameplayController.update()
        hudController.update()
    }

    override fun register(handler: KeyEventHandler) {
        shipSpawnController.register(handler)
    }

    override fun deregister(handler: KeyEventHandler) {
        shipSpawnController.deregister(handler)
    }
}