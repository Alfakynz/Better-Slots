package com.alfakynz.better_slots;

import com.alfakynz.better_slots.config.Config;

import java.nio.file.Path;

public final class BetterSlots {

    public static final String MOD_ID = Constants.MOD_ID;

    public static void init(Path configDir) {
        Config.load(configDir.resolve("better_slots.json"));
    }
}