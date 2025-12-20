package com.alfakynz.better_slots.mixin;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractContainerMenu.class)
public abstract class TorchSlot {

    @Shadow
    public abstract Slot getSlot(int index);

    @Inject(method = "clicked", at = @At("HEAD"), cancellable = true)
    private void onClicked(
            int slotId,
            int dragType,
            ClickType clickType,
            Player player,
            CallbackInfo ci
    ) {
        if (clickType != ClickType.QUICK_MOVE) return;

        Slot slot = this.getSlot(slotId);
        if (slot == null) return;

        ItemStack stack = slot.getItem();
        if (stack.isEmpty()) return;

        if (stack.getItem() == Items.TORCH) {
            ItemStack offhand = player.getItemBySlot(EquipmentSlot.OFFHAND);

            // Case 1: offhand is empty -> move the whole stack
            if (offhand.isEmpty()) {
                player.setItemSlot(EquipmentSlot.OFFHAND, stack.copy());
                slot.set(ItemStack.EMPTY);
                ci.cancel();
                return;
            }

            // Case 2: offhand already contains torches -> try to merge stacks
            if (offhand.getItem() == Items.TORCH && offhand.getCount() < offhand.getMaxStackSize()) {
                int transferable = Math.min(
                    stack.getCount(),
                    offhand.getMaxStackSize() - offhand.getCount()
                );

                offhand.grow(transferable);
                stack.shrink(transferable);

                if (stack.isEmpty()) {
                    slot.set(ItemStack.EMPTY);
                } else {
                    slot.setChanged();
                }

                ci.cancel();
            }
        }
    }
}