package net.sagnik.newmod.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GunCapabilityProvider implements ICapabilityProvider, net.minecraftforge.common.util.INBTSerializable<CompoundTag> {

    public static final Capability<GunCapability> GUN_CAP = CapabilityManager.get(new CapabilityToken<>(){});

    private GunCapability capability = null;
    private final LazyOptional<GunCapability> lazyOptional = LazyOptional.of(this::getOrCreate);

    private GunCapability getOrCreate() {
        if (capability == null) capability = new GunCapability();
        return capability;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == GUN_CAP ? lazyOptional.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() { return getOrCreate().serializeNBT(); }

    @Override
    public void deserializeNBT(CompoundTag nbt) { getOrCreate().deserializeNBT(nbt); }
}