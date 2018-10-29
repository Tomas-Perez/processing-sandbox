package com.wawey.processing.model

import com.wawey.processing.model.entity.Collider
import com.wawey.processing.model.entity.GameEntity
import edu.austral.starship.base.collision.CollisionEngine

/**
 *
 * @author Tomas Perez Molina
 */
class StarShipWorld(override val bounds: Bounds, private val engine: CollisionEngine<Collider>): World {

    private var entities: List<GameEntity> = emptyList()

    override fun addEntity(entity: GameEntity) {
        entities = entities.plus(entity)
    }

    override fun update() {
        entities = entities
                .asSequence()
                .filterNot { it.destroyed }
                .also {
                    engine.checkCollisions(it.map { x -> x.collider }.toList())
                }
                .onEach { it.update() }
                .toList()
    }
}