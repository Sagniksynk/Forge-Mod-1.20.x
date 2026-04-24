package net.sagnik.newmod.item.custom.guns;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.sagnik.newmod.capability.GunCapabilityProvider;
import net.sagnik.newmod.entity.BulletEntity;
import net.sagnik.newmod.network.ModMessages;
import net.sagnik.newmod.network.packet.AmmoDataPacket;
import net.minecraftforge.network.PacketDistributor;
import net.minecraft.server.level.ServerPlayer;
import net.sagnik.newmod.sound.ModSounds;

public abstract class GunItem extends Item {

    public static final int PISTOL_MAX_AMMO = 12;
    public static final int RIFLE_MAX_AMMO = 30;
    public static final int SHOTGUN_MAX_AMMO = 8;

    protected final String gunType;
    protected final float damage;
    protected final float speed;
    protected final int pellets;
    protected final float spread;
    protected final int fireCooldown;

    public GunItem(String gunType, float damage, float speed,
                   int pellets, float spread, int fireCooldown) {
        super(new Item.Properties().stacksTo(1));
        this.gunType = gunType;
        this.damage = damage;
        this.speed = speed;
        this.pellets = pellets;
        this.spread = spread;
        this.fireCooldown = fireCooldown;
    }

    public String getGunType() { return gunType; }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (hand != InteractionHand.MAIN_HAND)
            return InteractionResultHolder.pass(player.getItemInHand(hand));

        player.getCapability(GunCapabilityProvider.GUN_CAP).ifPresent(cap -> {
            if (cap.isReloading()) return;

            int currentAmmo = cap.getAmmo(gunType);

            if (currentAmmo <= 0) {
                // ← ADD THIS
                level.playSound(player, player.blockPosition(),
                        ModSounds.GUN_EMPTY.get(), SoundSource.PLAYERS, 1f, 1f);
                return;
            }

            if (!level.isClientSide()) {
                // Fire bullets
                for (int i = 0; i < pellets; i++) {
                    BulletEntity bullet = new BulletEntity(level, player, damage);

                    Vec3 look = player.getLookAngle();
                    double spreadX = (level.random.nextFloat() - 0.5f) * spread * 0.1;
                    double spreadY = (level.random.nextFloat() - 0.5f) * spread * 0.1;

                    bullet.setDeltaMovement(
                            (look.x + spreadX) * speed,
                            (look.y + spreadY) * speed,
                            (look.z + spreadX) * speed
                    );

                    level.addFreshEntity(bullet);
                }

                // ← ADD THIS
                level.playSound(null, player.blockPosition(),
                        getShootSound(), SoundSource.PLAYERS, 1f, 1f);

                // Consume ammo
                cap.consumeAmmo(gunType);

                ModMessages.INSTANCE.send(
                        PacketDistributor.PLAYER.with(() -> (ServerPlayer) player),
                        new AmmoDataPacket(gunType, cap.getAmmo(gunType)));
            }
        }); // ← capability lambda closes here

        player.getCooldowns().addCooldown(this, fireCooldown);
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, net.minecraft.world.entity.LivingEntity entity) {
        return true;
    }

    protected abstract net.minecraft.sounds.SoundEvent getShootSound();
}