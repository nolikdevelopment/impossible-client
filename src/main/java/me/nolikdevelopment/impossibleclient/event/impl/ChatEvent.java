package me.nolikdevelopment.impossibleclient.event.impl;

import me.nolikdevelopment.impossibleclient.event.Event;

public class ChatEvent extends Event {
    private String content;

    public ChatEvent(String content) {
        this.content = content;
    }

    public String getMessage() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
