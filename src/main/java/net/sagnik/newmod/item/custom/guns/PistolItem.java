package net.sagnik.newmod.item.custom.guns;

import net.minecraft.sounds.SoundEvent;
import net.sagnik.newmod.sound.ModSounds;

public class PistolItem extends GunItem {
    public PistolItem() {
        super("pistol",
                8f,    // damage
                3.0f,  // speed
                1,     // pellets
                2f,    // spread
                10);   // fire cooldown (ticks)
    }

    @Override
    protected SoundEvent getShootSound() { return ModSounds.PISTOL_SHOOT.get(); }
}