package com.wawey.processing.model.score

/**
 *
 * @author Tomas Perez Molina
 */
class Player(val name: String, var points: Int = 0, hp: Int = 100, var lives: Int = 3) {
    private var _hp = hp

    var hp: Int
        get() = _hp
        set(value) {
            _hp = value
            if(_hp <= 0) --lives
        }
}