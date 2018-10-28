package com.wawey.processing.model.entity

/**
 *
 * @author Tomas Perez Molina
 */
interface Observable<T> {
    fun addObserver(o: T)
    fun removeObserver(o: T)
}