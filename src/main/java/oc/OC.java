package oc;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Block;
import net.minecraft.command.argument.packrat.ParseResults;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import oc.block.ModBlocks;
import oc.component.ModDataComponentTypes;
import oc.effect.ModEffects;
import oc.enchantment.effect.ModEnchantmentEffects;
import oc.entity.ModEntityTypes;
import oc.event.ModEvents;
import oc.item.ModItemGroups;
import oc.item.ModItems;
import oc.sound.ModSounds;
import oc.world.dimension.ModDimensions;
import org.apache.logging.log4j.core.jmx.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

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

						if (player.getPos().y > 105) {
							player.setPos(0, 4, 0);
							{
							}

						}
						if (player.getPos().y < 100) {
							if (world instanceof ServerWorld serverWorld) {
                           player.setSpawnPoint(World.OVERWORLD, serverWorld.getSpawnPos(), 0, false ,false );


								//serverWorld.getServer().getCommandManager().getDispatcher().parse()

							}

							//this is where i teleport them to the overworld but idk how
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
		ModEvents.registerModEvents();
		ModEntityTypes.registerModEntityTypes();
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
	}
}