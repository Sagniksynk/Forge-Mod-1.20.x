package net.sagnik.newmod.sound;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sagnik.newmod.NewMod;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, NewMod.MOD_ID);

    public static final RegistryObject<SoundEvent> PISTOL_SHOOT = registerSound("pistol_shoot");
    public static final RegistryObject<SoundEvent> RIFLE_SHOOT = registerSound("rifle_shoot");
    public static final RegistryObject<SoundEvent> SHOTGUN_SHOOT = registerSound("shotgun_shoot");
    public static final RegistryObject<SoundEvent> GUN_RELOAD = registerSound("gun_reload");
    public static final RegistryObject<SoundEvent> GUN_EMPTY = registerSound("gun_empty");

    private static RegistryObject<SoundEvent> registerSound(String name) {
        return SOUND_EVENTS.register(name, () ->
                SoundEvent.createVariableRangeEvent(new ResourceLocation(NewMod.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}