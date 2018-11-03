package com.wawey.processing.controller

import com.wawey.processing.controller.event.KeyEventHandler
import com.wawey.processing.controller.gameplay.GameplayController
import com.wawey.processing.controller.hud.HUDController
import com.wawey.processing.controller.ship.ShipSpawnController
import com.wawey.processing.model.spawner.SpawnObserver
import com.wawey.processing.model.entity.bullet.Bullet
import com.wawey.processing.model.entity.ship.Ship
import com.wawey.processing.model.entity.ship.ShipObserver
import com.wawey.processing.model.entity.ship.ShootingObserver
import com.wawey.processing.model.score.DestroyVisitor
import com.wawey.processing.model.score.Player
import com.wawey.processing.model.score.PointScout
import com.wawey.processing.model.score.HitVisitor
import com.wawey.processing.view.Plane
import java.awt.Color
import java.util.*

/**
 *
 * @author Tomas Perez Molina
 */
class GameController(private val gameplayController: GameplayController,
                     private val hudController: HUDController,
                     private val shipSpawnController: ShipSpawnController,
                     private val gameOverScreenGen: (Player) -> GameOverScreen,
                     private val shipColors: List<Color>): ScreenController {

    private var routers: List<ControllerRouter> = emptyList()
    private val playerShips = mutableMapOf<UUID, Player>()
    private val pointVisitor = HitVisitor()
    private val destroyVisitor = DestroyVisitor()
    private var respawnedShips = emptyList<Ship>()
    private var gameStarted = false

    override fun render(plane: Plane) {
        gameplayController.render(plane)
        hudController.render(plane)
    }

    override fun update() {
        if (playerShips.filter { it.value.alive }.isEmpty() && gameStarted) {
            routers.forEach {
                it.goToStart()
                val highScorePlayer = playerShips.values.sortedBy { p -> -p.points }.first()
                it.newController(gameOverScreenGen(highScorePlayer))
            }
        }
        shipSpawnController.getNew().forEach {
            gameStarted = true
            val newPlayer = Player(playerShips.size, "Player ${playerShips.size + 1}")
            registerShip(newPlayer, it)
            gameplayController.addShip(it, shipColors[playerShips.size])
            hudController.addPlayer(newPlayer)
            playerShips[it.id] = newPlayer
        }
        respawnedShips.forEach {
            val player = playerShips[it.id]
            if (player != null && player.alive) {
                registerShip(player, it)
                gameplayController.addShip(it, shipColors[player.number])
            }
        }
        respawnedShips = emptyList()
        gameplayController.update()
        hudController.update()
    }

    private fun respawn(s: Ship) {
        val newShip = shipSpawnController.respawnShip(s)
        if (newShip != null) {
            respawnedShips = respawnedShips.plus(newShip)
        }
    }

    private fun registerShip(player: Player, ship: Ship) {
        val shootingObserver = ShootingObserver().apply {
            addObserver(object : SpawnObserver<Bullet> {
                override fun notifySpawn(t: Bullet) {
                    t.addObserver(PointScout(player, pointVisitor, destroyVisitor))
                }
            })
        }
        ship.addObserver(object : ShipObserver {
            override fun notifyShoot(bullets: List<Bullet>) = Unit
            override fun notifyHit(damage: Int) = Unit
            override fun notifyDestroy() = respawn(ship)
        })
        ship.addObserver(shootingObserver)
        ship.addObserver(player)
    }

    override fun register(handler: KeyEventHandler) {
        shipSpawnController.register(handler)
    }

    override fun deregister(handler: KeyEventHandler) {
        shipSpawnController.deregister(handler)
    }

    override fun addObserver(o: ControllerRouter) {
        routers = routers.plus(o)
    }

    override fun removeObserver(o: ControllerRouter) {
        routers = routers.minus(o)
    }
}