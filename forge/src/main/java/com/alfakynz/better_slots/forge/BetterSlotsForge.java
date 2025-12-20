package com.alfakynz.better_slots.forge;

import net.minecraftforge.fml.common.Mod;

import com.alfakynz.better_slots.BetterSlots;

@Mod(BetterSlots.MOD_ID)
public final class BetterSlotsForge {
    public BetterSlotsForge() {
        BetterSlots.init();
    }
}
