package com.alfakynz.better_slots.forge;

import net.minecraftforge.fml.common.Mod;

import com.alfakynz.better_slots.BetterSlots;

@Mod(BetterSlots.MOD_ID)
public final class ExampleModForge {
    public ExampleModForge() {
        // Run our common setup.
        BetterSlots.init();
    }
}
