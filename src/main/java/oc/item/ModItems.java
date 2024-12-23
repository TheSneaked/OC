package oc.item;



import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import oc.OC;
import oc.component.ModDataComponentTypes;
import oc.item.custom.*;

public class ModItems {


    public static final Item TRENCH_BLADE = registerItem("trench_blade",
            new TrenchBlade(ModToolMaterial.TRENCH_BLADE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterial.TRENCH_BLADE,2,-2.8f ))));

    public static final Item ABYSSAL_MOOR = registerItem("abyssal_moor",
            new AbyssalMoor(ModToolMaterial.ABYSSAL_MOOR, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterial.ABYSSAL_MOOR,4, -3.3f ))));
    public static final Item LEVI_SWORD = registerItem("levi_sword",
            new LeviSword(ModToolMaterial.LEVI_SWORD, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterial.LEVI_SWORD,3, -2.6f ))));
 public static final Item LEVI_AXE = registerItem("levi_axe",
            new LeviAxe(ModToolMaterial.LEVI_AXE, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterial.LEVI_AXE,3, -3f ))));
    public static final Item DRAGON_INGOT = registerItem("dragon_ingot",
            new Item(new Item.Settings()));
    public static final Item ABYSSAL_SHARD = registerItem("abyssal_shard",
            new Item(new Item.Settings()));
    public static final Item SCHLORP_GLORP = registerItem("schlorp_glorp",
            new SchlorpGlorp(ModToolMaterial.SCHLORP_GLORP, new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ModToolMaterial.SCHLORP_GLORP,4,-2.8f ))));

    public static final Item WIND_BAG = registerItem("wind_bag",
            new WindBag( new  Item.Settings().maxCount(1).component(ModDataComponentTypes.WIND_COUNT, 13)));


    //big sad

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(OC.MOD_ID, name), item);
    }

    public static void registerModItems() {

    }
}
