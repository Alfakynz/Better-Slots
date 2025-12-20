package com.alfakynz.better_slots.fabric;

import net.fabricmc.api.ModInitializer;

import com.alfakynz.better_slots.BetterSlots;

public final class BetterSlotsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        BetterSlots.init();
    }
}
