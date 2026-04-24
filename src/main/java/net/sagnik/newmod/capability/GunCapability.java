package net.sagnik.newmod.capability;

import net.minecraft.nbt.CompoundTag;

public class GunCapability {

    private int pistolAmmo = 0;
    private int rifleAmmo = 0;
    private int shotgunAmmo = 0;

    private boolean isReloading = false;
    private int reloadCooldown = 0;

    public int getAmmo(String gunType) {
        return switch (gunType) {
            case "pistol" -> pistolAmmo;
            case "rifle" -> rifleAmmo;
            case "shotgun" -> shotgunAmmo;
            default -> 0;
        };
    }

    public void setAmmo(String gunType, int amount) {
        switch (gunType) {
            case "pistol" -> pistolAmmo = Math.max(0, amount);
            case "rifle" -> rifleAmmo = Math.max(0, amount);
            case "shotgun" -> shotgunAmmo = Math.max(0, amount);
        }
    }

    public void consumeAmmo(String gunType) {
        setAmmo(gunType, getAmmo(gunType) - 1);
    }

    public boolean isReloading() { return isReloading; }
    public void setReloading(boolean reloading) { this.isReloading = reloading; }
    public int getReloadCooldown() { return reloadCooldown; }
    public void setReloadCooldown(int ticks) { this.reloadCooldown = ticks; }
    public void tickReload() { if (reloadCooldown > 0) reloadCooldown--; }

    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("pistolAmmo", pistolAmmo);
        tag.putInt("rifleAmmo", rifleAmmo);
        tag.putInt("shotgunAmmo", shotgunAmmo);
        return tag;
    }

    public void deserializeNBT(CompoundTag tag) {
        pistolAmmo = tag.getInt("pistolAmmo");
        rifleAmmo = tag.getInt("rifleAmmo");
        shotgunAmmo = tag.getInt("shotgunAmmo");
    }
}