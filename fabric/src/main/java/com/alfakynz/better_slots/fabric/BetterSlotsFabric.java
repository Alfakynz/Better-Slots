package com.alfakynz.better_slots.fabric;

import net.fabricmc.api.ModInitializer;

import com.alfakynz.better_slots.BetterSlots;
import net.fabricmc.loader.api.FabricLoader;

public final class BetterSlotsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        BetterSlots.init(FabricLoader.getInstance().getConfigDir());
    }
}
