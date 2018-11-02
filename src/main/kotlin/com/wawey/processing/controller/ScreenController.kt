package com.wawey.processing.controller

import com.wawey.processing.controller.event.KeyEventController
import com.wawey.processing.model.entity.Observable

/**
 *
 * @author Tomas Perez Molina
 */
interface ScreenController: AnimationController, KeyEventController, Observable<ControllerCreationObserver>