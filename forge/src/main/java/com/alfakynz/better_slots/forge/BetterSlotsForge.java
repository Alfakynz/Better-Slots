package com.alfakynz.better_slots.forge;

import com.alfakynz.better_slots.Constants;
import net.minecraftforge.fml.common.Mod;

import com.alfakynz.better_slots.BetterSlots;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(Constants.MOD_ID)
public final class BetterSlotsForge {
    public BetterSlotsForge() {
        BetterSlots.init(FMLPaths.CONFIGDIR.get());
    }
}
