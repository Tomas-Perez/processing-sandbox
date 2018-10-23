package com.wawey.processing.spawner;

/**
 * @author Tomas Perez Molina
 */
public interface SpawnObserver<T> {
    void notifySpawn(T t);
}
