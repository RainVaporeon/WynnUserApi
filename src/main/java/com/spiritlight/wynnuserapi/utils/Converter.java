package com.spiritlight.wynnuserapi.utils;

import com.spiritlight.wynnuserapi.players.Rank;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.spiritlight.wynnuserapi.players.Rank.*;

public class Converter {
    private static final Map<String, Rank> convertMap = new HashMap<String, Rank>() {{
        put("administrator", ADMINISTRATOR);
        put("moderator", MODERATOR);
        put("builder", BUILDER);
        put("item", ITEM);
        put("game master", GM);
        put("cmd", CMD);
        put("music", MUSIC);
        put("hybrid", HYBRID);
        put("media", MEDIA);
        put("player", PLAYER);
    }};

    public static Rank getRankFromString(String toConvert) {
        return convertMap.get(toConvert.toLowerCase(Locale.ROOT));
    }
}
