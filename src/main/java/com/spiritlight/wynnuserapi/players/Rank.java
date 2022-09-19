package com.spiritlight.wynnuserapi.players;

public enum Rank {
    ADMINISTRATOR("Administrator"),
    MODERATOR("Moderator"),
    BUILDER("Builder"),
    ITEM("Item"),
    GM("Game Master"),
    CMD("CMD"),
    MUSIC("Music"),
    HYBRID("Hybrid"),
    MEDIA("Media"),
    PLAYER("Player");


    final String realName;

    Rank(String realName) {
        this.realName = realName;
    }

    @Override
    public String toString() {
        return realName;
    }
}
