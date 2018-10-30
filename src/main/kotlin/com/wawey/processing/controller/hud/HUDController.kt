package com.wawey.processing.controller.hud

import com.wawey.processing.controller.AnimationController
import com.wawey.processing.model.score.Player

/**
 *
 * @author Tomas Perez Molina
 */
interface HUDController: AnimationController {
    fun addPlayer(player: Player)
}