package oc.item.custom;


import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import oc.enchantment.effect.ModEnchantmentEffects;
import oc.enchantment.effect.ReturnalEnchantmentEffect;
import oc.world.dimension.ModDimensions;
import org.intellij.lang.annotations.Identifier;

import java.awt.*;
import java.util.Set;

public class SchlorpGlorp extends SwordItem {

    public static boolean doesStackHaveEnchant(ItemStack stack, RegistryKey<Enchantment> enchantment) {
        var enchantments = EnchantmentHelper.getEnchantments(stack).getEnchantments();
        if(enchantments.isEmpty()) return false;

        for (var enchant : enchantments) {
            var key = enchant.getKey();
            if(key.isEmpty()) break;
            if(key.get() == enchantment) {
                return true;
            }
        }
        return false;
    }
    public SchlorpGlorp(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target.getHealth() <= 0 && target instanceof ServerPlayerEntity player) {
            World world = attacker.getWorld();
            player.setSpawnPoint(ModDimensions.SPACE_LEVEL_KEY, new BlockPos(0, 64, 0), 0, true, false);
        }


        return super.postHit(stack, target, attacker);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (doesStackHaveEnchant(user.getStackInHand(hand), ModEnchantmentEffects.RETURNAL)) {
            if (world instanceof ServerWorld serverWorld) {
                var infinity = serverWorld.getServer().getWorld(ModDimensions.SPACE_LEVEL_KEY);
                for (ServerPlayerEntity player : infinity.getPlayers()) {
                    player.teleport(serverWorld, user.getX(), user.getY(), user.getZ(), Set.of(), user.getYaw(), user.getPitch());
                }
            }
        }
        return super.use(world, user, hand);
    }


    @Override
    public Text getName(ItemStack stack) {
        return Text.translatable(this.getTranslationKey(stack)).setStyle(Style.EMPTY.withColor(0x9edea9 ));
    }
}



