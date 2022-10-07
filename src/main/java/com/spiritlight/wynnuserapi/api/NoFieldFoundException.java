package com.spiritlight.wynnuserapi.api;

import com.google.gson.JsonObject;

public class NoFieldFoundException extends RuntimeException {
    private final JsonObject cause;

    public NoFieldFoundException(String s) {
        super(s);
        this.cause = null;
    }

    public NoFieldFoundException(String s, JsonObject j) {
        super(s);
        this.cause = j;
    }

    public JsonObject getJsonCause() {
        return this.cause;
    }
}
