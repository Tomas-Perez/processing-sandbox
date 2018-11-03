package com.wawey.processing.configuration

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

private val bounds = Bounds(1920, 1080)

private val xCoords = listOf(100, bounds.x - 100, bounds.centerX())
private val yCoords = listOf(100, bounds.y - 100, bounds.centerY())

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
