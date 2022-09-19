package com.spiritlight.wynnuserapi.api;

import com.google.gson.*;
import com.spiritlight.wynnuserapi.connections.HttpHandler;
import com.spiritlight.wynnuserapi.connections.HttpResponse;
import com.spiritlight.wynnuserapi.connections.RatelimitException;
import com.spiritlight.wynnuserapi.players.Player;
import com.spiritlight.wynnuserapi.players.PlayerClass;
import com.spiritlight.wynnuserapi.utils.Converter;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;
import java.time.Instant;
import java.util.*;

public class Call {
    public static final String PLAYER_STAT = "https://api.wynncraft.com/v2/player/$PLAYER$/stats";
    public static final String ONLINE_PLAYERS = "https://api.wynncraft.com/public_api.php?action=onlinePlayers";

    /**
     * Utility method to fetch the player data<br>
     * If using UUID, it must be formatted first (That is, with dashes)<br>
     * @param username The username or UUID of player to fetch
     * @return A {@link Player} object containing this player's info
     */
    @CheckReturnValue
    public static @Nullable Player getPlayer(String username) throws RatelimitException {
        final Player ret = new Player();
        final List<PlayerClass> classes = new ArrayList<>();
        HttpResponse response = HttpHandler.get(PLAYER_STAT.replace("$PLAYER$", username));
        if(response.getCode() == 429) {
            throw new RatelimitException("Hit ratelimit!", Integer.parseInt(response.getHeader().get("ratelimit-reset")));
        }
        String parse = response.getBody();
        JsonObject jsonObject = new Gson().fromJson(parse, JsonObject.class);
        if(jsonObject == null) return null;
        JsonObject dataEntry = jsonObject.get("data").getAsJsonArray().get(0).getAsJsonObject();
        JsonObject metaEntry = dataEntry.getAsJsonObject("meta");
        JsonObject locationEntry = metaEntry.get("location").getAsJsonObject();
        JsonObject globalEntry = dataEntry.getAsJsonObject("global");
        JsonArray classEntry = dataEntry.get("classes").getAsJsonArray();
        ret.setJson(jsonObject.deepCopy());
        // Player object
        ret.setUsername(dataEntry.get("username").getAsString());
        ret.setUuid(UUID.fromString(dataEntry.get("uuid").getAsString()));
        ret.setRank(Converter.getRankFromString(dataEntry.get("rank").getAsString()));
        ret.setFirstJoin(Date.from(Instant.parse(metaEntry.get("firstJoin").getAsString())));
        ret.setLastJoin(Date.from(Instant.parse(metaEntry.get("lastJoin").getAsString())));
        ret.setOnline(locationEntry.get("online").getAsBoolean());
        JsonElement j = locationEntry.get("server");
        ret.setWorld(j instanceof JsonNull ? "Offline" : j.getAsString());
        ret.setBlocksWalked(globalEntry.get("blocksWalked").getAsLong());
        ret.setMobsKilled(globalEntry.get("mobsKilled").getAsInt());
        ret.setTotalDiscoveries(globalEntry.get("discoveries").getAsInt());
        // Classes
        for(JsonElement jsonElement : classEntry) {
            JsonObject classBase = jsonElement.getAsJsonObject();
            final PlayerClass pc = new PlayerClass();
            final Map<String,Integer> completionMap = new HashMap<>();
            pc.setName(classBase.get("name").getAsString());
            pc.setTotalLevel(classBase.get("level").getAsInt());
            pc.setBlocksWalked(classBase.get("blocksWalked").getAsLong());
            pc.setMobsKilled(classBase.get("mobsKilled").getAsInt());
            pc.setLoginCount(classBase.get("logins").getAsInt());
            pc.setDeathCount(classBase.get("deaths").getAsInt());
            pc.setPlayTime(classBase.get("playtime").getAsInt());
            pc.setDiscoveries(classBase.get("discoveries").getAsInt());
            JsonArray dungeonCompletions = classBase.getAsJsonObject("dungeons").getAsJsonArray("list");
            for(JsonElement element : dungeonCompletions) {
                JsonObject o = element.getAsJsonObject();
                completionMap.put(o.get("name").getAsString(), o.get("completed").getAsInt());
            }
            JsonArray raidCompletions = classBase.getAsJsonObject("raids").getAsJsonArray("list");
            for(JsonElement element : raidCompletions) {
                JsonObject o = element.getAsJsonObject();
                completionMap.put(o.get("name").getAsString(), o.get("completed").getAsInt());
            }
            pc.setStats(completionMap);
            classes.add(pc);
        }
        ret.setPlayerClasses(classes);
        return ret;
    }

    /**
     * Bulk searches the list of players supplied.
     * @param toSearch The player list to search.
     * @return A Map of &lt;String, Player&gt;. Player may be null if the name is invalid.
     * @throws RatelimitException if the ratelimit was hit. Use {@link RatelimitException#getCoolDown()} to get the cooldown reset time.
     */
    @CheckReturnValue
    public static Map<String, Player> getPlayers(List<String> toSearch) throws RatelimitException {
        final Map<String, Player> ret = new HashMap<>();
        for (String s : toSearch) {
            Player p;
            try {
                p = getPlayer(s);
            } catch (NullPointerException ex) {
                p = null;
            }
            ret.put(s, p);
        }
        return ret;
    }

    /**
     * Utility method, returns an array of online players.<br>
     * {@code maxTries} will be default to 50 if not supplied a value.
     * @param maxTries The maximum "invalid server" error tolerated before returning the value.
     * @return The list of online players.
     * @throws RatelimitException if the ratelimit was hit. Use {@link RatelimitException#getCoolDown()} to get the cooldown reset time.
     */
    @CheckReturnValue
    public static List<String> getOnlinePlayers(int maxTries) throws RatelimitException {
        HttpResponse response = HttpHandler.get(ONLINE_PLAYERS);
        if(response.getCode() == 429) {
            throw new RatelimitException("Ratelimit hit!", Integer.parseInt(response.getHeader().get("ratelimit-reset")));
        }
        final List<String> ret = new ArrayList<>();
        String parse = response.getBody();
        JsonObject object = new Gson().fromJson(parse, JsonObject.class);
        int search = 1;
        while(maxTries > 0) {
            JsonElement e = object.get("WC" + search);
            search++;
            if(e == null) {
                maxTries--;
                continue;
            }
            JsonArray arr = e.getAsJsonArray();
            for(JsonElement element : arr) {
                ret.add(element.getAsString());
            }
        }
        return ret;
    }

    /**
     * Utility method, returns an array of online players.
     * @return The list of online players.
     * @throws RatelimitException if the ratelimit was hit. Use {@link RatelimitException#getCoolDown()} to get the cooldown reset time.
     */
    @CheckReturnValue
    public static List<String> getOnlinePlayers() throws RatelimitException {
        return getOnlinePlayers(50);
    }
}
