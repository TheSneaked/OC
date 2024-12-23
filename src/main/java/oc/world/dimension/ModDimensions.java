package oc.world.dimension;

import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.DimensionTypes;
import oc.OC;

import java.util.OptionalLong;

public class ModDimensions {
    public static final RegistryKey<DimensionOptions> SPACE_KEY = RegistryKey.of(RegistryKeys.DIMENSION,
            Identifier.of(OC.MOD_ID,"infinity"));
    public static final RegistryKey<World> SPACE_LEVEL_KEY = RegistryKey.of(RegistryKeys.WORLD,
            Identifier.of(OC.MOD_ID,"infinity"));
    public static final RegistryKey<DimensionType> SPACE_DIM_TYPE = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
            Identifier.of(OC.MOD_ID,"infinity_type"));
    public static void bootstrapType(Registerable<DimensionType> context) {
        context.register(SPACE_DIM_TYPE,new DimensionType(
                OptionalLong.of(12000),
                false,
                false,
                false,
                false,
                1.0,
                true,
                false,
                0,
                256,
                256,
                BlockTags.INFINIBURN_END,
                DimensionTypes.OVERWORLD_ID,
                1.0f,
                new DimensionType.MonsterSettings(false,false, UniformIntProvider.create(0,0),0)));
    }
}