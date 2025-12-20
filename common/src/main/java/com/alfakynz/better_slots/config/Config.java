package com.alfakynz.better_slots.config;

import com.alfakynz.better_slots.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.resources.ResourceLocation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class Config {

    public boolean enable_mod = true;
    public Set<String> items = new HashSet<>();

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static Config INSTANCE;

    private static Path CONFIG_PATH;

    private static final Set<String> DEFAULT_ITEMS = Set.of(
            "minecraft:torch",
            "minecraft:soul_torch",
            "minecraft:lantern",
            "minecraft:soul_lantern",
            "minecraft:candle"
    );

    public static Config get() {
        return INSTANCE;
    }

    public static void load(Path configPath) {
        CONFIG_PATH = configPath;

        try {
            if (Files.notExists(configPath)) {
                INSTANCE = new Config();
                INSTANCE.items.addAll(DEFAULT_ITEMS);

                Files.createDirectories(configPath.getParent());
                save();
                return;
            }

            INSTANCE = GSON.fromJson(Files.readString(configPath), Config.class);

            if (INSTANCE.items == null) {
                INSTANCE.items = new HashSet<>(DEFAULT_ITEMS);
            }

        } catch (IOException e) {
            INSTANCE = new Config();
            Constants.LOG.error("Failed to load config file {}", configPath, e);
        }
    }

    public static void save() {
        if (CONFIG_PATH == null || INSTANCE == null) return;

        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            Files.writeString(CONFIG_PATH, GSON.toJson(INSTANCE));
        } catch (IOException e) {
            Constants.LOG.error("Failed to save config file {}", CONFIG_PATH, e);
        }
    }

    public boolean isItemAllowed(ResourceLocation id) {
        return enable_mod && items.contains(id.toString());
    }
}