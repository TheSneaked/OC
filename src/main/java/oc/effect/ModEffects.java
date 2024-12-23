package oc.effect;


import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import oc.OC;

public final class ModEffects {

    public static RegistryEntry<StatusEffect> SUBMERGED_EFFECT;
    public static RegistryEntry<StatusEffect> STUN_EFFECT;


    private static RegistryEntry<StatusEffect> register(String id, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(OC.MOD_ID, id), statusEffect);
    }

    public static void registerModEffects() {
        SUBMERGED_EFFECT = register("submerged", new SubmergedEffect());




    }
}
