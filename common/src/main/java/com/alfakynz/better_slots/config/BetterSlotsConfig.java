package com.alfakynz.better_slots.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.resources.ResourceLocation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class BetterSlotsConfig {

    public boolean enable = true;
    public Set<String> items = new HashSet<>();

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static BetterSlotsConfig INSTANCE;

    private static final Set<String> DEFAULT_ITEMS = Set.of(
            "minecraft:torch",
            "minecraft:soul_torch",
            "minecraft:lantern",
            "minecraft:soul_lantern",
            "minecraft:candle"
    );

    public static BetterSlotsConfig get() {
        return INSTANCE;
    }

    public static void load(Path configPath) {
        try {
            if (Files.notExists(configPath)) {
                INSTANCE = new BetterSlotsConfig();
                INSTANCE.items.addAll(DEFAULT_ITEMS);
                Files.createDirectories(configPath.getParent());
                Files.writeString(configPath, GSON.toJson(INSTANCE));
                return;
            }

            INSTANCE = GSON.fromJson(Files.readString(configPath), BetterSlotsConfig.class);
        } catch (IOException e) {
            INSTANCE = new BetterSlotsConfig();
            e.printStackTrace();
        }
    }

    public boolean isItemAllowed(ResourceLocation id) {
        return enable && items.contains(id.toString());
    }
}