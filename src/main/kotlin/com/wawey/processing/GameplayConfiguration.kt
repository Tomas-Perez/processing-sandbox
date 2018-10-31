package com.wawey.processing

import com.wawey.processing.model.Bounds
import com.wawey.processing.model.vector2D.Vector2Adapter
import com.wawey.processing.model.vector2D.Vector2D

/**
 *
 * @author Tomas Perez Molina
 */
data class GameplayConfiguration(
        val bounds: Bounds,
        val shipSpawnLocations: List<Vector2D>
)

private val bounds = Bounds(2560/2, 1440/2)

private val xCoords = listOf(0, bounds.x, bounds.centerX())
private val yCoords = listOf(0, bounds.y, bounds.centerY())

private val spawnLocations =
        xCoords.map {
            x -> yCoords.map {
            y -> Vector2Adapter.vector(x, y)
        }
        }.flatten()

val defaultGameplayConfig = GameplayConfiguration(
        bounds = bounds,
        shipSpawnLocations = spawnLocations
)
