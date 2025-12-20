package com.alfakynz.better_slots.neoforge;

import com.alfakynz.better_slots.BetterSlots;
import com.alfakynz.better_slots.Constants;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLPaths;

@Mod(Constants.MOD_ID)
public final class BetterSlotsNeoForge {
    public BetterSlotsNeoForge() {
        BetterSlots.init(FMLPaths.CONFIGDIR.get());
    }
}
