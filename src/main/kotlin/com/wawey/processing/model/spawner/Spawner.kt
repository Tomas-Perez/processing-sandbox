package com.wawey.processing.model.spawner

import com.wawey.processing.model.entity.Observable

/**
 *
 * @author Tomas Perez Molina
 */
interface Spawner<T>: Observable<SpawnObserver<T>> {
    fun spawn(x: Int, y: Int): T
}

interface SpawnObserver<T> {
    fun notifySpawn(t: T)
}