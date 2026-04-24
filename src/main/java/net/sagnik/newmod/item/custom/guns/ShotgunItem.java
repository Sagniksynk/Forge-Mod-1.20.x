package net.sagnik.newmod.item.custom.guns;

import net.minecraft.sounds.SoundEvent;
import net.sagnik.newmod.sound.ModSounds;

public class ShotgunItem extends GunItem {
    public ShotgunItem() {
        super("shotgun",
                5f,    // damage per pellet
                2.0f,  // speed
                6,     // pellets (6 spread shots)
                15f,   // spread (wide)
                40);   // fire cooldown (slow)
    }

    @Override
    protected SoundEvent getShootSound() { return ModSounds.SHOTGUN_SHOOT.get(); }
}