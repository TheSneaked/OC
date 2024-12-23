package oc.item;



import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import oc.OC;
import oc.block.ModBlocks;


public class ModItemGroups {
    public static final ItemGroup AOTC_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(OC.MOD_ID, "oc"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.TRENCH_BLADE))
                    .displayName(Text.translatable("itemgroup.oc.oc"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.TRENCH_BLADE);
                        entries.add(ModItems.ABYSSAL_MOOR);
                        entries.add(ModItems.LEVI_SWORD);
                        entries.add(ModItems.LEVI_AXE);
                        entries.add(ModItems.DRAGON_INGOT);
                        entries.add(ModItems.ABYSSAL_SHARD);
                        entries.add(ModItems.SCHLORP_GLORP);
                        entries.add(ModBlocks.BEDROCK_TILES);
                        entries.add(ModItems.WIND_BAG);





                    }).build());


    public static void registerItemGroups() {
        OC.LOGGER.info("Registering Item Groups for " + OC.MOD_ID);
    }
}