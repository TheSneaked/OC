package oc;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import oc.block.ModBlocks;
import oc.component.ModDataComponentTypes;
import oc.effect.ModEffects;
import oc.enchantment.effect.ModEnchantmentEffects;
import oc.item.ModItemGroups;
import oc.item.ModItems;
import oc.sound.ModSounds;
import oc.world.dimension.ModDimensions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OC implements ModInitializer {
	public static final String MOD_ID = "oc";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier id(String string) {
		return Identifier.of(MOD_ID, string);
	}
	@Override
	public void onInitialize() {


		ServerTickEvents.END_WORLD_TICK.register(world -> {
			for (ServerPlayerEntity player : world.getPlayers()) {
				if (player.getWorld().getDimensionEntry().getKey().isPresent()) {
					if (player.getWorld().getDimensionEntry().getKey().get().equals(ModDimensions.SPACE_DIM_TYPE)) {
						player.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 100, 1));
						if (player.getPos().y > 64) {
							player.requestTeleport(player.getX(), player.getY() - 4, player.getZ());
						}
					}
				}
			}
		});


		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModEffects.registerModEffects();
		ModBlocks.registerModBlocks();
		ModDataComponentTypes.registerDataComponentTypes();
		ModSounds.registerSounds();
		ModEnchantmentEffects.registerModEnchantmentEffects();
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
	}
}