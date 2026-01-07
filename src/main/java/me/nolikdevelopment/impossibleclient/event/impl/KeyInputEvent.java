package me.nolikdevelopment.impossibleclient.event.impl;

import me.nolikdevelopment.impossibleclient.event.Event;

public class KeyInputEvent extends Event {
    private final int key;

    public KeyInputEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
