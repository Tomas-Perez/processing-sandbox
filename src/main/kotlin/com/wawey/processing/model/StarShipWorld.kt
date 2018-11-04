package com.wawey.processing.model

import com.wawey.processing.model.collision.CollisionEngine
import com.wawey.processing.model.entity.Collider
import com.wawey.processing.model.entity.GameEntity

/**
 *
 * @author Tomas Perez Molina
 */
class StarShipWorld(private val engine: CollisionEngine<Collider>): World {

    private var entities: List<GameEntity> = emptyList()

    override fun addEntity(entity: GameEntity) {
        entities = entities.plus(entity)
    }

    override fun update() {
        entities = entities
                .asSequence()
                .filterNot { it.state.destroyed }
                .also { engine.checkCollisions(it.map { x -> x.collider }.toList()) }
                .onEach { it.update() }
                .toList()
    }
}