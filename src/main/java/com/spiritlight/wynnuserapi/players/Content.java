package com.spiritlight.wynnuserapi.players;

@SuppressWarnings("unused")
public class Content {
    private int completed;

    public Content(int completed) {
        this.completed = completed;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }
}