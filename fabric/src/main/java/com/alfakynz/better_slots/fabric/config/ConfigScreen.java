package com.alfakynz.better_slots.fabric.config;

import com.alfakynz.better_slots.config.Config;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.AbstractConfigListEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.ArrayList;
import java.util.List;

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

        List<String> itemList = new ArrayList<>(Config.get().items);
        List<String> defaultItemList = new ArrayList<>(Config.DEFAULT_ITEMS);

        AbstractConfigListEntry<List<String>> itemsEntry =
                entryBuilder.startStrList(
                                Component.translatable("option.better_slots.settings.items.list"),
                                itemList
                        )
                        .setExpanded(true)
                        .setDefaultValue(defaultItemList)
                        .setTooltip(Component.translatable("option.better_slots.settings.items.tooltip"))
                        .setSaveConsumer(newValue -> {
                            Config.get().items.clear();
                            for (String id : newValue) {
                                if (ResourceLocation.isValidResourceLocation(id)) {
                                    Config.get().items.add(id);
                                }
                            }
                        })
                        .build();

        generalCategory.addEntry(itemsEntry);

        return builder.build();
    }
}