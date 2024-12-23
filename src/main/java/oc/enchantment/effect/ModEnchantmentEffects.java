package oc.enchantment.effect;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import oc.OC;

public class ModEnchantmentEffects {
    public static final RegistryKey<Enchantment> BUOYYANT = of("buoyyant");
    public static MapCodec<BuyoyantEnchantmentEffect> BUOYANT_EFFECT = register("buoyant", BuyoyantEnchantmentEffect.CODEC);

    public static final RegistryKey<Enchantment> GROUNDING = of("grounding");
    public static MapCodec<GroundingEnchantmentEffect> GROUNDING_EFFECT = register("grounding", GroundingEnchantmentEffect.CODEC);

    public static final RegistryKey<Enchantment> RETURNAL = of("returnal");
    public static MapCodec<ReturnalEnchantmentEffect> RETURNAL_EFFECT = register("returnal", ReturnalEnchantmentEffect.CODEC);

    private static RegistryKey<Enchantment> of(String path) {
        Identifier id = Identifier.of(OC.MOD_ID, path);
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, id);
    }

    private static <T extends EnchantmentEntityEffect> MapCodec<T> register(String id, MapCodec<T> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(OC.MOD_ID, id), codec);
    }

    public static void registerModEnchantmentEffects() {
        OC.LOGGER.info("Registering EnchantmentEffects for" + OC.MOD_ID);
    }
}