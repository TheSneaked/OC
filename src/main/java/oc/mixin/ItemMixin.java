package oc.mixin;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BundleContentsComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.BreezeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BundleItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import oc.item.ModItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {

    @Inject(at = @At("HEAD"), method = "useOnEntity", cancellable = true)
    private void useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        // Check if this is a bundle being used on a breeze
        if(stack.isOf(Items.BUNDLE) && entity instanceof BreezeEntity) {
            // Kill the entity
            entity.kill();

            // Destroy the bundle
            dropAllBundledItems(stack, user);
            stack.decrement(1);

            // Give the bag
            ItemStack bagStack = ModItems.WIND_BAG.getDefaultStack();
            user.giveItemStack( bagStack);

            // Return ActionResult.CONSUME
            cir.setReturnValue(ActionResult.CONSUME);
            cir.cancel();
        }
    }

    @Unique
    private static void dropAllBundledItems(ItemStack stack, PlayerEntity player) {
        BundleContentsComponent bundleContentsComponent = stack.get(DataComponentTypes.BUNDLE_CONTENTS);
        if (bundleContentsComponent != null && !bundleContentsComponent.isEmpty()) {
            stack.set(DataComponentTypes.BUNDLE_CONTENTS, BundleContentsComponent.DEFAULT);

            if (player instanceof ServerPlayerEntity) bundleContentsComponent.iterateCopy().forEach((stacks) -> player.dropItem(stacks, true));
        }
    }
}
