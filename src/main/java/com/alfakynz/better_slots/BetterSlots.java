package com.alfakynz.better_slots;

import com.alfakynz.better_slots.config.Config;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class BetterSlots implements ModInitializer {
    public static final String MOD_NAME = "Better Slots";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);
    public static final Path configDir = FabricLoader.getInstance().getConfigDir();

    @Override
    public void onInitialize() {
        Config.load(configDir.resolve("better-slots.json"));
    }
}