package com.wawey.processing.model

import com.wawey.processing.model.entity.GameEntity

/**
 *
 * @author Tomas Perez Molina
 */
class StarShipWorld(_bounds: Bounds): World {

    private var entities: List<GameEntity> = emptyList()

    override val bounds: Bounds = _bounds

    override fun addEntity(entity: GameEntity) {
        entities = entities.plus(entity)
    }

    override fun update() {
        entities = entities
                .asSequence()
                .filterNot { it.destroyed }
                .onEach { it.update() }
                .toList()
    }
}