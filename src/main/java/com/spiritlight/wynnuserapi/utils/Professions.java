package com.spiritlight.wynnuserapi.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.spiritlight.wynnuserapi.players.PlayerClass;

import java.util.Objects;

public class Professions {
    /**
     * Note: Combat is excluded from professions, please use {@link PlayerClass#getLevel()} for it.
     * @param o The <i>professions</i> JsonObject
     * @return A complete set of professions, or an exception.
     */
    public static Professions fromJson(JsonObject o) {
        return new Professions(
                getPart(o.get("alchemism")),
                getPart(o.get("armouring")),
                getPart(o.get("cooking")),
                getPart(o.get("farming")),
                getPart(o.get("fishing")),
                getPart(o.get("jeweling")),
                getPart(o.get("mining")),
                getPart(o.get("scribing")),
                getPart(o.get("tailoring")),
                getPart(o.get("weaponsmithing")),
                getPart(o.get("woodcutting")),
                getPart(o.get("woodworking"))
        );
    }

    private static Type getPart(JsonObject o) {
        JsonElement e = o.get("level");
        JsonElement x = o.get("xp");
        int level = (e instanceof JsonNull ? -1 : e.getAsInt());
        float xp = (x instanceof JsonNull ? -1.0F : x.getAsFloat());
        return new Type(level, xp);
    }

    private static Type getPart(JsonElement e) {
        return getPart((JsonObject) e);
    }

    private final Type alchemism;
    private final Type armouring;
    private final Type cooking;
    private final Type farming;
    private final Type fishing;
    private final Type jeweling;
    private final Type mining;
    private final Type scribing;
    private final Type tailoring;
    private final Type weaponsmithing;
    private final Type woodcutting;
    private final Type woodworking;

    public Professions(Type alchemism, Type armouring, Type cooking, Type farming, Type fishing, Type jeweling, Type mining, Type scribing, Type tailoring, Type weaponsmithing, Type woodcutting, Type woodworking) {
        this.alchemism = alchemism;
        this.armouring = armouring;
        this.cooking = cooking;
        this.farming = farming;
        this.fishing = fishing;
        this.jeweling = jeweling;
        this.mining = mining;
        this.scribing = scribing;
        this.tailoring = tailoring;
        this.weaponsmithing = weaponsmithing;
        this.woodcutting = woodcutting;
        this.woodworking = woodworking;
    }

    public Type getAlchemism() {
        return alchemism;
    }

    public Type getArmouring() {
        return armouring;
    }

    public Type getCooking() {
        return cooking;
    }

    public Type getFarming() {
        return farming;
    }

    public Type getFishing() {
        return fishing;
    }

    public Type getJeweling() {
        return jeweling;
    }

    public Type getMining() {
        return mining;
    }

    public Type getScribing() {
        return scribing;
    }

    public Type getTailoring() {
        return tailoring;
    }

    public Type getWeaponsmithing() {
        return weaponsmithing;
    }

    public Type getWoodcutting() {
        return woodcutting;
    }

    public Type getWoodworking() {
        return woodworking;
    }

    // internal: sorted list
    private static final String[] professionList = {
            "alchemism",
            "armouring",
            "cooking",
            "farming",
            "fishing",
            "jeweling",
            "mining",
            "scribing",
            "tailoring",
            "weaponsmithing",
            "woodcutting",
            "woodworking"
    };

    public static class Type {
        private final int level;
        private final float progress;

        public Type(int level, float progress) {
            this.level = level;
            this.progress = progress;
        }

        public int getLevel() {
            return level;
        }

        /**
         *
         * @return -1 if progress is null by any chance.
         */
        public float getProgress() {
            return progress;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professions that = (Professions) o;
        return alchemism.equals(that.alchemism) && armouring.equals(that.armouring) && cooking.equals(that.cooking) && farming.equals(that.farming) && fishing.equals(that.fishing) && jeweling.equals(that.jeweling) && mining.equals(that.mining) && scribing.equals(that.scribing) && tailoring.equals(that.tailoring) && weaponsmithing.equals(that.weaponsmithing) && woodcutting.equals(that.woodcutting) && woodworking.equals(that.woodworking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alchemism, armouring, cooking, farming, fishing, jeweling, mining, scribing, tailoring, weaponsmithing, woodcutting, woodworking);
    }

    @Override
    public String toString() {
        return "Professions{" +
                "alchemism=" + alchemism +
                ", armouring=" + armouring +
                ", cooking=" + cooking +
                ", farming=" + farming +
                ", fishing=" + fishing +
                ", jeweling=" + jeweling +
                ", mining=" + mining +
                ", scribing=" + scribing +
                ", tailoring=" + tailoring +
                ", weaponsmithing=" + weaponsmithing +
                ", woodcutting=" + woodcutting +
                ", woodworking=" + woodworking +
                '}';
    }
}
