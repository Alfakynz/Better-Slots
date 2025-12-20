package com.alfakynz.better_slots;

import com.alfakynz.better_slots.config.BetterSlotsConfig;

import java.nio.file.Path;

public final class BetterSlots {

    public static final String MOD_ID = "better_slots";

    public static void init(Path configDir) {
        BetterSlotsConfig.load(configDir.resolve("better_slots.json"));
    }
}