package com.wawey.processing.model

/**
 *
 * @author Tomas Perez Molina
 */
class StarShipWorld(private val _boundsX: Int, private val _boundsY: Int): World {

    private var entities: List<GameEntity> = emptyList()

    override val boundsX: Int
        get() = _boundsX
    override val boundsY: Int
        get() = _boundsY

    override fun addEntity(entity: GameEntity) {
        entities = entities.plus(entity)
    }

    override fun update() {
        entities = entities
                .asSequence()
                .filterNot { it.isDestroyed() }
                .onEach { it.update() }
                .toList()
    }
}