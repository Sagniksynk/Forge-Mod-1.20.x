package net.sagnik.newmod.item.custom.guns;

import net.minecraft.sounds.SoundEvent;
import net.sagnik.newmod.sound.ModSounds;

public class RifleItem extends GunItem {
    public RifleItem() {
        super("rifle",
                12f,   // damage
                4.5f,  // speed (faster bullet)
                1,     // pellets
                0.5f,  // spread (very accurate)
                4);   // fire cooldown
    }

    @Override
    protected SoundEvent getShootSound() { return ModSounds.RIFLE_SHOOT.get(); }
}