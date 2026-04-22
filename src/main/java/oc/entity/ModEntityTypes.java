package oc.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import oc.OC;

public class ModEntityTypes {


    private static <T extends Entity> EntityType <T> registerEntity(String name, EntityType<T> entity) {
        return Registry.register(Registries.ENTITY_TYPE, Identifier.of(OC.MOD_ID, name), entity);
    }
    public static EntityType<LeviathanSwordProjectileEntity> leviathanSwordProjectileEntityEntityType = registerEntity("leviathan_sword", EntityType.Builder.<LeviathanSwordProjectileEntity>create(LeviathanSwordProjectileEntity::new, SpawnGroup.MISC).build());
    public static void registerModEntityTypes() {
    }
}
