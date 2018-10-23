package com.wawey.processing.spawner;

/**
 * @author Tomas Perez Molina
 */
public interface Observable<T> {
    void addObserver(T observer);
    void removeObserver(T observer);
}
