package com.alfakynz.better_slots.neoforge.config;

import com.alfakynz.better_slots.Constants;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = Constants.MOD_ID, dist = Dist.CLIENT)
public class ModMenuIntegration {
    public ModMenuIntegration(ModContainer container) {
        if (ModList.get().isLoaded("cloth_config")) {
            container.registerExtensionPoint(IConfigScreenFactory.class, (mc, parent) -> ConfigScreen.create(parent));
        }
    }
}