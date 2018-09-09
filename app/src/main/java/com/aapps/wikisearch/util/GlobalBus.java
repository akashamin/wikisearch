package com.aapps.wikisearch.util;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public class GlobalBus {

    private static Bus mBus;

    public static Bus getBus() {
        if (mBus == null) {
            mBus = new Bus(ThreadEnforcer.ANY);
        }
        return mBus;
    }
}
