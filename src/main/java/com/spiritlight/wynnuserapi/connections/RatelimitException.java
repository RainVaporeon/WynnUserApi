package com.spiritlight.wynnuserapi.connections;

public class RatelimitException extends Exception {
    private final int coolDown;

    public RatelimitException(String s, int cd) {
        super(s);
        this.coolDown = cd;
    }

    public void timeout() throws InterruptedException {
        Thread.sleep(coolDown * 1000L);
    }

    public int getCoolDown() {
        return this.coolDown;
    }
}
