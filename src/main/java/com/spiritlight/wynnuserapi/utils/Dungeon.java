package com.spiritlight.wynnuserapi.utils;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dungeon {
    private static final String c = "Corrupted ";
    public static final String DS = "Decrepit Sewers";
    public static final String DECREPIT_SEWERS = DS;
    public static final String IP = "Infested Pit";
    public static final String INFESTED_PIT = IP;
    public static final String LS = "Lost Sanctuary";
    public static final String LOST_SANCTUARY = LS;
    public static final String UC = "Underworld Crypt";
    public static final String UNDERWORLD_CRYPT = UC;
    public static final String SST = "Sand-Swept Tomb";
    public static final String SAND_SWEPT_TOMB = SST;
    public static final String IB = "Ice Barrows";
    public static final String ICE_BARROWS = IB;
    public static final String UR = "Undergrowth Ruins";
    public static final String UNDERGROWTH_RUINS = UR;
    public static final String GG = "Galleon's Graveyard";
    public static final String GALLEONS_GRAVEYARD = GG;
    public static final String FF = "Fallen Factory";
    public static final String FALLEN_FACTORY = FF;
    public static final String CDS = c + DS;
    public static final String CORRUPTED_DECREPIT_SEWERS = CDS;
    public static final String CIP = c + IP;
    public static final String CORRUPTED_INFESTED_PIT = CIP;
    public static final String CLS = c + LS;
    public static final String CORRUPTED_LOST_SANCTUARY = CLS;
    public static final String CUC = c + UC;
    public static final String CORRUPTED_UNDERWORLD_CRYPT = CUC;
    public static final String CSST = c + SST;
    public static final String CORRUPTED_SAND_SWEPT_TOMB = CSST;
    public static final String CIB = c + IB;
    public static final String CORRUPTED_ICE_BARROWS = CIB;
    public static final String CUR = c + UR;
    public static final String CORRUPTED_UNDERGROWTH_RUINS = CUR;
    /**
     * @apiNote Before this dungeon officially releases, any attempts to use this entry will result in errors.
     */
    public static final String CGG = c + GG;
    /**
     * @apiNote Before this dungeon officially releases, any attempts to use this entry will result in errors.
     */
    public static final String CORRUPTED_GALLEONS_GRAVEYARD = CGG;
    /**
     * @apiNote Before this dungeon officially releases, any attempts to use this entry will result in errors.
     */
    public static final String CFF = c + FF;
    /**
     * @apiNote Before this dungeon officially releases, any attempts to use this entry will result in errors.
     */
    public static final String CORRUPTED_FALLEN_FACTORY = CFF;

    public static final String EO = "Eldritch Outlook";

    public static final List<String> REGULAR_DUNGEONS = ImmutableList.of(DS, IP, LS, UC, SST, IB, UR, GG, FF, EO);
    public static final List<String> CORRUPTED_DUNGEONS = ImmutableList.of(CDS, CIP, CLS, CUC, CSST, CIB, CUR, CGG);
    public static final List<String> ALL_DUNGEONS;
    private static final List<String> ad = new ArrayList<>();

    static {
        ad.addAll(REGULAR_DUNGEONS);
        ad.addAll(CORRUPTED_DUNGEONS);
        ALL_DUNGEONS = ImmutableList.of(Arrays.toString(ad.toArray()));
    }
}
