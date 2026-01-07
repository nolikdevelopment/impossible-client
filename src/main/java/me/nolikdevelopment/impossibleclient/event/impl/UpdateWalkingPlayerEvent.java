package me.nolikdevelopment.impossibleclient.event.impl;

import me.nolikdevelopment.impossibleclient.event.Event;
import me.nolikdevelopment.impossibleclient.event.Stage;

public class UpdateWalkingPlayerEvent extends Event {
    private final Stage stage;

    public UpdateWalkingPlayerEvent(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }
}
