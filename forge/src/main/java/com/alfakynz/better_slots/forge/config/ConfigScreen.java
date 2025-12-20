package com.alfakynz.better_slots.forge.config;

import com.alfakynz.better_slots.config.Config;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public class ConfigScreen {

    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.translatable("option.better_slots.settings"))
                .setSavingRunnable(Config::save);

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        var generalCategory = builder.getOrCreateCategory(Component.translatable("option.better_slots.settings.general"));

        record Setting(String key, BooleanSupplier getter, Consumer<Boolean> setter, Boolean default_value) {}

        Setting[] settings = new Setting[] {
                new Setting("enable_mod", () -> Config.get().enable_mod, newValue -> Config.get().enable_mod = newValue, true),
        };

        for (Setting setting : settings) {
            String key = setting.key();
            BooleanSupplier getter = setting.getter();
            Consumer<Boolean> setter = setting.setter();
            Boolean default_value = setting.default_value();

            generalCategory.addEntry(entryBuilder
                    .startBooleanToggle(Component.translatable("option.better_slots.settings." + key), getter.getAsBoolean())
                    .setDefaultValue(default_value)
                    .setSaveConsumer(setter)
                    .build());
        }

        return builder.build();
    }
}