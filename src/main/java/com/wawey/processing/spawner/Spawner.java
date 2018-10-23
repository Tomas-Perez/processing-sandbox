package com.wawey.processing.spawner;

/**
 * @author Tomas Perez Molina
 */
public interface Spawner<T> extends Observable<SpawnObserver<T>> {
    void addObserver(SpawnObserver<T> observer);
    void removeObserver(SpawnObserver<T> observer);
}
