package oc.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import oc.OC;

public class ModSounds {
    public static final SoundEvent WIND_USE = registerSoundEvent("wind_use");





    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(OC.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        OC.LOGGER.info("Registering Mod Sounds for " + OC.MOD_ID);
    }
}