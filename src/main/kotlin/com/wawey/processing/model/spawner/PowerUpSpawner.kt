package com.wawey.processing.model.spawner

import com.wawey.processing.model.entity.powerup.DoubleDamagePowerUp
import com.wawey.processing.model.entity.powerup.DoubleShotPowerUp
import com.wawey.processing.model.entity.powerup.PowerUp
import com.wawey.processing.model.entity.powerup.SpreadPowerUp
import com.wawey.processing.model.vector2D.Vector2Adapter

/**
 *
 * @author Tomas Perez Molina
 */
class DoubleShotSpawner: Spawner<PowerUp> {
    private val observers: MutableList<SpawnObserver<PowerUp>> = mutableListOf()

    override fun spawn(x: Int, y: Int): PowerUp =
            DoubleShotPowerUp(Vector2Adapter.vector(x, y)).also { p -> observers.forEach { it.notifySpawn(p) } }

    override fun addObserver(o: SpawnObserver<PowerUp>) {
        observers.add(o)
    }

    override fun removeObserver(o: SpawnObserver<PowerUp>) {
        observers.remove(o)
    }
}

class SpreadSpawner: Spawner<PowerUp> {
    private val observers: MutableList<SpawnObserver<PowerUp>> = mutableListOf()

    override fun spawn(x: Int, y: Int): PowerUp =
            SpreadPowerUp(Vector2Adapter.vector(x, y)).also { p -> observers.forEach { it.notifySpawn(p) } }

    override fun addObserver(o: SpawnObserver<PowerUp>) {
        observers.add(o)
    }

    override fun removeObserver(o: SpawnObserver<PowerUp>) {
        observers.remove(o)
    }
}

class DoubleDamageSpawner: Spawner<PowerUp> {
    private val observers: MutableList<SpawnObserver<PowerUp>> = mutableListOf()

    override fun spawn(x: Int, y: Int): PowerUp =
            DoubleDamagePowerUp(Vector2Adapter.vector(x, y)).also { p -> observers.forEach { it.notifySpawn(p) } }

    override fun addObserver(o: SpawnObserver<PowerUp>) {
        observers.add(o)
    }

    override fun removeObserver(o: SpawnObserver<PowerUp>) {
        observers.remove(o)
    }
}

class RandomPowerUpSpawner(private val spawners: List<Spawner<PowerUp>>): Spawner<PowerUp> {
    private val observers: MutableList<SpawnObserver<PowerUp>> = mutableListOf()

    override fun spawn(x: Int, y: Int): PowerUp =
            spawners.shuffled().first().spawn(x, y)
                    .also { p -> observers.forEach { it.notifySpawn(p) } }

    override fun addObserver(o: SpawnObserver<PowerUp>) {
        observers.add(o)
    }

    override fun removeObserver(o: SpawnObserver<PowerUp>) {
        observers.remove(o)
    }
}

