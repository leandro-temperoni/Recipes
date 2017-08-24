package com.temperoni.recipes.mvp.presenter;

import org.greenrobot.eventbus.EventBus;

/**
 * @author Leandro Temperoni
 */

class BasePresenter {

    EventBus bus;

    BasePresenter(EventBus bus) {
        this.bus = bus;
    }
}
